package com.shsnc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients({"com.shsnc.**.openapi.client.*"})
@EnableDiscoveryClient
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
