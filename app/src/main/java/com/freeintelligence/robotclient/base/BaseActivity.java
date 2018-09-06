package com.freeintelligence.robotclient.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import com.freeintelligence.robotclient.ui.activity.FirstVideoActivity;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.ClickUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;



/**
 * 所有Activity都继承此Activity
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private static final String TAG = "BaseActivity";
    private BaseFragment lastFragment;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000; //需要自己定义标志
    static Handler myHandler = new Handler();
    protected static Context context;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(App.activity, FirstVideoActivity.class);
            startActivity(intent);
            AppManager.finishAllActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);//关键代码
        context = this;
        App.activity = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        loadData();
        AppManager.addActivity(this);
//        RkOperationUtil.showTab (context);
//        WakeupAction.AIUIWakeUp(this, 0);
//        SpeakAction.getInstance().speak(context, "您好，很高兴为您服务", "wakeUp");
    }

    @Override
    protected void onStart() {
        super.onStart();
        startAD();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myHandler.removeCallbacks(myRunnable);
    }


    protected abstract int getLayoutId();

    protected abstract void loadData();

    protected abstract void initView();

    /**
     * 切换Fragment的方法
     *
     * @param fragmentClass 要跳转的Fragment
     * @param containId     容器ID
     * @param isHidden      是否隐藏
     * @param bundle        参数
     * @param isBack        是否添加到回退栈
     * @return
     */
    public BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containId, boolean isHidden, Bundle bundle, boolean isBack) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //获取Fragment的类名，用类名当做Tag
        String fragmentName = fragmentClass.getSimpleName();
        //根据tag来查找当前Fragment，如果不为null 就代表当前Fragment已经被加载过至少一次
        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        if (currentFragment == null) {
            //如果Fragment为null 就创建Fragment对象，添加到FragmentManager中
            try {
                //通过Java动态代理创建的对象
                currentFragment = fragmentClass.newInstance();
                //添加到FragmentManager中
                transaction.add(containId, currentFragment, fragmentName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (isHidden) {
            //隐藏上一个Fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);
            //显示当前Fragment
            transaction.show(currentFragment);
        } else {
            //替换上一个Fragment
            transaction.replace(containId, currentFragment, fragmentName);
        }
        //传递参数
        if (bundle != null) {
            currentFragment.setBundle(bundle);
        }

        if (isBack) {
            transaction.addToBackStack(fragmentName);
        }

        transaction.commit();
        lastFragment = currentFragment;
        return lastFragment;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myHandler.removeCallbacks(myRunnable);
                if (ClickUtils.isFastDoubleClick()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                startAD();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void startAD() {
        myHandler.removeCallbacks(myRunnable);
        myHandler.postDelayed(myRunnable, 20000);
    }
}
