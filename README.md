## android快速开发工具类
### [FResourcesUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FResourcesUtils.java)  获取R资源相关类
  
  FResourcesUtils.getStringResources(context,"title") 获取字符串资源id
  
  ...
### [FToastUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FToastUtils.java)  Toast弹窗，可自定义背景，弹出位置，添加图标等
  
  正常使用
  
  FToastUtils.getInstant(MainActivity.this).showMessage("显示信息");
  
  自定义使用
  
  FToastConf ftc = new FToastConf();
  
  ftc.setTextSize(14);
  
  ...
  
  FToastUtils.getInstant(MainActivity.this).setConf(ftc).showMessage("显示信息");
### [FScreenUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FScreenUtils.java) 获取屏幕分辨率等
 
  FScreenUtils.getScreen(context) 获取屏幕宽高
 
  FScreenUtils.snapShotWithStatusBar(activity) 获取当前屏幕截图，包含状态栏
 
  ...
### [FFileUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FFileUtils.java) 文件操作工具类 创建，删除，移动，写入等文件操作
 
  FFileUtils.mkDir(dirPath) 可创建多个文件夹
 
  FFileUtils.delFile(filepath) 删除文件
 
  FFileUtils.writeText(filePath,content) 写入数据到文件
  ...
### [FAssetsARawUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FAssetsARawUtils.java) assets raw 相关操作类
 
  FAssetsARawUtils.assetsDataToSD(String filePath, String assetsName, Context context) 拷贝assets文件
 
  FAssetsARawUtils.copyRawFileToSdcard(int rawId, Context context, String outPutFileAbs) 拷贝raw文件
 
  。。。
### [FSharedPreferencesUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FSharedPreferencesUtils.java) SharedPreferences数据读写类
 
  FSharedPreferencesUtils.putInt(Context context, String key, int value) 保存int
 
  FSharedPreferencesUtils.getInt(Context context, String name, String key) 取出int
 
  ...
### [FTimeUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FTimeUtils.java) 时间类
 
  FTimeUtils.dateToString(Date date, String format) 日期转字符串
 
  FTimeUtils.yearDiffCurr(String after, String before) 获取指定日期的时间差
 
  ...
### [FLogUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FLogUtils.java) log日志类 可定位到相应代码行，可保存到sd卡，美观的格式化
  
  FLogConf fLogConf = new FLogConf();
  
  fLogConf.setSavesd(true);
  
  FLogUtils.getInstance().setConf(fLogConf).e("logmsg");
 
  或者
 
  FLogUtils.getInstance().e(String msg)
  ...
### [FZipUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrlibs/src/main/java/com/hotapk/fastandrlibs/utils/FZipUtils.java) 文件解压缩类
  
  FZipUtils.unzipFile(final String zipFilePath, final String destDirPath) 解压文件
  
  FZipUtils.zipFile(final String resFilePath,final String zipFilePath) 压缩文件
  
  ...
  
  
