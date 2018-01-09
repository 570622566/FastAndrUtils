![](http://upload-images.jianshu.io/upload_images/2383936-04e12f5855b6771e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 关于
Fastandrutils 是一套整理修改整合的android开发常用的工具类。 

这样可以减少复制粘贴代码，从而减少重复代码，也不用为了一个常用的功能去谷歌百度，让代码更简洁，让开发更高效。

同时希望您的添加完善，让android开发变得更简单。

感兴趣的话，就点赞支持下

[个人博客](http://www.hotapk.cn)

## 使用
1. Gradle 添加

    compile 'cn.hotapk:fastandrutils:1.0.0'

2. androidmanifest.xml 的 application 添加

    android:name="cn.hotapk.fastandrutils.utils.FApplication"
    
    或者
    
     ```
     public class 你的application extends FApplication {
      ...
     }
     ```
     
     或者 在你的application 添加
     
     FUtils.init(this);
     
     ## 类说明
     
     类 | 描述
     --- | ---
     [FActivityLifecycleCallbacks.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FActivityLifecycleCallbacks.java) | activity 生命周期管理
     [FAppUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FAppUtils.java) | app相关信息
     [FApplication.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FApplication.java) | Application
     [FAssetsARawUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FAssetsARawUtils.java) | assets raw 相关操作
     [FCleanUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FCleanUtils.java) | 清理app缓存
     [FClickProxy.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FClickProxy.java) | 防止重复点击
     [FCloseUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FCloseUtils.java) | 关闭数据流
     [FConvertUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FConvertUtils.java) | 数据转换
     [FCrashUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FCrashUtils.java) | app异常退出捕获
     [FDBExprotUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FDBExprotUtils.java) | 数据库导出到sd卡
     [FEncryptUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FEncryptUtils.java) | 加密相关
     [FFileUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FFileUtils.java) | 文件操作工具
     [FHanziToPinyin.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FHanziToPinyin.java) | 汉字转拼音
     [FImageUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FImageUtils.java) | 图片工具
     [FKeyBoardHeightUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FKeyBoardHeightUtils.java) | 获取键盘高度
     [FKeyBoardUI.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FKeyBoardUI.java) | 软键盘上方显示EditText
     [FKeyBoardUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FKeyBoardUtils.java) | 软键盘显示隐藏
     [FLogNetServer.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FLogNetServer.java) | 访问手机log内网webservers
     [FLogUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FLogUtils.java) | 日志操作
     [FNetworkUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FNetworkUtils.java) | 网络相关
     [FPermissionUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FPermissionUtils.java) | 权限申请
     [FPhoneUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FPhoneUtils.java) | 设备相关
     [FResourcesUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FResourcesUtils.java) | 获取资源相关
     [FScreenUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FScreenUtils.java) | 屏幕相关
     [FSharedPreferencesUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FSharedPreferencesUtils.java) | SharedPreferences相关
     [FTimeUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FTimeUtils.java) | 时间操作
     [FToastUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FToastUtils.java) | Toast
     [FUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FUtils.java) | 初始化该框架
     [FValidatorUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FValidatorUtils.java) | 验证相关
     [FZipUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FZipUtils.java) | 文件解压缩类
     [NanoHTTPD.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/NanoHTTPD.java) | android 使用的小型简单的webservers
     


## API

- 类名

[FActivityLifecycleCallbacks.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FActivityLifecycleCallbacks.java)

- 说明

    activity 生命周期管理

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | addActivity | 添加activity|
    | currentActivity | 获取当前activity |
    | removeActivity | 结束activity |
    | finishActivity | 结束指定类名的activity |
    | removeAllActivities | 销毁所有activity |

- 类名

[FAppUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FAppUtils.java)

- 说明 

    app相关信息

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | getAppPackageName | 获取App包名 |
    | getAppName | 获取App名称 |
    | getVerCode | 获取App版本号 |
    | getVerName | 获取App版本名称 |
    | installApk | 安装App |
    | uninstallApk | 卸载App |
    | isServiceRunning | 检测服务是否运行 |
    | stopRunningService | 停止运行服务 |
    | getSign | 获取应用签名 |
    | hexdigest |签名byte数组转32位签名字符串 |

- 类名

[FApplication.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FApplication.java)

- 说明 

Application 

- 方法描述

    无

- 类名

[FAssetsARawUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FAssetsARawUtils.java)

- 说明 

    assets raw 相关操作

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | assetsDataToSD|拷贝assets下的文件到sd卡|
    | getAssetsToString |读取assets文件内容|
    | readAssetsByteArray |读取assets文件 转byte数组|
    | getRawToString |读取raw文件内容|
    | readRawFileToByteArray |读取raw文件 转byte数组|
    | copyRawFileToSdcard |复制raw文件到sd卡|

- 类名

[FCleanUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FCleanUtils.java)

- 说明 

   清理app缓存

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | cleanInternalCache | 清除本应用内部缓存 |
    | cleanDatabases | 清除本应用所有数据库 |
    | cleanDatabaseByName | 按名字清除本应用数据库 |
    | cleanSharedPreference | 清除本应用SharedPreference |
    | cleanFiles | 清除本应用files下的内容 |
    | cleanExternalCache | 清除外部cache下的内容 |
    | cleanCustomCache | 清除自定义路径下的文件 |
    | cleanApplicationData | 清除本应用所有及自定义文件路径的数据 |

- 类名

[FClickProxy.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FClickProxy.java)

- 说明 

   防止重复点击

- 方法描述

   无

- 类名

[FCloseUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FCloseUtils.java)

- 说明 

关闭数据流

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | closeIO|关闭IO|

- 类名

[FConvertUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FConvertUtils.java)

- 说明 

   数据转换

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | string2MD5 | 对字符串md5加密 |
    | encodedMessage | Bytes转 Base64字符串 |
    | decodedMessage | Base64字符串转bytes |
    | px2dip | 将px值转换为dip或dp值 |
    | dip2px | 将dip或dp值转换为px值 |
    | px2sp | 将px值转换为sp值 |
    | sp2px | 将sp值转换为px值 |
    | input2OutputStream | inputStream转outputStream |
    | output2InputStream | outputStream转inputStream |
    | inputStream2Bytes | inputStream转byteArr |
    | bytes2InputStream | byteArr转inputStream |
    | outputStream2Bytes | outputStream转byteArr |
    | bytes2OutputStream | byteArr转outputStream |
    | inputStream2String | inputStream转string按编码 |
    | string2InputStream | string转inputStream按编码 |
    | outputStream2String | outputStream转string按编码 |
    | string2OutputStream | string转outputStream按编码 |
    | obj2Map | 对象转map |
    | map2String | map转字符串 |
    | getFormatSize | 格式化KB MB GB 单位 |

- 类名

[FCrashUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FCrashUtils.java)

- 说明 

   app异常退出捕获

- 方法描述

   无

- 类名

[FDBExprotUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FDBExprotUtils.java)

- 说明 

   数据库导出到sd卡

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | startExportDatabase | 导出数据库 |

- 类名

[FEncryptUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FEncryptUtils.java)

- 说明 

   加密相关

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | encryptMD2ToString | MD2加密 |
    | encryptMD5ToString | MD5加密 |
    | encryptMD5File2String | MD5加密文件 |
    | encryptSHA1ToString | SHA1加密 |
    | encryptSHA224ToString | SHA224加密 |
    | encryptSHA256ToString | SHA256加密 |
    | encryptSHA384ToString | SHA384加密 |
    | encryptSHA512ToString | SHA512加密 |
    | hashTemplate | hash加密模板 |
    | encryptHmacMD5ToString | HmacMD5加密 |
    | encryptHmacSHA1ToString | HmacSHA1加密 |
    | encryptHmacSHA224ToString | HmacSHA224加密 |
    | encryptHmacSHA256ToString | HmacSHA256加密 |
    | encryptHmacSHA384ToString | HmacSHA384加密 |
    | encryptHmacSHA512ToString | HmacSHA512加密 |
    | hmacTemplate | Hmac加密模板 |
    | encryptDES | DES加密 |
    | decryptDES | DES解密 |
    | encrypt3DES | 3DES加密 |
    | decrypt3DES | 3DES解密 |
    | encryptAES | AES加密 |
    | decryptAES | AES解密 |

- 类名

[FFileUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FFileUtils.java)

- 说明 

   文件操作工具

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | getRootDir | 获取根目录 |
    | mkDir | 可创建文件夹 |
    | creatFile | 创建文件 |
    | delFile | 删除文件 |
    | delDir | 删除文件夹 |
    | copyFileTo | 拷贝文件 |
    | moveFileTo | 移动文件 |
    | moveFilesTo | 移动文件目录 |
    | file2byte | 文件转byte数组 |
    | readFile | 文件读取 |
    | inputStreamToFile | InputStream转文件 |
    | file2Inp | 文件转InputStream |
    | writeText | 写入数据到文件 |
    | writeByteArrayToFile | byte数组转文件 |
    | appendText | 文件追加数据 |
    | appendText | 文件追加数据，可追加数据到头部 |
    | getLength | 获取文件大小 |
    | getFileName | 获取文件名 |
    | exists | 判断文件是否存在 |
    | orderByDate | 按文件时间排序 |
    | orderByName | 按文件名称排序 |
    | orderByLength | 按文件大小排序 |
    | filter | 文件筛选 |
    | getFiles | 获取文件列表 |

- 类名

[FHanziToPinyin.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FHanziToPinyin.java)

- 说明 

   汉字转拼音

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | get | 汉字转拼音 |

- 类名

[FImageUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FImageUtils.java)

- 说明 

   图片工具

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | bitmap2Bytes | bitmap转byteArr |
    | bytes2Bitmap |byteArr转bitmap |
    | yuv2Jpeg | yuv转jpeg |
    | bitmap2StrByBase64 | 将Bitmap转换成Base64字符串 |
    | drawable2Bytes | drawable转byteArr |
    | bytes2Drawable | byteArr转drawable |
    | readPictureDegree | 读取图片属性：旋转的角度 |
    | rotaingImageView | 旋转图片 |
    | drawable2Bitmap | drawable转bitmap |
    | bitmap2Drawable | bitmap转drawable |
    | view2Bitmap | view转bitmap |
    | getBitmap | 获取bitmap |
    | scale | 缩放图片 |
    | clip | 裁剪图片 |
    | skew | 倾斜图片 |
    | rotate | 旋转图片 |
    | toRound | 转为圆形图片 |
    | toRoundCorner | 转为圆角图片 |
    | addCornerBorder | 添加圆角边框 |
    | addBorder | 添加边框 |
    | addReflection | 添加倒影 |
    | addTextWatermark | 添加文字水印 |
    | addImageWatermark | 添加图片水印 |
    | toAlpha | 转为alpha位图 |
    | toGray | 转为灰度图片 |
    | fastBlur | 高斯模糊 |
    | binaryzation | 图片二值化 |
    | save | 保存图片 |
    | isImage | 根据文件名判断文件是否为图片 |
    | getImageType | 获取图片类型 |
    | compressByScale | 按缩放压缩 |
    | compressByQuality | 按质量压缩 |
    | compressBySampleSize | 按采样大小压缩 |

- 类名

[FKeyBoardHeightUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FKeyBoardHeightUtils.java)

- 说明 

   获取键盘高度

- 方法描述

   无

- 类名

[FKeyBoardUI.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FKeyBoardUI.java)

- 说明 

   软键盘上方显示EditText

- 方法描述

   无

- 使用方法

**FKeyBoardUI. buildKeyBoardUI(Activity)**

- 类名

[FKeyBoardUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FKeyBoardUtils.java)

- 说明 

   软键盘显示隐藏

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | openKeybord | 打开软键盘 |
    | closeKeybord | 关闭软键盘 |
    | hideInputForce | 隐藏软键盘 |
    | showInput | 打开键盘 |

- 类名

[FLogNetServer.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FLogNetServer.java)

- 说明 

  访问手机log内网webservers

- 方法描述

   无

- 使用

   结合FLogUtils使用

- 类名

[FLogUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FLogUtils.java)

- 说明 

日志操作

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | startLogServer | 启动log的WebServer服务 |
    | stopLogServer | 关闭log的WebServer服务 |
    | e | 打印log |
    | setCrash | 保存app异常退出时的log |
    | debug | 是否开启bebug模式 |
    | saveSD | 是否保存到sd卡 |
    | saveCrash | 是否保存crash信息 |
    | setLogSize | 设置log文件大小 |
    | setlogDir | 设置log文件目录 |

- 类名

[FNetworkUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FNetworkUtils.java)

- 说明 

网络相关

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | openWirelessSettings | 打开网络设置界面 |
    | getActiveNetworkInfo | 获取活动网络信息 |
    | isConnected | 判断网络是否连接 |
    | getNetworkOperatorName | 获取网络运营商名称 |
    | isNetworkAvailable | 判断网络连接是否打开,包括移动数据连接 |
    | isWifi | 检测当前打开的网络类型是否WIFI |
    | is3G | 检测当前打开的网络类型是否3G |
    | is4G | 检测当前打开的网络类型是否4G |
    | isWiFi | 是否打开Wifi |
    | setDataEnabled | 打开或关闭移动数据 |
    | setWifiEnabled | 打开或关闭wifi |
    | getNetworkType | 判断当前是否网络连接 |
    | getUrlParams | 获取URL中参数 并返回Map |
    | getIPAddress | 获取ip地址 |

- 类名

[FPermissionUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FPermissionUtils.java)

- 说明 

   权限申请

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | onRequestPermissionsResult | 请求权限结果 |
    | getDeniedPermissions | 获取请求权限中需要授权的权限 |
    | hasAlwaysDeniedPermission | 是否彻底拒绝了某项权限 |
    | shouldShowRequestPermissionRationale |  是否有权限需要说明提示|
    | requestPermissions |  请求权限|

- 使用

  该方法 onRequestPermissionsResult() 对应到Activity中的  onRequestPermissionsResult()方法。


- 类名

[FPhoneUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FPhoneUtils.java)

- 说明 

   设备相关

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | isPhone | 判断设备是否是手机 |
    | getIMEI | 获取IMEI码 |
    | getIMSI | 获取IMSI码 |
    | getPhoneType | 获取移动终端类型 |
    | isSimCardReady | 判断sim卡是否准备好 |
    | getSimOperatorName | 获取Sim卡运营商名称 |
    | getSimOperatorName | 获取Sim卡运营商名称 |
    | getPhoneStatus | 获取手机状态信息 |
    | getAllContactInfo | 获取手机联系人 |
    | getAllSMS | 获取手机短信并保存到xml中 |

- 类名

[FResourcesUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FResourcesUtils.java)

- 说明 

   获取资源相关

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | getAnimResources |获取Anim资源 |
    | getMipmapResources |获取Mipmap资源 |
    | getIdResources |获取Id资源 |
    | getDrawableResources |获取Drawable资源 |
    | getColorResources |获取Color资源 |
    | getStringResources |获取String资源 |
    | getLayoutResources |获取Layout资源 |
    | getAttrResources |获取Attr资源 |
    | getStyleResources |获取Style资源 |
    | getStyleable |获取styleable资源 |


- 类名

[FScreenUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FScreenUtils.java)

- 说明 

   屏幕相关

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | getScreen | 获得屏幕宽高pix |
    | getStatusHeight | 获得状态栏的高度pix |
    | getTitleHeight | 获得标题栏的高度pix |
    | snapShotWithStatusBar | 获取当前屏幕截图，包含状态栏 |
    | snapShotWithoutStatusBar | 获取当前屏幕截图，不包含状态栏 |
    | setFullScreen |设置屏幕为全屏 |
    | setLandscape |设置屏幕为横屏 |
    | setPortrait | 设置屏幕为竖屏 |
    | isLandscape | 判断是否横屏 |
    | isPortrait | 判断是否竖屏 |
    | getScreenRotation | 获取屏幕旋转角度 |
    | isScreenLock | 判断是否锁屏 |
    | setSleepDuration | 设置进入休眠时长 |
    | getSleepDuration | 获取进入休眠时长 |
    | isTablet | 判断是否是平板 |

- 类名

[FSharedPreferencesUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FSharedPreferencesUtils.java)

- 说明 

   SharedPreferences

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | putInt | 保存int |
    | putBoolean | 保存Boolean |
    | putFloat | 保存Float  |
    | putLong | 保存Long  |
    | putString | 保存String  |
    | putStringSet | 保存StringSet  |
    | clear | 清除数据 |
    | clearByKey | 清除key中的数据 |

- 类名

[FTimeUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FTimeUtils.java)

- 说明 

   时间操作

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | stringToDate | 字符串转日期类型 |
    | dateToString | 日期转字符串 |
    | getDaysOfMonth | 获取某年某月的天数 |
    | getToday | 获得当前日期 |
    | getToMonth | 获得当前月份 |
    | getToYear | 获得当前年份 |
    | getDay | 返回日期的天 |
    | getYear | 返回日期的年 |
    | getMonth | 返回日期的月份，1-12 |
    | dayDiff | 计算两个日期相差的天数 |
    | yearDiff | 比较两个日期的年差 |
    | getFristDayTime | 获取一天的开始时间 |
    | getLastDayTime | 获取一天的结束时间 |
    | yearDiffCurr | 比较指定日期与当前日期的差 |
    | getFirstWeekdayOfMonth | 获取每月的第一周 |
    | getLastWeekdayOfMonth | 获取每月的最后一周 |
    | getFirstDayOfMonth | 获取本月第一天 |
    | getLastDayOfMonth | 获取本月最后一天 |
    | isDate | 判断日期是否有效,包括闰年的情况 |
    | getAstro | 根据生日获取星座 |


- 类名

[FToastUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FToastUtils.java)

- 说明 

Toast

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | setConf | 配置相关参数 |
    | showMessage | 显示短时间的Toast |
    | showLongMsg | 显示长时间的Toast |

- 使用

 ***FToastUtils. getInstant(). showMessage();***
 ***FToastUtils. getInstant(). setConf(conf).showMessage();***

- 类名

[FUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FUtils.java)

- 说明 

    初始化框架

- 方法描述

   无

- 使用
***FUtils.init(Application)***

- 类名

[FValidatorUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FValidatorUtils.java)

- 说明 

   验证相关

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | isMobileSimple | 验证手机号（简单） |
    | isMobileExact | 验证手机号（精确） |
    | isTel | 验证电话号码 |
    | isIDCard15 | 验证身份证号码15位 |
    | isIDCard18 | 验证身份证号码18位 |
    | isEmail | 验证邮箱 |
    | isURL | 验证URL |
    | isZh | 验证汉字 |
    | isUsername | 验证用户名 |
    | isDate | 验证yyyy-MM-dd格式的日期校验 |
    | isIP | 验证IP地址 |
    | isIP | 验证IP地址 |

- 类名

[FZipUtils.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FZipUtils.java)

- 说明 

   文件解压缩类

- 方法描述

    | 方法 | 描述 |
    | ----|----|
    | zipFile | 压缩文件 |
    | unzipFile | 解压文件 |

- 类名

[NanoHTTPD.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/NanoHTTPD.java)

- 说明 

  android 使用的小型简单的webservers

- 方法描述

   无

- 使用

   参照
   
[FLogNetServer.java](https://github.com/570622566/FastAndrUtils/blob/master/fastandrutils/src/main/java/cn/hotapk/fastandrutils/utils/FLogNetServer.java)