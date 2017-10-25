package com.moniyuekao.Modul;

import com.moniyuekao.Commoninfo;
import com.moniyuekao.Utils.NetWorkRequestUtils;
import com.moniyuekao.View.CallBackResult;
import com.moniyuekao.View.ownerView;

import java.util.Map;

import okhttp3.Call;

/**
 * User: 张亚博
 * Date: 2017-10-25 09:05
 * Description：
 */
public class modul implements CallBackResult<Commoninfo> {
    private ownerView  ownerView;

    public modul() {
    }

    public void setOwnerView(com.moniyuekao.View.ownerView ownerView) {
        this.ownerView = ownerView;
    }
    public void getdata(String url, Map<String,Object> map){
        NetWorkRequestUtils.getInstance().GetCommonData(url, map, this,Commoninfo.class);

}

    @Override
    public void onFailure(Call call, Exception e) {
        ownerView.onFailure(call, e);
    }

    @Override
    public void onReponse(Commoninfo commoninfo) {
        ownerView.onResponse(commoninfo);
    }


}