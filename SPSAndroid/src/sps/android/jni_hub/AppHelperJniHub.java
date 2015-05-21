package sps.android.jni_hub;

import sps.android.SPSAndroid;
import sps.android.helper.AppHelper;
import sps.android.helper.FileSystemHelper;
import android.os.Message;



/**
 * 用于AppHelper对应的Native调用
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class AppHelperJniHub
{
	/**
	 * AppHelperJniHub 日志标签
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String LOG_TAG = "+++ AppHelperJniHub +++";
	
	
	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.AppHelper#get_version_code(android.content.Context)
	 */
	public static int get_version_code()
	{
		return AppHelper.get_version_code( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.AppHelper#get_version_name(android.content.Context)
	 */
	public static String get_version_name()
	{
		return AppHelper.get_version_name( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.AppHelper#get_uuid(android.content.Context)
	 */
	public static String get_uuid()
	{
		return AppHelper.get_uuid( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.AppHelper#get_app_data_root_path(android.content.Context)
	 */
	public static String get_app_data_root_path()
	{
		return AppHelper.get_app_data_root_path( SPSAndroid.get_context() );
	}
	

	/**
	 * 获取程序内部数据剩余空间（字节）
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.FileSystemHelper#get_available_size(java.lang.String)
	 */
	public static long get_app_data_path_available_size()
	{
		return FileSystemHelper.get_available_size( AppHelper.get_app_data_root_path( SPSAndroid.get_context() ) );
	}
	

	/**
	 * 打开URL请求
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int open_url( String strUrl )
	{
		if ( strUrl == null || strUrl.length() <= 0 )
		{
			android.util.Log.e( LOG_TAG, "open_url >> url is null" );
			return -1;
		}
		
		Message msg = new Message();
		msg.what = MsgHandlerHubJniHub.MSG_OPEN_URL;
		msg.obj = strUrl;
		MsgHandlerHubJniHub.send_msg(msg);
		
		return 0;
	}
	

	/**
	 * 安装APK请求
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int install_app( String strAppFile )
	{
		if ( strAppFile == null || strAppFile.length() <= 0 )
		{
			android.util.Log.e( LOG_TAG, "install_app >> app is null" );
			return -1;
		}
		
		Message msg = new Message();
		msg.what = MsgHandlerHubJniHub.MSG_INSTALL_APP;
		msg.obj = strAppFile;
		MsgHandlerHubJniHub.send_msg(msg);
		
		return 0;
	}
	

	/**
	 * 获取当前资源的版本（APK中）
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int get_resource_version()
	{
		return AppHelper.get_resource_version( SPSAndroid.get_context() );
	}
}
