package sps.android.jni_hub;

import sps.android.helper.ExternalStorageHelper;
import sps.android.helper.FileSystemHelper;




/**
 * 用于ExternalStorageHelper对应的Native调用
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class ExternalStorageHelperJniHub
{

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.ExternalStorageHelper#is_available()
	 */
	public static boolean is_available()
	{
		return ExternalStorageHelper.is_available();
	}
	


	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.ExternalStorageHelper#get_root_path()
	 */
	public static String get_root_path()
	{
		return ExternalStorageHelper.get_root_path();
	}
	


	/**
	 * SD卡剩余空间
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.FileSystemHelper#get_available_size(java.lang.String)
	 */
	public static long get_available_size()
	{
		return FileSystemHelper.get_available_size( ExternalStorageHelper.get_root_path() );
	}
}
