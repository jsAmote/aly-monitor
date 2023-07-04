package com.shsnc.ip2region;

import org.lionsoul.ip2region.xdb.Searcher;

public class ip2region {
    /**
     * 通过ip地址获取所属国家-离线方式
     *
     * @param ip        IP地址
     * @param dbPath    ip2region.xdb路径
     * @return      国家信息
     */
    public static String getCountryByIpOffline(String ip, String dbPath) throws Exception{
        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        byte[] vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        Searcher searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        // 3、查询
        return searcher.search(ip);
    }


    public static void main(String[] args) throws Exception {
        String dbPath = "E:\\work\\aly-monitor\\src\\main\\resources\\ip2region.xdb";

        System.out.println(getCountryByIpOffline("27.105.130.93",dbPath));
        System.out.println(getCountryByIpOffline("221.214.8.82",dbPath));
        System.out.println(getCountryByIpOffline("182.37.164.66",dbPath));
        System.out.println(getCountryByIpOffline("61.144.35.2",dbPath));
    }

}
