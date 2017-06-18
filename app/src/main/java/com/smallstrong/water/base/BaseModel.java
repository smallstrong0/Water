package com.smallstrong.water.base;


import com.smallstrong.water.http.Http;
import com.smallstrong.water.http.WaterApi;

/**
 *
 *@author smallstrong
 *created at 2017/6/18 下午4:46
 */
 
public class BaseModel implements IModel {
    protected static WaterApi waterApi;

    //初始化httpService
    static {
        waterApi = Http.getWaterApi();
    }

}
