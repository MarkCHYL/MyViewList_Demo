package com.view.demo.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.view.demo.bean.ProvinceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 实现代码
 * Created by Mark on 2017/11/13.
 */

public class ResourceManager {

    List<ProvinceModel> list = new ArrayList<>();
    Callable callable;

    public ResourceManager(Callable callable){
        this.callable = callable;
        getData();
    }

    public void getData() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://192.168.0.151:80/1.html")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("mark","failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseData(response.body().string());//response.body().string()调用一次有效，
                // 然后会被清除
            }
        });
    }

    private void parseData(String data){
        try {
            if (data.isEmpty()){
                return;
            }
            JSONObject object = new JSONObject(data);
            JSONArray  jsonArray = object.getJSONArray("data");
            list = formJsonArray(jsonArray.toString(),new TypeToken<ProvinceModel>(){}.getType());
            callable.onCall(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static <T> T formJsonArray(String json, Type t){
        if (json == null){
            return  null;
        }
        return new Gson().fromJson(json,t);
    }

    public interface Callable{
        void onCall(List<ProvinceModel> list);
    }
}
