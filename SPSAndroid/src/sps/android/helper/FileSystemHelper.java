package sps.android.helper;

import java.io.File;

import android.os.StatFs;



/**
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class FileSystemHelper
{
	/**
	 * FileSystemHelper 日志标签
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static final String LOG_TAG = "+++ FileSystemHelper +++";
	
	
	/**
	 * 指定路径的可用空间（字节）
	 * @param fullPath
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static long get_available_size( String fullPath )
	{
		if ( fullPath == null || fullPath.length() <= 0 )
		{
			return 0;
		}
		
		StatFs stat = new StatFs( fullPath );
		@SuppressWarnings("deprecation")
		final long blockSize = stat.getBlockSize();
		@SuppressWarnings("deprecation")
		final long availableBlocks = stat.getAvailableBlocks();
		
		return blockSize * availableBlocks;
	}
	
	
	/**
	 * 删除文件
	 * @param strFile
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int delete_file( String strFile )
	{
		if ( strFile == null || strFile.length() <= 0 )
		{
			return -1;
		}
		
		File file = new File( strFile );
		
		if ( file.isDirectory() )
		{
			return 1;
		}
		
		if ( file.exists() == false )
		{
			return 2;
		}
		
		if ( file.delete() )
		{
			return 0;
		}

		return -10;
	}
	

	/**
	 * 删除文件夹
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int delete_directory( String strDirectory )
	{
		return delete_directory( new File( strDirectory ) );
	}
	
	
	/**
	 * 删除文件夹
	 * @version added in [ 1, 1.0.0 ]
	 */
	public static int delete_directory( File fileDirectory )
	{
		if ( fileDirectory == null )
		{
			return -1;
		}
		
		if ( ! fileDirectory.exists() )
		{
			return 1;
		}
				
		if ( ! fileDirectory.isDirectory() )
		{
			//Log.e( LOG_TAG, "delete_directory [ file ] >> " + fileDirectory.getAbsolutePath() );
			fileDirectory.delete();
			return 2;
		}
		
		String[] children = fileDirectory.list();
		for ( int i = 0; i < children.length; i++ )
		{
		    delete_directory( new File( fileDirectory, children[i] ) );
		}

		//Log.e( LOG_TAG, "delete_directory [ dir ] >> " + fileDirectory.getAbsolutePath() );
		fileDirectory.delete();
		
		return 0;
	}
	

	/**
	 * 获取带单位的容量大小<br>
	 * 如：100Bytes, 10.05KB, 10.05MB, 10.05GB, 10.05TB, 10.05PB, 5.05EB,<br><br>
	 * 
	 * 2^63 - 1      9223372036854775807      0x7FFFFFFFFFFFFFFF<br><br>
	 * 
	 * test num<br>
	 * long nFileSize = 0x7FFFFFFFFFFFFFFFL;<br>
	 * long nFileSize = ( 1L << 60 ) + 512 * ( 1L << 50 ); // x.xxEB<br>
	 * long nFileSize = ( 1L << 50 ) + 512 * ( 1L << 40 ); // x.xxPB<br>
	 * long nFileSize = ( 1L << 40 ) + 512 * ( 1L << 30 ); // x.xxTB<br>
	 * long nFileSize = ( 1L << 30 ) + 512 * ( 1L << 20 ); // x.xxGB<br>
	 * long nFileSize = ( 1L << 20 ) + 512 * ( 1L << 10 ); // x.xxMB<br>
	 * long nFileSize = ( 1L << 10 ) + 512; // x.xxKB<br>
	 * long nFileSize = 512; // Bytes<br>
	 * long nFileSize = -512;<br>
	 * long nFileSize = -1e18;<br>
	 * @param nFileSize 字节
	 */
	public static String get_file_size_txt( long nFileSize )
	{	
		final long nKB = 1L << 10;
		final long nMB = 1L << 20;
		final long nGB = 1L << 30;
		final long nTB = 1L << 40;
		final long nPB = 1L << 50;
		final long nEB = 1L << 60;
		//final long nZB = 1L << 70;
		//final long nYB = 1L << 80;


		String strFileSize = "";
		

		if ( nFileSize < 0 )
		{
			strFileSize = "" + nFileSize;
			return strFileSize;
		}
	
		
		// x Bytes
		if ( nFileSize < nKB )
		{
			strFileSize = "" + nFileSize + "Bytes";
			return strFileSize;
		}
	
	
		long nValue = 0;
		double fPercent = 0.0;
	
		String strMeasureOfCapacity = ""; // 容量单位
	
		
		// x.xx KB
		if ( nFileSize < nMB )
		{
			nValue = nFileSize >> 10;
			fPercent = 1.0 * ( nFileSize - nKB * nValue ) / nKB;
			strMeasureOfCapacity = "KB";
		}
		// x.xx MB
		else if ( nFileSize < nGB )
		{
			nValue = nFileSize >> 20;
			fPercent = 1.0 * ( nFileSize - nMB * nValue ) / nMB;
			strMeasureOfCapacity = "MB";
		}
		// x.xx GB
		else if ( nFileSize < nTB )
		{
			nValue = nFileSize >> 30;
			fPercent = 1.0 * ( nFileSize - nGB * nValue ) / nGB;
			strMeasureOfCapacity = "GB";
		}
		// x.xx TB
		else if ( nFileSize < nPB )
		{
			nValue = nFileSize >> 40;
			fPercent = 1.0 * ( nFileSize - nTB * nValue ) / nTB;
			strMeasureOfCapacity = "TB";
		}
		// x.xx PB
		else if ( nFileSize < nEB )
		{
			nValue = nFileSize >> 50;
			fPercent = 1.0 * ( nFileSize - nPB * nValue ) / nPB;
			strMeasureOfCapacity = "PB";
		}
		// x.xx EB
		else
		{
			nValue = nFileSize >> 60;
			fPercent = 1.0 * ( nFileSize - nEB * nValue ) / nEB;
			strMeasureOfCapacity = "EB";
		}
		
	
		if ( fPercent >= 1.0 )
		{
			fPercent = 0.99;
		}
		final int nMantissa = (int)(fPercent * 100);
		
		
		strFileSize = "" + nValue;
		if ( nMantissa < 10 )
		{
			strFileSize += ".0";
		}
		else
		{
			strFileSize += ".";
		}
		strFileSize += nMantissa;
		strFileSize += strMeasureOfCapacity;
		
		
		return strFileSize;
	}

}
