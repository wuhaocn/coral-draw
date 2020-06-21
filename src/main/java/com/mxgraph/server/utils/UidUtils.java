package com.mxgraph.server.utils;

public class UidUtils {
    public  static String getUid(String user){
        String uid = System.currentTimeMillis() + "_" + user.getBytes().hashCode();
        return uid;
    }

    public static void main(String[] args) {
        System.out.println(getUid("123"));
    }
}
