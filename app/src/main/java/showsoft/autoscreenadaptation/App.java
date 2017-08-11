package showsoft.autoscreenadaptation;

import android.app.Application;

/**
 * Created by Caodongyao on 2017/8/4.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        new ScreenAdaptation(this, 720,1280).register();
    }
}
