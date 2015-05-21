package sps.android.util;



/**
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class Log
{
	/**
	 * Log开关
	 * @version added in [ 1, 1.0.0 ]
	 *
	 */
	private static boolean DEBUG_ENABLE = true;

	/**
	 * 设置Log开关
	 * @version added in [ 1, 1.0.0 ]
	 * @param isEnable true:启用，false:禁用
	 *
	 */
	public static void set_debug( boolean isEnable )
	{
		DEBUG_ENABLE = isEnable; 
	}

	/**
	 * Verbose Log
	 * @version added in [ 1, 1.0.0 ]
	 * @param strTag 日志标签
	 * @param strContent 日志内容
	 *
	 */
	public static int v( String strTag, String strContent )
	{
		if ( DEBUG_ENABLE == false )
		{
			return -1;
		}
		
		return android.util.Log.v( strTag, strContent );
	}

	/**
	 * Debug Log
	 * @version added in [ 1, 1.0.0 ]
	 * @param strTag 日志标签
	 * @param strContent 日志内容
	 *
	 */
	public static int d( String strTag, String strContent )
	{
		if ( DEBUG_ENABLE == false )
		{
			return -1;
		}
		
		return android.util.Log.d( strTag, strContent );
	}

	/**
	 * Infor Log
	 * @version added in [ 1, 1.0.0 ]
	 * @param strTag 日志标签
	 * @param strContent 日志内容
	 *
	 */
	public static int i( String strTag, String strContent )
	{
		if ( DEBUG_ENABLE == false )
		{
			return -1;
		}
		
		return android.util.Log.i( strTag, strContent );
	}

	/**
	 * Warn Log
	 * @version added in [ 1, 1.0.0 ]
	 * @param strTag 日志标签
	 * @param strContent 日志内容
	 *
	 */
	public static int w( String strTag, String strContent )
	{
		if ( DEBUG_ENABLE == false )
		{
			return -1;
		}
		
		return android.util.Log.w( strTag, strContent );
	}

	/**
	 * Error Log
	 * @version added in [ 1, 1.0.0 ]
	 * @param strTag 日志标签
	 * @param strContent 日志内容
	 */
	public static int e( String strTag, String strContent )
	{
		if ( DEBUG_ENABLE == false )
		{
			return -1;
		}
		
		return android.util.Log.e( strTag, strContent );
	}
	
}
