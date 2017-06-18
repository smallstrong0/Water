package com.smallstrong.water.base;

/**
 * Created by smallstrong on 2017/6/17.
 */

public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();

    void onResponse(Object obj);//M层返回数据给P层

}
