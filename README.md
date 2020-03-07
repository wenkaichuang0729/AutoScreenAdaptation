# AutoScreenAdaptation
</br>安卓屏幕适配  项目直接用dp和sp的可以不用改动   直接加入一句代码即可实现适配
</br>还未确定会不会有兼容性问题和性能问题 需要时间测试
</br>目前测试过模拟器的不同分辨率和不同dpi基本效果不会差太多
</br>旋转屏幕 布局会按横版的大小缩放 视图也不会乱
</br>支持x  y适配
</br>主要代码
```java
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
   //Application回调
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
    
    //使用:
    //直接再Application 初始化即可
    public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //ui给的设计稿的大小
        new ScreenAdaptation(this, 720,1280).register();
      }
    }
```
这里是以720x1280的大小来展示的,假设ui给的是80px    对应的xml里写40dp   也就是1dp=2px

##效果图
</br> 

![图1](https://raw.githubusercontent.com/wenkaichuang0729/AutoScreenAdaptation/master/images/480x800-720x1280.png)
</br>480x800-720x1280.png

![图2](https://raw.githubusercontent.com/wenkaichuang0729/AutoScreenAdaptation/master/images/800x1280-216dip480x854-160dpi.png)
</br>800x1280-216dip(左图)480x854-160dpi(右图).png

该Demo学习过程改造而成
