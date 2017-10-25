package com.moniyuekao.View;

import okhttp3.Call;

/**
 * User: 张亚博
 * Date: 2017-10-25 09:28
 * Description：
 */
public interface CallBackResult<T> {
    void onFailure(Call call,Exception e);
    void onReponse(T t);
}
