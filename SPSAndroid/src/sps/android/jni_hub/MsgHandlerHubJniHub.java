package sps.android.jni_hub;

import sps.android.SPSAndroid;
import sps.android.helper.AppHelper;
import android.os.Handler;
import android.os.Message;



/**
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class MsgHandlerHubJniHub
{
	/**
	 * 消息：打开URL
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final int MSG_OPEN_URL = 0;

	/**
	 * 消息：安装APK
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final int MSG_INSTALL_APP = 1;
	

	/**
	 * @version added in [ 1, 1.0.0 ]
	 * @see android.os.Handler
	 */
	private static Handler s_handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch ( msg.what )
			{
			case MSG_OPEN_URL: { AppHelper.open_url( SPSAndroid.get_context(), (String)(msg.obj) ); } break;
			case MSG_INSTALL_APP: { AppHelper.install_app( SPSAndroid.get_context(), (String)(msg.obj) ); } break;
			}
		} // end handleMessage
	}; // end handler
	

	/**
	 * 发送消息：来自Native对Android UI线程的请求
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static void send_msg( Message msg )
	{
		s_handler.sendMessage(msg);
	}
}
