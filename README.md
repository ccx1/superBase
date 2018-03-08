## superBase
一个开发初期所需要使用的工具类集合

### android

   ##主要是针对了几个特殊的地方.内部做了application的activity的监听,SuperBaseApplication,就可以将activity做成监听,这个activity的管理器还做了可以直接关闭所有的activity的操作,


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

   12.里面的baseActivity需要toolbar的android:paddingTop="@dimen/toolbar_padding_top"属性设置为在values-19下的dimens里面设置<dimen name="toolbar_padding_top">25dp</dimen>在默认下面设置为    <dimen name="toolbar_padding_top">0dp</dimen>

   附上toolbar代码

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:background="@color/colorPrimary"
        android:layout_width="match_parent"
        android:minHeight="?attr/actionBarSize"
        android:layout_height="wrap_content"
        android:paddingTop="@dimen/toolbar_padding_top"
        app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        tools:showIn="@layout/activity_base">

    </android.support.v7.widget.Toolbar>


   13. 增加读取pdf的jar包,能够读取到pdf的内容并且以String的形式进行写入.也就是成功将其转成txt文本格式,具体方式在fileutil里面实现方式是,在assets目录下放一个pdf文件即可

    String pdfContent = FileUtils.readPdfContent2String(MainActivity.this, "fileName.pdf");

   将这个String返回结果进行写入文件即可,另外推荐一种直接显示pdf的开源框架


   14. 对networkstateview进行优化,在superBaseActivity里面进行了regist的操作,增加根据网络进行判断.并且根据网络的情况进行页面切换,增加了有无线没网络的印象情况,但是这种情况需要请求网络才能进行验证,所以这个返回值暂时没有做实现,也就是netutil.nonetwork没有进行操作,可以将其放在网络请求方法中进行显示shownetwork

    mNetworkStateView = (NetworkStateView) $(R.id.nwsv);
    registerNetWorkStateView(mNetworkStateView);

   这里另外附上netstateview的方法介绍

      <com.example.administrator.viewutilslist.view.NetworkStateView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/nwsv"
            app:emptyView="@layout/view_empty" //当出现没有数据的时候显示的界面,用户可以自己命名
            app:errorView="@layout/view_network_error" //出现错误的界面
            app:loadingView="@layout/view_loading" //加载中的界面
            app:noNetworkView="@layout/view_no_network" //没有网络的界面
            >

      </com.example.administrator.viewutilslist.view.NetworkStateView>


   可以通过include标签进行导入


    <include layout="@layout/view_network"/>


   15. 增加了Receiver的操作.在superbaseActivity里面进行了注册.直接进行使用几个网络辨析方法

   16. 增加了对64K方法文件的处理.在superBaseApplication里面进行处理

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    
    
   17. 在demo的dagger分支中里面加入了dagger的module注入,并且发现了一篇挺好的dagger2的文章,写的非常好(我一直在想只有我一人觉得dagger有点难用吗)
   
   [dagger2的框架注入](https://www.jianshu.com/p/47c7306b2994)
   

### 后续大概会继续更新左侧导航类,自定义控件使用,工具类的完善,以及几个管理类别的处理,以及RXJAVA的处理方式等等

#### 后续完善

   1. socket.io以及websocket的框架搭建.
   2. rxjava的框架搭建
   3. 自定义控件的完善

#### 人需要做笔记

##### application启动顺序

    Application-> attachBaseContext ();

    ContentProvider:onCreate()

    Application:onCreate()
    
    
##### 小bug记录,下次修复
    
   1. networkstateview的子view处理方法问题.
   2. 工具类的方法冗余
   3. baseActivity的封装过于繁多.需要修复多次继承的问题

#### mark点

1. 原先想写流式布局,发现这里有个更好的,暂时先记录坐标

   [比较喜欢的一个流式布局](https://github.com/hongyangAndroid/FlowLayout)

   [一个比较好的indexRecycleView](https://github.com/YoKeyword/IndexableRecyclerView)

   [pdfView开源框架](https://github.com/JoanZapata/android-pdfview)


## 这个项目大致先这样吧,未完待续...

