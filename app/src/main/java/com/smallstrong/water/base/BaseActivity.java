package com.smallstrong.water.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.smallstrong.ss_baseui.StatusLayout;

import butterknife.ButterKnife;

/**
 * 通用基类activity
 *
 * @author smallstrong
 * created at 2017/6/17 下午5:06
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    protected P mPresenter;
    protected StatusLayout statusLayout;
    public static final int INSTANT_IN = 0;
    public static final int INSTANT_OUT = 0;

    @SuppressLint({"InlinedApi", "NewApi"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);//在找到控件之后才能传入layout
        setStatusLayout();
        mPresenter = loadPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initData();
    }

    //加载布局
    protected abstract int getLayoutId();
    //
    protected abstract void setStatusLayout();
    //创建P层
    protected abstract P loadPresenter();

    protected abstract void reLoad();

    protected abstract void initData();

    public void showLoading() {
        if (statusLayout != null) {
            statusLayout.showLoading();
        }
    }

    public void showContent() {
        if (statusLayout != null) {
            statusLayout.showContent();
        }
    }

    public void showEmpty() {
        if (statusLayout != null) {
            statusLayout.showEmpty();
        }
    }

    public void showError() {
        if (statusLayout != null) {
            statusLayout.showError();
        }
    }

    public void closeKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v != null) imm.showSoftInput(v, 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (statusLayout != null) {
            statusLayout.setRetryListener(clickListener);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reLoad();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(INSTANT_IN, INSTANT_OUT);
    }
}
