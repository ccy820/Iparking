package cn.chenchiyi.iparking.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class JSONUtils {

    public static String toString(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json,Class<T> tClass){
        Gson gson = new Gson();
        return gson.fromJson(json, tClass);
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> toList(String json, Type type){
        Gson gson = new Gson();
        return (ArrayList<T>)gson.fromJson(json, type);
    }
}
