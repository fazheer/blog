package com.godfazheer.utils;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtils {
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("Ali-CDN-Real-IP");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-real-ip");
        }
        if (ip == null || "".equals(ip) || "null".equals(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static boolean validIp(String ip) {
        String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ip);
        return m.matches();
    }
    public static String getIpInfo(String ip) throws Exception {
       String dbPath = System.getProperty("user.dir")+"/ip2region/ip2region.xdb";
        byte[] buffer = new byte[0];

        try {
            buffer = Searcher.loadContentFromFile(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher : %s\n", e);
        }

        // 2、查询
        Searcher searcher =Searcher.newWithBuffer(buffer);
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }
        // 3、关闭资源
        searcher.close();
        return "未知";
    }
    public static String getCityName(String ipInfo) {
        String[] split = org.apache.commons.lang3.StringUtils.split(ipInfo,"|");
        if (split != null) {
            if (split[0].equals("0"))
                return "哥谭";
            else return split[3];
        }
        return "未知";
    }
    public static String[] getIpBrowserOps(HttpServletRequest httpServletRequest) throws Exception {
        String ipAddr = getIpAddr(httpServletRequest);
        String cityName = getCityName(getIpInfo(ipAddr));
        String userAgent = httpServletRequest.getHeader("User-Agent");
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        String browser= agent.getBrowser()+" "+agent.getBrowserVersion();
        String ops =agent.getOperatingSystem().getName();
        String result = cityName+" | "+browser+" | "+ops;
        System.out.println(result);
        return org.apache.commons.lang3.StringUtils.split(result,"|");
    }

}
