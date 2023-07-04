package com.shsnc.ip2region;
import cn.hutool.core.io.FileUtil;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Geoip工具类，使用单例模式（双重校验锁DCL）.
 * GeoLite2-City.mmdb 不太准确
 *
 * @author lixingwu
 * @date 2022-05-23 14:27:00
 */
@Slf4j
public class GeoIp2Analysis {

    private volatile static GeoIp2Analysis geoIp2Analysis;

    /***
     * DatabaseReader，用于加载ip数据库
     */
    private DatabaseReader reader;

    /***
     * TODO：自行修改ip数据库位置
     */
    private static final String GEO_LITE2_MMDB_FILE_PATH = "E:\\work\\aly-monitor\\src\\main\\resources\\GeoLite2-City.mmdb";

    private GeoIp2Analysis() {
        // 初始化加载文件
        try {
            log.info("装载IP数据库文件...");
            File geoLite2MmdbFile = FileUtil.file(GEO_LITE2_MMDB_FILE_PATH);
            reader = new DatabaseReader.Builder(geoLite2MmdbFile).build();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IP数据库[{}]装载失败，{}", GEO_LITE2_MMDB_FILE_PATH, e.getMessage());
        }
    }

    /**
     * 获取IP解析单例
     *
     * @return GeoIp2Analysis
     */
    public static GeoIp2Analysis getInstance() {
        if (geoIp2Analysis == null) {
            synchronized (GeoIp2Analysis.class) {
                if (geoIp2Analysis == null) {
                    geoIp2Analysis = new GeoIp2Analysis();
                }
            }
        }
        return geoIp2Analysis;
    }

    // TODO：此处只是打印了信息，根据实际情况修改
    public void getIpInfo(String ip) throws IOException, GeoIp2Exception {
        InetAddress address = InetAddress.getByName(ip);
        CityResponse response = reader.city(address);
        Country country = response.getCountry();
        Subdivision subdivision = response.getMostSpecificSubdivision();
        City city = response.getCity();
        log.info("{} {} {}",
                country.getNames().get("zh-CN"),
                subdivision.getNames().get("zh-CN"),
                city.getNames().get("zh-CN")
        );
    }

    // TODO：测试
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        GeoIp2Analysis.getInstance().getIpInfo("124.205.155.148");
        GeoIp2Analysis.getInstance().getIpInfo("27.105.130.93");
        GeoIp2Analysis.getInstance().getIpInfo("124.205.155.150");
        GeoIp2Analysis.getInstance().getIpInfo("124.205.155.156");
        GeoIp2Analysis.getInstance().getIpInfo("112.250.107.37");
        GeoIp2Analysis.getInstance().getIpInfo("116.17.102.220");
        GeoIp2Analysis.getInstance().getIpInfo("14.29.139.251");
        GeoIp2Analysis.getInstance().getIpInfo("121.8.215.106");
        GeoIp2Analysis.getInstance().getIpInfo("182.37.164.66");
    }

}
