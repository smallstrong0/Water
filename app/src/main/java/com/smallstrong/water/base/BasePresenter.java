package com.smallstrong.water.base;

import java.lang.ref.WeakReference;

/**
 *
 *@author smallstrong
 *created at 2017/6/17 下午6:08
 */


public abstract class BasePresenter<V extends IView> implements IPresenter,IView {
    private WeakReference actReference;
    public V mView;


    @Override
    public void attachView(IView iView) {
        actReference = new WeakReference(iView);
        mView = (V) actReference.get();
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }


    @Override
    public void showLoadingView() {
        mView.showLoadingView();
    }


    @Override
    public void showErrorView(String msg) {
        mView.showErrorView(msg);

    }


}

