package showsoft.autoscreenadaptation;

import android.app.Application;

/**
 * Created by wenkaichuang on 2017/8/11.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //需要传入ui设计给的大小
        new ScreenAdaptation(this, 720,1280).register();
    }
}
