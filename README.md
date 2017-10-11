## ViewUtilsList
一个开发初期所需要使用的工具类集合

### android

	dependencies {
	        compile 'com.github.ci250454344:ViewUtilsList:v1.0.1'
	}
  
  
   ##主要是针对了几个特殊的地方.内部做了application的activity的监听,只用继承ViewUtilsApplication,就可以将activity做成监听,这个activity的管理器还做了可以直接关闭所有的activity的操作,


        /**
         * 对activity的监听,做好统一管理,直接继承或者重写这个方法即可
         */
        registerActivityLifecycleCallbacks(new ActivityLifecycleListener() {
            @Override
            public void onActivityStartedListerner(Activity activity) {
                ActivityManager.getInstance().pushOneActivity(activity);
            }

            @Override
            protected void onActivityDestroyedListerner(Activity activity) {
                ActivityManager.getInstance().popOneActivity(activity);
            }
        });
        
        
   然后new出一个监听的管理器即可.

### 关于OkhttpUtils

   内部的okhttp工具类,主要是做了对json数据的处理,以及一次性转换成javabean,通过json数据的传递,来进行与服务器之间的通讯,这个版本还在思考中,正在努力改进
   主要是在静态代码块里面进行了所有的初始化,然后直接可以进行调用.
   静态代码块是随着类的加载而加载的,只会调用一次,所以跟我们的单例有点类似.
   并且对这个okhttp做了缓存,至于这个缓存的目录.暂时先这个样子吧.已经正在做新的工具类的搭建,应该很快了


    /*
        初始化
     */
    static {
        File file = null;
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(MEDIA_REMOVED) || externalStorageState.equals(MEDIA_UNMOUNTED) || externalStorageState.equals(MEDIA_BAD_REMOVAL) || externalStorageState.equals(MEDIA_CHECKING) || externalStorageState.equals(MEDIA_SHARED)) {
            file = new File(File.separator + "shcache");

        } else {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + "shcache");

        }
        if (!file.exists()) {
            file.mkdirs();
        }

        mOkHttpClient = new OkHttpClient
                .Builder()
                .cache(new Cache(file, 10 * 1024 * 1024))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }

### 内部工具类的介绍.
   1.ThreadUtil,主要封装了线程池,对线程的统一管理吧,是两个方法 runOnSubThread(Runnable runnable) 以及一个 runOnUiThread(Runnable runnable) 来对线程之间子线程和主线程的切换 
   2.ImageUtils 主要介绍几个方法,一个是将bitmap转换成String值进行传递,还有一个是将String转换成Bitmap值进行传递的.
   3.MD5 MD5加密工具类,主要是复杂的代码.写起来有点麻烦,抄了一个工具类进行,直接 MD5.md5("") 直接进行加密
   
   md5(String str)
   
   4.Logutils 主要是一个日志的管理类,一键管理,用一个isdebug的参数来进行管理,如果不使用了,将这个值设置为false即可
   
    public static        boolean isDebug = true;
   
   5.GsonUtils 一个gson的转换类,可以转换成javabean.集合之类的
   6.GetIpUtils 这是一个会自动获取外网ip的工具类,内部有两个方法, GetNetIp() 和 getIPAddress(Context context) 不过基本上用 GetNetIp() 即可,另一个我正在考虑怎么处理.


## 这个项目大致先这样吧,未完待续...

