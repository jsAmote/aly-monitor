package com.shsnc.controller;

import com.shsnc.api.core.annotation.EnableAutoResolve;
import com.shsnc.base.util.config.BizException;
import com.shsnc.base.util.sql.Pagination;
import com.shsnc.base.util.sql.QueryData;
import com.shsnc.bean.RegionQueryCondition;
import com.shsnc.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@EnableAutoResolve
@RequestMapping("/re")
public class RegionController {

    @Autowired
    RegionService regionService;

    @PostMapping("/addRegion")
    public boolean addReportReturn() throws BizException {

        String filePath = "ip2region.txt";
        List text = new ArrayList();
        text = regionService.readTxtFile(filePath,"utf-8");
        return false;
    }

    @CrossOrigin
    @PostMapping("/getIpsByPage")
    public QueryData getReportReturnByPage(RegionQueryCondition condition, Pagination pagination){
        return regionService.getIpsByPage(condition,pagination);
    }



    @PostMapping("/test")
    public String test() {
        System.out.println("111111111111");
        return "Hello";
    }
}
