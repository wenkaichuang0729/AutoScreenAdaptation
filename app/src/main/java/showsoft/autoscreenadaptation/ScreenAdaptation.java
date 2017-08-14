package showsoft.autoscreenadaptation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by wenkaichuang on 2017/8/11.
 */

public class ScreenAdaptation {


    private  Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
    private  Application mApplication;
    private  float mWidth = 720;
    private  float mHeight = 1280;


    public ScreenAdaptation(Application application, float width, int height) {
        mApplication = application;
        mWidth = width;
        mHeight = height;

        activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //开启Activity才执行
                resetDensity(activity, mWidth,mHeight);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        };
    }

    /**
     * 注册
     */
    public void register(){
        resetDensity(mApplication, mWidth,mHeight);
        mApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * 注销
     */
    public void unregister(){
        //设置为默认
        mApplication.getResources().getDisplayMetrics().setToDefaults();
        mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * dp适配 getResources().getDisplayMetrics().density
     * sp适配 getResources().getDisplayMetrics().scaledDensity
     * pt适配 getResources().getDisplayMetrics().xdpi
     * @param context
     * @param width ui设计图的宽度
     * @param height ui设计图的高度
     */
    private static void resetDensity(Context context, float width , float height){
        Point point = new Point();
        //获取屏幕的数值
        ((WindowManager)context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        //dp适配 getResources().getDisplayMetrics().density
        context.getResources().getDisplayMetrics().density = point.x/width*2f;
        context.getResources().getDisplayMetrics().density = point.y/height*2f;
        //sp适配 getResources().getDisplayMetrics().scaledDensity
        context.getResources().getDisplayMetrics().scaledDensity = point.x/width*2f;
        context.getResources().getDisplayMetrics().scaledDensity = point.y/height*2f;
    }



}
