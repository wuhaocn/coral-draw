package com.mxgraph.server.biz.service.impl;

import com.mxgraph.server.biz.bean.DrawData;
import com.mxgraph.server.biz.dao.DrawDataDao;
import com.mxgraph.server.biz.service.DrawDataService;
import com.mxgraph.server.biz.utils.SearchVo;
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
 *
 * @author coral
 */
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
        List<DrawData> drawDataList = drawDataDao.findByOwnerId(ownerId);
        for (DrawData drawData : drawDataList) {
            // drawData.setBody(null);
        }
        return drawDataList;
    }

    @Override
    public DrawData findByUuid(String uuid) {
        List<DrawData> drawDataList = drawDataDao.findByUuid(uuid);
        if (drawDataList != null && drawDataList.size() > 0) {
            return drawDataList.get(0);
        }
        return null;
    }

    @Override
    public Page<DrawData> findByOwnerIdConfition(String ownerId,  SearchVo searchVo, Pageable pageable) {
        Page<DrawData> drawDataPage = drawDataDao.findAll(new Specification<DrawData>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<DrawData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<String> ownerIdField = root.get("ownerId");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if (!StringUtils.isEmpty(searchVo.getWord())) {
                    Predicate p1 = cb.like(nameField, '%' + searchVo.getWord() + '%');
                    Predicate p2 = cb.equal(ownerIdField, ownerId);
                    list.add(cb.and(p1, p2));
                } else {
                    Predicate p1 = cb.equal(ownerIdField, ownerId);
                    list.add(cb.or(p1));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);

        return drawDataPage;
    }

    @Override
    public Page<DrawData> findByConfition(SearchVo searchVo, Pageable pageable) {

        Page<DrawData> drawDataPage = drawDataDao.findAll(new Specification<DrawData>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<DrawData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<String> uuidField = root.get("uuid");
                Path<String> ownerIdField = root.get("ownerId");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if (!StringUtils.isEmpty(searchVo.getWord())) {
                    Predicate p1 = cb.like(nameField, '%' + searchVo.getWord() + '%');
                    Predicate p2 = cb.like(uuidField, '%' + searchVo.getWord() + '%');
                    Predicate p3 = cb.like(ownerIdField, '%' + searchVo.getWord() + '%');
                    list.add(cb.or(p1, p2, p3));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
//        for (DrawData drawData : drawDataPage.getContent()) {
//            drawData.setBody(new byte[]{});
//            drawData.setSvgData(new byte[]{});
//        }
        return drawDataPage;
    }
}