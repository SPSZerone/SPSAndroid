package sps.android.jni_hub;

import sps.android.SPSAndroid;
import sps.android.helper.NetworkHelper;



/**
 * 用于NetworkHelper对应的Native调用
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class NetworkHelperJniHub
{
	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#is_connected_of_active_network(android.content.Context)
	 */
	public static boolean is_connected_of_active_network()
	{
		return NetworkHelper.is_connected_of_active_network( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#get_active_network_type(android.content.Context)
	 */
	public static int get_active_network_type()
	{
		return NetworkHelper.get_active_network_type( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#is_wifi_of_active_network_type(android.content.Context)
	 */
	public static boolean is_wifi_of_active_network_type()
	{
		return NetworkHelper.is_wifi_of_active_network_type( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#is_mobile_of_active_network_type(android.content.Context)
	 */
	public static boolean is_mobile_of_active_network_type()
	{
		return NetworkHelper.is_mobile_of_active_network_type( SPSAndroid.get_context() );
	}


	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#is_wifi_connected(android.content.Context)
	 */
	public static boolean is_wifi_connected()
	{
		return NetworkHelper.is_wifi_connected( SPSAndroid.get_context() );
	}
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#is_mobile_connected(android.content.Context)
	 */
	public static boolean is_mobile_connected()
	{
		return NetworkHelper.is_mobile_connected( SPSAndroid.get_context() );
	}
	
	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see sps.android.helper.NetworkHelper#get_ip_address()
	 */
	public static String get_ip_address()
	{
		return NetworkHelper.get_ip_address();
	}
}
