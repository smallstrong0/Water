package com.smallstrong.water.base;

/**
 * Http 请求接口
 */
public interface HttpResponseInterface {

    void onResponse(Object result);

    void onFailure();

}
