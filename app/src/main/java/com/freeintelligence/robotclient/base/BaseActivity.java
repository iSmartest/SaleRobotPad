package com.freeintelligence.robotclient.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.freeintelligence.robotclient.R;;
import com.freeintelligence.robotclient.ui.activity.FirstVideoActivity;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.utils.StatusBarUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;


/**
 * 所有Activity都继承此Activity
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private static final String TAG = "BaseActivity";
    private BaseFragment lastFragment;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000; //需要自己定义标志

    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;
    private ImageView mBack;
    static Handler myHandler = new Handler();
    protected Context context;
    static Runnable myRunnable = new Runnable(){
        @Override
        public void run() {
            //TODO 30s无操作
            App.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  Intent intent = new Intent(App.activity, FirstVideoActivity.class);
                    App.activity.startActivity(intent);
                    App.activity.finish();
                }
            });
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
        mBack = findViewById(R.id.title_Back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initView();
        loadData();
        myHandler.postDelayed(myRunnable,60000);
    }


    @Override
    protected void onDestroy() {
        myHandler.removeCallbacks(myRunnable);
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void loadData();

    protected abstract void initView();

    /**
     * 显示Toast  String
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }

    /**
     * 现实Toast int
     * @param context
     * @param message
     */
    public static void showToast(Context context, int message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;

            if(String.valueOf(message).equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    toast.show() ;
                }
            }else{
                oldMsg = String.valueOf(message) ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }


    protected String getTitle(String s){
        //获取title
        return getIntent().getExtras().getString(s).toString();
    }
    protected String getJson(String key){
        //获取title
        return getIntent().getExtras().getString(key).toString();
    }



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
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                myHandler.removeCallbacks(myRunnable);
                break;
            case MotionEvent.ACTION_CANCEL:
                myHandler.postDelayed(myRunnable,60000);
                break;
            case MotionEvent.ACTION_UP:
                myHandler.postDelayed(myRunnable,60000);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getAction()){
            case KeyEvent.ACTION_DOWN:
                myHandler.removeCallbacks(myRunnable);
                break;
            case KeyEvent.ACTION_UP:
                myHandler.postDelayed(myRunnable,30000);
                break;
        }
        return super.dispatchKeyEvent(event);
    }
}
