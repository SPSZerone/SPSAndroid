package sps.android.helper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import sps.android.util.Log;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



/**
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class NetworkHelper
{
	/**
	 * NetworkHelper 日志标签
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String LOG_TAG = "+++ NetworkHelper +++";
	
	
	/**
	 * 获取当前活动的网络信息
	 * @version added in [ 1, 1.0.0 ]
	 * @param context
	 */
	public static NetworkInfo get_active_network_infor( Context context )
	{
		if ( context == null )
		{
			return null;
		}
		
		ConnectivityManager connectManager = ( ConnectivityManager )( context.getSystemService( Context.CONNECTIVITY_SERVICE ) );
		if ( connectManager == null )
		{
			return null;
		}		
		
		return connectManager.getActiveNetworkInfo();
	}
	
	
	/**
	 * 当前活动的网络是否已连接到网络
	 * @version added in [ 1, 1.0.0 ]
	 * @return true: 已连接到网络
	 */
	public static boolean is_connected_of_active_network( Context context )
	{
		if ( context == null )
		{
			return false;
		}
		
		
		NetworkInfo networkInfo = get_active_network_infor( context );
		if ( networkInfo == null )
		{
			Log.e( LOG_TAG, "is_active_network_connected > NetworkInfo is null" );
			return false;
		}
		
		
		if ( networkInfo.isConnected() == false )
		{
			Log.d( LOG_TAG, "is_active_network_connected > not connected" );
			return false;
		}
		
		
		if ( networkInfo.getState() == NetworkInfo.State.CONNECTED )
		{
			Log.d( LOG_TAG, "is_active_network_connected > TRUE" );
			return true;
		}

		Log.d( LOG_TAG, "is_active_network_connected > FALSE" );
		
		return false;
	}
	
	
	/**
	 * 获取当前活动网络的类型
	 * @version added in [ 1, 1.0.0 ]
	 * @param context
	 */
	public static int get_active_network_type( Context context )
	{
		NetworkInfo networkInfo = get_active_network_infor( context );
		if ( networkInfo == null )
		{
			return -1;
		}
		return networkInfo.getType();
	}
	
	
	/**
	 * 当前活动的网络是否为WIFI环境
	 * @version added in [ 1, 1.0.0 ]
	 * @param context
	 */
	public static boolean is_wifi_of_active_network_type( Context context )
	{
		return get_active_network_type( context ) == ConnectivityManager.TYPE_WIFI;
	}
	
	
	/**
	 * 当前活动的网络是否为Mobile环境
	 * @version added in [ 1, 1.0.0 ]
	 * @param context
	 */
	public static boolean is_mobile_of_active_network_type( Context context )
	{
		return get_active_network_type( context ) == ConnectivityManager.TYPE_MOBILE;
	}
	
	
	/**
	 * WIFI网络是否已连接到网络
	 * @version added in [ 1, 1.0.0 ]
	 * @param context
	 */
	public static boolean is_wifi_connected( Context context )
	{
		if ( context == null )
		{
			return false;
		}
		
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wiFiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        
        if ( wiFiNetworkInfo == null )
        {  
        	return false;
        }

		if ( wiFiNetworkInfo.isConnected() == false )
		{
			return false;
		}
		
		if ( wiFiNetworkInfo.getState() == NetworkInfo.State.CONNECTED )
		{
			return true;
		}		
		
		return false;
	}
	
	
	/**
	 * Mobile网络是否已连接到网络
	 * @version added in [ 1, 1.0.0 ]
	 * @param context
	 */
	public static boolean is_mobile_connected(Context context)
	{
		if ( context == null )
		{
			return false;
		}
		
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        
        if ( mobileNetworkInfo == null )
        {  
        	return false;
        }

		if ( mobileNetworkInfo.isConnected() == false )
		{
			return false;
		}
		
		if ( mobileNetworkInfo.getState() == NetworkInfo.State.CONNECTED )
		{
			return true;
		}		
		
		return false;
	}
	
	
	/**
	 * 获取当前的IP地址
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static String get_ip_address()
	{
		try
		{
			for ( Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); )
			{
				NetworkInterface intf = en.nextElement();
				
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if ( inetAddress.isLoopbackAddress() == false )
					{
						return inetAddress.getHostAddress();
					}
				} // end for
			} // end for
		}
		catch(SocketException ex)
		{
			
		}
		
		return null;
	}
}
