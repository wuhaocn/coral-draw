package com.mxgraph.server.biz.service.impl;

import com.mxgraph.server.biz.bean.DrawData;
import com.mxgraph.server.biz.dao.DrawDataDao;
import com.mxgraph.server.biz.service.DrawDataService;
import com.mxgraph.server.biz.service.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 部门负责人接口实现
 * @author coral
 */
@Slf4j
@Service
@Transactional
public class DrawDataServiceImpl implements DrawDataService {

    @Autowired
    private DrawDataDao drawDataDao;

    @Override
    public DrawDataDao getRepository() {
        return drawDataDao;
    }


    @Override
    public List<DrawData> findByOwnerId(String ownerId) {
        List<DrawData> drawDataList =  drawDataDao.findByOwnerId(ownerId);
        for (DrawData drawData :drawDataList) {
           // drawData.setBody(null);
        }
        return drawDataList;
    }
    @Override
    public DrawData findByUuid(String uuid) {
        List<DrawData> drawDataList =  drawDataDao.findByUuid(uuid);
        if (drawDataList != null && drawDataList.size() > 0 ){
            return drawDataList.get(0);
        }
        return null;
    }

    @Override
    public Page<DrawData> findByConfition(Integer type, String key, SearchVo searchVo, Pageable pageable) {

        return drawDataDao.findAll(new Specification<DrawData>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<DrawData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<String> requestUrlField = root.get("requestUrl");
                Path<String> requestTypeField = root.get("requestType");
                Path<String> requestParamField = root.get("requestParam");
                Path<String> usernameField = root.get("username");
                Path<String> ipField = root.get("ip");
                Path<String> ipInfoField = root.get("ipInfo");
                Path<Integer> logTypeField = root.get("logType");
                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //类型
                if(type!=null){
                    list.add(cb.equal(logTypeField, type));
                }

                //模糊搜素
                if(!StringUtils.isEmpty(key)){
                    Predicate p1 = cb.like(requestUrlField,'%'+key+'%');
                    Predicate p2 = cb.like(requestTypeField,'%'+key+'%');
                    Predicate p3 = cb.like(requestParamField,'%'+key+'%');
                    Predicate p4 = cb.like(usernameField,'%'+key+'%');
                    Predicate p5 = cb.like(ipField,'%'+key+'%');
                    Predicate p6 = cb.like(ipInfoField,'%'+key+'%');
                    Predicate p7 = cb.like(nameField,'%'+key+'%');
                    list.add(cb.or(p1,p2,p3,p4,p5,p6, p7));
                }

//                //创建时间
//                if(!StringUtils.isEmpty(searchVo.getStartDate())&&!StringUtils.isEmpty(searchVo.getEndDate())){
//                    Date start = DateUtil.parse(searchVo.getStartDate());
//                    Date end = DateUtil.parse(searchVo.getEndDate());
//                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
//                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }
}