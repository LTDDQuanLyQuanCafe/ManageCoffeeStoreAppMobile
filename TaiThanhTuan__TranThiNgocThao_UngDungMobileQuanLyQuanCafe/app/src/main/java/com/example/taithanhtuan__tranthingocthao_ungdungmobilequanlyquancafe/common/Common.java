package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Common {
    public static String get_IPLocal() {
        return _IP;
    }
    static String _IP;
    public Common(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    _IP = Inet4Address.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static String preUrl = String.format("https://%s/api/",get_IPLocal());
}
