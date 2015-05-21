package sps.android.jni_hub;

import sps.android.helper.FileSystemHelper;

/**
 * 用于FileSystemHelper对应的Native调用
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class FileSystemHelperJniHub
{
	/**
	 * FileSystemHelperJniHub 日志标签
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String LOG_TAG = "+++ FileSystemHelperJniHub +++";
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.FileSystemHelper#get_available_size(java.lang.String)
	 */
	public static long get_available_size( String fullPath )
	{
		return FileSystemHelper.get_available_size( fullPath );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.FileSystemHelper#delete_directory(java.lang.String)
	 */
	public static int delete_directory( String fullPath )
	{
		return FileSystemHelper.delete_directory( fullPath );
	}
}
