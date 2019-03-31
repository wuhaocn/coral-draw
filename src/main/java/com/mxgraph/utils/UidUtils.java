package com.mxgraph.utils;

import com.mxgraph.bean.DrawData;
import com.mxgraph.bean.DrawUser;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.util.Base64Utils;

public class UidUtils {
    public  static String getUid(String user){
        String uid = System.currentTimeMillis() + Base64Utils.encodeToString(user.getBytes());
        return uid;
    }

    public static void main(String[] args) {
        System.out.println(getUid("123"));
    }
}
