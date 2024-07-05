package com.wyl.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.sql.SqlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.wyl.redis.entity.AdsInduEtpEnterpriseLoginNumDf;
import com.wyl.redis.entity.AdsInduEtpEnterpriseNumDf;
import com.wyl.redis.entity.AdsInduEtpEnterpriseRevokeNumDf;
import com.wyl.redis.entity.AdsInduEtpEnterpriseStartNumDfNew;
import com.wyl.redis.mapper.AdsInduEtpEnterpriseLoginNumDfMapper;
import com.wyl.redis.mapper.AdsInduEtpEnterpriseNumDfMapper;
import com.wyl.redis.mapper.AdsInduEtpEnterpriseRevokeNumDfMapper;
import com.wyl.redis.mapper.AdsInduEtpEnterpriseStartNumDfNewMapper;
import com.wyl.redis.service.AdsInduEtpEnterpriseStartNumDfNewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @Description 红皮云-企业发展分析-注册总量增长趋势分析
* @Author wuyilong
* @Date 2024-07-04
*/
@Slf4j
@Service
public class AdsInduEtpEnterpriseStartNumDfNewServiceImpl extends ServiceImpl<AdsInduEtpEnterpriseStartNumDfNewMapper, AdsInduEtpEnterpriseStartNumDfNew> implements AdsInduEtpEnterpriseStartNumDfNewService {

    @Autowired
    private AdsInduEtpEnterpriseLoginNumDfMapper adsInduEtpEnterpriseLoginNumDfMapper;
    @Autowired
    private AdsInduEtpEnterpriseNumDfMapper adsInduEtpEnterpriseNumDfMapper;
    @Autowired
    private AdsInduEtpEnterpriseRevokeNumDfMapper adsInduEtpEnterpriseRevokeNumDfMapper;
    @Autowired
    private AdsInduEtpEnterpriseStartNumDfNewMapper adsInduEtpEnterpriseStartNumDfNewMapper;

    @Override
    public void mergeAdsInduEtpEnterpriseStartNumDfNew() {
        log.info("***********开始整合**************");

        List<AdsInduEtpEnterpriseNumDf> adsInduEtpEnterpriseNumDfs = adsInduEtpEnterpriseNumDfMapper.selectList(null);
        List<String> domainLinks = adsInduEtpEnterpriseNumDfs.stream().map(AdsInduEtpEnterpriseNumDf::getDomainLink).distinct().collect(Collectors.toList());

        List<AdsInduEtpEnterpriseStartNumDfNew> adsInduEtpEnterpriseStartNumDfNews = new ArrayList<>();
        domainLinks.parallelStream().forEach(v->{
            AdsInduEtpEnterpriseStartNumDfNew adsInduEtpEnterpriseStartNumDfNew = new AdsInduEtpEnterpriseStartNumDfNew();

            // 存续
            LambdaQueryWrapper<AdsInduEtpEnterpriseStartNumDfNew> wrapper = Wrappers.lambdaQuery(AdsInduEtpEnterpriseStartNumDfNew.class);
            wrapper.eq(AdsInduEtpEnterpriseStartNumDfNew::getDomainLink,v);
            wrapper.eq(AdsInduEtpEnterpriseStartNumDfNew::getStartYear,"2021");
            List<AdsInduEtpEnterpriseStartNumDfNew> adsInduEtpEnterpriseNumDfs1 = adsInduEtpEnterpriseStartNumDfNewMapper.selectList(wrapper);

            // 注册量
            LambdaQueryWrapper<AdsInduEtpEnterpriseLoginNumDf> wrapper1 = Wrappers.lambdaQuery(AdsInduEtpEnterpriseLoginNumDf.class);
            wrapper1.eq(AdsInduEtpEnterpriseLoginNumDf::getDomainLink,v);
            wrapper1.eq(AdsInduEtpEnterpriseLoginNumDf::getLoginYear,"2021");
            List<AdsInduEtpEnterpriseLoginNumDf> adsInduEtpEnterpriseLoginNumDf = adsInduEtpEnterpriseLoginNumDfMapper.selectList(wrapper1);

            Map<String, Long> loginMap = new HashMap<>();
            for (AdsInduEtpEnterpriseLoginNumDf induEtpEnterpriseLoginNumDf : adsInduEtpEnterpriseLoginNumDf) {
                loginMap.put(induEtpEnterpriseLoginNumDf.getRegionId(),induEtpEnterpriseLoginNumDf.getLinkEnterpriseLoginNum());
            }

            // 吊销量
            LambdaQueryWrapper<AdsInduEtpEnterpriseRevokeNumDf> wrapper2 = Wrappers.lambdaQuery(AdsInduEtpEnterpriseRevokeNumDf.class);
            wrapper2.eq(AdsInduEtpEnterpriseRevokeNumDf::getDomainLink,v);
            wrapper2.eq(AdsInduEtpEnterpriseRevokeNumDf::getRevokeYear,"2021");
            List<AdsInduEtpEnterpriseRevokeNumDf> adsInduEtpEnterpriseRevokeNumDf = adsInduEtpEnterpriseRevokeNumDfMapper.selectList(wrapper2);
            Map<String, Long> revokeMap = new HashMap<>();
            for (AdsInduEtpEnterpriseRevokeNumDf induEtpEnterpriseRevokeNumDf : adsInduEtpEnterpriseRevokeNumDf) {
                revokeMap.put(induEtpEnterpriseRevokeNumDf.getRegionId(),induEtpEnterpriseRevokeNumDf.getLinkEnterpriseRevokeNum());
            }

            adsInduEtpEnterpriseNumDfs1.forEach(m->{
                Long loginNum = loginMap.get(m.getRegionId());
                Long revokeNum = revokeMap.get(m.getRegionId());
                m.setStartYear("2020");
                Long originNum = m.getLinkEnterpriseStarNum();
                if(loginNum != null) {
                    originNum = m.getLinkEnterpriseStarNum()-loginNum;
                }
                if(revokeNum != null) {
                    originNum +=revokeNum;
                }
                m.setLinkEnterpriseStarNum(originNum);
            });

            // 进行保存
            adsInduEtpEnterpriseStartNumDfNewMapper.insertBatchAll(adsInduEtpEnterpriseNumDfs1);
            log.info("***产业链：{}******保存成功**********",v);
        });

        log.info("***结束**********");
    }

    @Override
    public void adsInduEtpEnterpriseStartNumDfNew2024() {
        // 存续
        LambdaQueryWrapper<AdsInduEtpEnterpriseNumDf> wrapper = Wrappers.lambdaQuery(AdsInduEtpEnterpriseNumDf.class);
        List<AdsInduEtpEnterpriseNumDf> adsInduEtpEnterpriseNumDfs1 = adsInduEtpEnterpriseNumDfMapper.selectList(wrapper);
        List<AdsInduEtpEnterpriseStartNumDfNew> adsInduEtpEnterpriseStartNumDfNews = adsInduEtpEnterpriseNumDfs1.stream().map(m -> {
            AdsInduEtpEnterpriseStartNumDfNew adsInduEtpEnterpriseStartNumDfNew = new AdsInduEtpEnterpriseStartNumDfNew();
            BeanUtil.copyProperties(m, adsInduEtpEnterpriseStartNumDfNew);
            adsInduEtpEnterpriseStartNumDfNew.setStartYear("2024");
            adsInduEtpEnterpriseStartNumDfNew.setLinkEnterpriseStarNum(m.getLinkEnterpriseNum());
            return adsInduEtpEnterpriseStartNumDfNew;
        }).collect(Collectors.toList());

        List<List<AdsInduEtpEnterpriseStartNumDfNew>> partition = Lists.partition(adsInduEtpEnterpriseStartNumDfNews, 10000);
        partition.parallelStream().forEach(v->{
            adsInduEtpEnterpriseStartNumDfNewMapper.insertBatchAll(v);
        });

//        SqlSession sqlSession = SqlHelper.sqlSessionBatch(AdsInduEtpEnterpriseStartNumDfNew.class);
//        AdsInduEtpEnterpriseStartNumDfNewMapper mapper = sqlSession.getMapper(AdsInduEtpEnterpriseStartNumDfNewMapper.class);
//        collect.forEach(v->mapper.insert(v));
//        sqlSession.commit();
//        sqlSession.flushStatements();
        log.info("************保存完毕*************");
    }
}
