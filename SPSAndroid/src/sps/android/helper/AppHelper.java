package sps.android.helper;

import java.io.File;
import java.util.UUID;

import sps.android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;




/**
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class AppHelper
{
	/**
	 * AppHelper 日志标签
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String LOG_TAG = "+++ AppHelper +++";
	

	/**
	 * AppHelper 资源版本meta name
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String resource_version_name = "jyzy_resource_version";
	

	/**
	 * 获取当前程序的包信息
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static PackageInfo get_package_info( Context context )
	{
		if ( context == null )
		{
			return null;
		}
		
        PackageManager packageManager = context.getPackageManager();
        try
		{
			return packageManager.getPackageInfo( context.getPackageName(), 0 );
		}
        catch (NameNotFoundException e)
		{
        	Log.e( LOG_TAG, "get_package_info EXCEPTION" );
		}
        
        return null;
	}


	/**
	 * 获取当前程序的版本号
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int get_version_code( Context context )
	{
        PackageInfo packInfo = get_package_info( context );
        if ( packInfo == null )
        {
        	return 0;
        }

        return packInfo.versionCode;
	}
	


	/**
	 * 获取当前程序的版本名称
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static String get_version_name( Context context )
	{
		PackageInfo packInfo = get_package_info( context );
        if ( packInfo == null )
        {
        	return null;
        }

        return packInfo.versionName;
	}
	


	/**
	 * 获取程序meta data
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static String get_application_meta_data_string( Context context, String strName )
	{
		if ( strName == null || strName.length() <= 0 )
		{
			android.util.Log.e( LOG_TAG, "get_application_meta_data_string >> Name is NULL" );
			return null;
		}
		
		try
		{
			ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo( context.getPackageName(), PackageManager.GET_META_DATA );
			return appInfo.metaData.getString( strName );
		}
		catch ( NameNotFoundException e )
		{
			android.util.Log.e( LOG_TAG, "get_application_meta_data_string >> NameNotFoundException" );
		}
		
		return null;
	}
	


	/**
	 * 获取程序meta data
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int get_application_meta_data_integer( Context context, String strName, int defaultValue )
	{
		if ( strName == null || strName.length() <= 0 )
		{
			android.util.Log.e( LOG_TAG, "get_application_meta_data_integer >> Name is NULL" );
			return -1;
		}
		
		try
		{
			ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo( context.getPackageName(), PackageManager.GET_META_DATA );
			return appInfo.metaData.getInt( strName, defaultValue );
		}
		catch ( NameNotFoundException e )
		{
			android.util.Log.e( LOG_TAG, "get_application_meta_data_integer >> NameNotFoundException" );
		}
		
		return -1;
	}
	

	/**
	 * 获取当前设备的UUID
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static String get_uuid( Context context )
	{
		if ( context == null )
		{
			return null;
		}
		
		
		try
		{
			TelephonyManager telManager = ( TelephonyManager )( context.getSystemService( Context.TELEPHONY_SERVICE ) );
			WifiManager wifiManager = (WifiManager)( context.getSystemService( Context.WIFI_SERVICE ) );
			
			
			final String strDeviceID = ( telManager != null ) ? telManager.getDeviceId() : "";
			final String strSerialNum = ( telManager != null ) ? telManager.getSimSerialNumber() : "";
			final String strAndroidID = android.provider.Settings.Secure.getString( context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID );
	        final String strMacAddress = ( wifiManager != null ) ? wifiManager.getConnectionInfo().getMacAddress() : "";
		
			final long nDeviceID = ( strDeviceID != null ) ? strDeviceID.hashCode() : 0;
			final long nSerialNum = ( strSerialNum != null ) ? strSerialNum.hashCode() : 0;
			final long nAndroidID = ( strAndroidID != null ) ? strAndroidID.hashCode() : 0;
			final long nMacAddress = ( strMacAddress != null ) ? strMacAddress.hashCode() : 0;
		
			
			UUID uuid = new UUID( ( nMacAddress << 32 ) | nAndroidID, ( nDeviceID << 32 ) | nSerialNum );	
			
			return uuid.toString();
		}
		catch (Exception e)
		{
			Log.e( LOG_TAG, "Exception GET UUID" );
		}
		
		
		return null;
	}
	

	/**
	 * 获取程序内部数据根路径
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static String get_app_data_root_path( Context context )
	{		 
		if ( context == null )
		{
			return null;
		}
		
		return File.separator + "data" + File.separator + "data" + File.separator + context.getPackageName();
	}
	

	/**
	 * 安装APK
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int install_app( Context context, String strAppFile )
	{
		if ( context == null )
		{
			return -1;
		}

		if ( strAppFile == null || strAppFile.length() <= 0 )
		{
			return -2;
		}

		Log.e( LOG_TAG, "install_app >> " + strAppFile );
		
		Intent intent = new Intent( Intent.ACTION_VIEW );
		intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		intent.setDataAndType( Uri.parse( "file://" + strAppFile ), "application/vnd.android.package-archive" );
		context.startActivity( intent );
		
		return 0;
	}



	/**
	 * 打开URL
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int open_url( Context context, String strUrl )
	{
		if ( context == null )
		{
			return -1;
		}

		if ( strUrl == null || strUrl.length() <= 0 )
		{
			return -2;
		}

		Log.e( LOG_TAG, "open_url >> " + strUrl );
		
		Uri url = Uri.parse( strUrl );
		Intent intent = new Intent( Intent.ACTION_VIEW, url );
		context.startActivity( intent );
		
		return 0;
	}
	

	/**
	 * 获取程序的版本信息
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static String get_verion_infor( Context context )
	{
		final int nVersionCode = AppHelper.get_version_code(context);
		String strVersionName = AppHelper.get_version_name(context);
		if ( strVersionName == null )
		{
			strVersionName = "unknown";
		}
		
		return "[ " + nVersionCode + ", " + strVersionName + " ]";
	}


	/**
	 * 获取当前资源的版本（APK中）
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int get_resource_version( Context context )
	{
        return get_application_meta_data_integer( context, resource_version_name, 0 );
	}
}
