package com.moniyuekao.Presenter;

import com.moniyuekao.Commoninfo;
import com.moniyuekao.Modul.modul;
import com.moniyuekao.View.PresenterView;
import com.moniyuekao.View.ownerView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * User: 张亚博
 * Date: 2017-10-25 09:05
 * Description：
 */
public class presenter implements ownerView<Commoninfo> {
    private PresenterView presenterView;
    private modul modul;

    public presenter(PresenterView presenterView) {
        this.presenterView = presenterView;
        modul=new modul();
        modul.setOwnerView(this);
    }

    public void getData(String url,String uid){
        Map<String,Object> map=new HashMap<>();
        map.put("uid",uid);
        modul.getdata(url,map);
    }

    @Override
    public void onFailure(Call call, Exception e) {
        presenterView.onFailure(call, e);
    }

    @Override
    public void onResponse(Commoninfo commoninfo) {
        presenterView.onResponse(commoninfo);
    }


}
