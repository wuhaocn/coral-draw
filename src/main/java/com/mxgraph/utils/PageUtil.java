package com.mxgraph.utils;


import com.mxgraph.bean.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * @author coral
 */
public class PageUtil {

    public static Pageable initPage(PageVo page){

        Pageable pageable=null;
        int pageNumber=page.getPageNumber();
        int pageSize=page.getPageSize();
        String sort=page.getSort();
        String order=page.getOrder();

        if(pageNumber<1){
            pageNumber=1;
        }
        if(pageSize<1){
            pageSize=10;
        }
        if(StringUtils.isNotBlank(sort)) {
            Sort.Direction d;
            if(StringUtils.isBlank(order)) {
                d = Sort.Direction.DESC;
            }else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = new Sort(d,sort);
            pageable = PageRequest.of(pageNumber-1, pageSize,s);
        }else {
            pageable = PageRequest.of(pageNumber-1, pageSize);
        }
        return pageable;
    }
}
