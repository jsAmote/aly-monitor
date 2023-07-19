package com.shsnc.bean;

import lombok.Data;
import lombok.Value;

@Data
public class RegionQueryCondition extends QueryCondition {
    private String ipStart;
    private String ipEnd;
    private String con;

}
