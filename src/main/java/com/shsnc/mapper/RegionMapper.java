package com.shsnc.mapper;

import com.shsnc.base.util.sql.Pagination;
import com.shsnc.bean.RegionQueryCondition;
import com.shsnc.model.RegionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    int addEntity(RegionModel regionModel);
    int findCountByCondition(@Param("condition") RegionQueryCondition condition);
    List<RegionModel> findByCondition(@Param("condition") RegionQueryCondition condition ,@Param("pagination") Pagination pagination);
}
