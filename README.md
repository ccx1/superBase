## ViewUtilsList
一个开发初期所需要使用的工具类集合

### android

### maven

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

### build.gradle

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



### 内部工具类的介绍.
   1.ThreadUtil,主要封装了线程池,对线程的统一管理吧,是两个方法 runOnSubThread(Runnable runnable) 以及一个 runOnUiThread(Runnable runnable) 来对线程之间子线程和主线程的切换 
   2.ImageUtils 主要介绍几个方法,一个是将bitmap转换成String值进行传递,还有一个是将String转换成Bitmap值进行传递的.
   3.MD5 MD5加密工具类,主要是复杂的代码.写起来有点麻烦,抄了一个工具类进行,直接 MD5.md5("") 直接进行加密
   
   
  	public static String md5(String str)
   
   
   4.Logutils 主要是一个日志的管理类,一键管理,用一个isdebug的参数来进行管理,如果不使用了,将这个值设置为false即可
   
    public static        boolean isDebug = true;
   
   5.GsonUtils 一个gson的转换类,可以转换成javabean.集合之类的,需要gson的依赖才能使用
   6.GetIpUtils 这是一个会自动获取外网ip的工具类,内部有两个方法, GetNetIp() 和 getIPAddress(Context context) 不过基本上用 GetNetIp() 即可,另一个我正在考虑怎么处理.

   7.增加了几个工具类:
        AES加密工具类.
        Base64加密工具类.
        HexUtils.
        maputils.
        RSAUtils.
        SHA1Utils.
        以及一个常规的String工具类

   8.项目初始化做了一些调整,在Application中进行统一初始化,并增加了Utils的总工具类使用.内部有方法重载这样的措施,Utils里面带有文件处理方法以及数据返回的方法

    Utils.init(this);

   9.增加了jxl的excel的解析工具包,通过workbook进行处理.

   10.增加了一个webUtil工具类.使用URL进行处理

   11.增加了SharedPreferences的处理工具类(需要完善..目前只有String处理以及boolean处理,添加方法改为异步处理)

   12.原先想写流式布局,发现这里有个更好的,暂时先记录坐标

   <https://github.com/hongyangAndroid/FlowLayout>

### 后续大概会继续更新右侧导航类,自定义控件使用,工具类的完善,以及几个管理类别的处理


## 这个项目大致先这样吧,未完待续...

