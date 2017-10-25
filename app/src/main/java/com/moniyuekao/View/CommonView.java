package com.moniyuekao.View;

import okhttp3.Call;

/**
 * User: 张亚博
 * Date: 2017-10-25 09:05
 * Description：
 */
public interface CommonView<T> {
     void onFailure(Call call,Exception e);
     void onResponse(T t);
}
