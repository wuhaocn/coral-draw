package com.mxgraph.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author coral
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;
}
