package com.smallstrong.water.http;


import com.smallstrong.water.bean.DemoBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by GaoSheng on 2016/9/13.
 * 网络请求的接口都在这里
 */

public interface WaterApi {


    @GET("test")
    Flowable<DemoBean> test(
            @Path("test") String test);
}
