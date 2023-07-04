package com.shsnc.api;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.cms.model.v20190101.*;

public class DescribeMetricLast {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeMetricLastRequest request = new DescribeMetricLastRequest();

        //namespace和metric通过DescribeMetricMetaList和DescribeProjectMeta获取
        request.setNamespace("acs_ecs_dashboard");
        request.setMetricName("cpu_total");
        //查询的关联dimension过滤，既可以是一个JSONArray，也可以是一个JSONObject
        request.setDimensions("[{\"instanceId\":\"i-8vb6p*****\"}]");
        //本次查询的分页长度，每次查询最多返回1000条数据。
        request.setLength("1000");
        //查询数据的开始时间
        request.setStartTime("2019-07-22 11:00:00");
        //查询数据的结束时间
        request.setEndTime("2019-07-22 12:00:00");
        request.setPeriod("60");

        try {
            DescribeMetricLastResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}
