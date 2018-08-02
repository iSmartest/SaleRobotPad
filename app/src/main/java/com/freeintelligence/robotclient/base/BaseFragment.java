package com.freeintelligence.robotclient.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 所有Fragment都继承此类
 */

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;
    protected Bundle bundle;
    private View view;
    private static final String TAG = "BaseFragment";
    private Toast toast;
    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null)
        view = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
    }
    protected abstract int getLayoutId();
    protected abstract void initView(View view);
    protected abstract void loadData();
    protected void onHidden(){}
    protected void onShow(){}

    protected void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();//getActivity()
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        }

        toast.show();//重新显示吐司
    }
    protected void showToast(int i) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), i, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();//关闭吐司显示
            toast = Toast.makeText(getActivity(), i, Toast.LENGTH_SHORT);
        }

        toast.show();//重新显示吐司
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            onHidden();
        }else {
            onShow();
        }
    }
    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
