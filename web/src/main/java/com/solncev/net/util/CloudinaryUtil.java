package com.solncev.net.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "itis-2023");
            configMap.put("api_key", "478343635312231");
            configMap.put("api_secret", "vzYCHlcDKcbQ8LCiGXO-4YAfJUM");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
