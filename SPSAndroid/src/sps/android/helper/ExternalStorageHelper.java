package sps.android.helper;

import android.os.Environment;



/**
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class ExternalStorageHelper
{
	/**
	 * ExternalStorageHelper 日志标签
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String LOG_TAG = "+++ ExternalStorageHelper +++";
	
	
	/**
	 * 获取默认ExternalStorage根路径（即SD卡根路径）
	 */
	public static String get_root_path()
	{
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	
	/**
	 * 默认ExternalStorage是否已安装（挂载）（即SD卡是否为可用状态）
	 */
	public static boolean is_available()
	{
		if ( Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED ) )
		{
			return true;
		}

		return false;
	}
	
	
	/**
	 * 默认ExternalStorage剩余空间（即SD卡剩余空间）
	 */
	public static long get_available_size()
	{
		return FileSystemHelper.get_available_size(get_root_path());
	}	
}
