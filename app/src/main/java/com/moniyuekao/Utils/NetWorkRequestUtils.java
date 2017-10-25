package com.moniyuekao.Utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.moniyuekao.View.CallBackResult;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * User: 张亚博
 * Date: 2017-10-25 09:08
 * Description：
 */
public class NetWorkRequestUtils {
    private OkHttpClient client;
    public  static NetWorkRequestUtils networkRequest;
    private final Handler handler;
    private final Gson gson;

    public NetWorkRequestUtils() {
        client=new OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .build();

        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());


    }

    @SuppressLint("NewApi")
    public static NetWorkRequestUtils getInstance(){
            if (networkRequest==null) {
                synchronized (NetWorkRequestUtils.class){
                    if (networkRequest==null) {
                        networkRequest = new NetWorkRequestUtils();
                    }
                }
            }
            return networkRequest;
        }

        public<T>  void  GetCommonData(String url, Map<String,Object> map, final CallBackResult callBackResult, final Class<T> tclass){

            FormBody.Builder requestBody= new FormBody.Builder();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                requestBody.add(entry.getKey(),entry.getValue().toString());
            }
            RequestBody requestbody = requestBody.build();
            Request request=new Request.Builder().post(requestbody).url(url).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callBackResult.onFailure(call,e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        T t = gson.fromJson(string, tclass);
                        final Message msg = Message.obtain();
                        msg.obj=t;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBackResult.onReponse(msg.obj);
                            }
                        });

                    }


                }
            });
        };
}
