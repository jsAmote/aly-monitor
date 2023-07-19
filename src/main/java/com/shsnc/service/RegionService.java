package com.shsnc.service;

import com.shsnc.base.util.JsonUtil;
import com.shsnc.base.util.sql.Pagination;
import com.shsnc.base.util.sql.QueryData;
import com.shsnc.bean.Region;
import com.shsnc.bean.RegionQueryCondition;
import com.shsnc.mapper.RegionMapper;
import com.shsnc.model.RegionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    @Resource
    RegionMapper regionMapper;

    private static Logger logger = LoggerFactory.getLogger(RegionService.class);


    public String add(Region region){
        if(region.getCon() == null){
            return null;
        }
        RegionModel regionModel = JsonUtil.convert(region,RegionModel.class);
        regionMapper.addEntity(regionModel);

        return "OK";
    }

    public QueryData getIpsByPage(RegionQueryCondition condition, Pagination pagination){
        QueryData queryData = new QueryData(pagination);
        int count =  regionMapper.findCountByCondition(condition);
        queryData.setRowCount(count);
        if (count > 0) {
           List<RegionModel> regionModelList = regionMapper.findByCondition(condition,pagination);
            queryData.setRecords(regionModelList.stream().collect(Collectors.toList()));
        }
        return queryData;
    }

    public List readTxtFile(String filePath, String encoding) {
        List res = new ArrayList();
        InputStream is=null;
        InputStreamReader isr=null;
        try {
            is= this.getClass().getClassLoader().getResourceAsStream(filePath);
            isr=new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                //211.142.172.0|211.142.175.255|中国|0|河南省|鹤壁市|移动
                String[] parts = lineTxt.split("\\|");

                // 获取前两个分割部分
                String part1 = parts[0];
                String part2 = parts[1];

                // 将剩下的部分组合到一起
                StringBuilder combined = new StringBuilder();
                for (int i = 2; i < parts.length; i++) {
                    combined.append(parts[i]);
                    if (i < parts.length - 1) {
                        combined.append("|");
                    }
                }

                Region region = new Region();
                region.setIpStart(part1);
                region.setIpEnd(part2);
                region.setCon(combined.toString());

                add(region);
                res.add(lineTxt);
            }
        } catch (Exception e) {
            logger.error("readTxtFile",e);
        }finally {
            if(is!=null){
                try {
                    is.close();
                    isr.close();
                } catch (IOException e) {}
            }
        }
        return res;
    }




}
