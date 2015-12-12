package cn.com.ntesec.uml.utils;
import java.util.Map;

import com.google.gson.*;
 

public class JsonUtils{
 
    private static Gson gson=null;
    static{
        if(gson==null){
            gson=new Gson();
        }
    }
    private JsonUtils(){}
    
    public static String objectToJson(Object ts){
        String jsonStr=null;
        if(gson!=null){
            jsonStr=gson.toJson(ts);
        }
        return jsonStr;
    }
    /**
     * 将Map转化为Json
     * 
     * @param map
     * @return String
     */
    public static <T> String mapToJson(Map<String, T> map) {
     Gson gson = new Gson();
     String jsonStr = gson.toJson(map);
     return jsonStr;
    }
}

