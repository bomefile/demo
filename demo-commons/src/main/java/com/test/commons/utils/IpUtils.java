package com.test.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtils {

	private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);
	
	public static String getLocalIp(){
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress();//获得本机IP
		} catch (UnknownHostException e) {
			logger.error("", e);
		}
		return null;
	}

	public static Long getIpTail(){
        String ip = getLocalIp();

        if (StringUtils.isNotBlank(ip)){
            String[] sections = ip.split("\\.");
            if (sections.length == 4){
                String tail = sections[3];
                return Long.valueOf(tail);
            }
        }

        //ip 末尾范围是0-255
        return 256L;

    }

    public static void main(String[] args) {
        String localIp = getLocalIp();
        System.out.println(localIp);

        Long ipTail = getIpTail();
        System.out.println(ipTail);

    }

}
