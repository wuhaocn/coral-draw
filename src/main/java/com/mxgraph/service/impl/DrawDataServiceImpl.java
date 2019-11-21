package com.mxgraph.service.impl;

import com.google.appengine.api.search.DateUtil;
import com.mxgraph.bean.DrawData;
import com.mxgraph.bean.SearchVo;
import com.mxgraph.dao.DrawDataDao;
import com.mxgraph.service.DrawDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return drawDataDao.findByOwnerId(ownerId);
    }

    @Override
    public Page<DrawData> findByConfition(String ownerId, String key, SearchVo searchVo, Pageable pageable) {
        return drawDataDao.findAll(new Specification<DrawData>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<DrawData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<Date> createTimeField=root.get("createTime");
                Path<Integer> ownerIdField = root.get("ownerId");
                List<Predicate> list = new ArrayList<Predicate>();
                //类型
                if(StringUtils.isNotBlank(ownerId)){
                    list.add(cb.equal(ownerIdField, ownerId));
                }
                //模糊搜素
                if(StringUtils.isNotBlank(key)){

                    Predicate p7 = cb.like(nameField,'%'+key+'%');
                    list.add(cb.or(p7));
                }


                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public DrawData findByUuid(String uuid) {
        return drawDataDao.findByUuid(uuid);
    }
}