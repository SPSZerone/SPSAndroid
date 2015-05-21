package sps.android.security;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * 
 * @author 陈威
 * @version added in [ 1, 1.0.0 ]
 *
 */
public class HashHelper
{
	/**
	 * 哈希值范围（大写）
	 * @version added in [ 1, 1.0.0 ]
	 */
	private static final char[] HEX_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 哈希值范围（小写）
	 * @version added in [ 1, 1.0.0 ]
	 */
	private static final char[] HEX_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	

	/**
	 * 获取文件的MD5值（大写）
	 * @version added in [ 1, 1.0.0 ]
	 * @param file 文件
	 * @return 文件的MD5值（大写）
	 */
	public static String get_file_hash_md5(File file)
	{
		return generate_file_hash(file, get_md5_instance());
	}

	/**
	 * 获取文件的MD5值（大写）
	 * @version added in [ 1, 1.0.0 ]
	 * @param file 文件绝对路径
	 * @return 文件的MD5值（大写）
	 */
	public static String get_file_hash_md5(String file)
	{
		return generate_file_hash(file, get_md5_instance());
	}

	/**
	 * 获取文件的SHA1值（大写）
	 * @version added in [ 1, 1.0.0 ]
	 * @param file 文件
	 * @return 文件的SHA1值（大写）
	 */
	public static String get_file_hash_sha1(File file)
	{
		return generate_file_hash(file, get_sha1_instance());
	}

	/**
	 * 获取文件的SHA1值（大写）
	 * @version added in [ 1, 1.0.0 ]
	 * @param file 文件绝对路径
	 * @return 文件的SHA1值（大写）
	 */
	public static String get_file_hash_sha1(String file)
	{
		return generate_file_hash(file, get_sha1_instance());
	}

	/**
	 * 生成文件的Hash值
	 * @version added in [ 1, 1.0.0 ]
	 * @param file 文件绝对路径
	 * @param msgDigest java.security.MessageDigest实例
	 * @return Hash值，取决于msgDigest的算法类型
	 * @see java.security.MessageDigest
	 */
	private static String generate_file_hash(String file, MessageDigest msgDigest)
	{
		if ( file == null )
		{
			return null;
		}
		
		return generate_file_hash( new File(file), msgDigest );
	}

	/**
	 * 生成文件的Hash值
	 * @version added in [ 1, 1.0.0 ]
	 * @param file 文件
	 * @param msgDigest java.security.MessageDigest实例
	 * @return Hash值，取决于msgDigest的算法类型
	 * @see java.security.MessageDigest
	 */
	private static String generate_file_hash(File file, MessageDigest msgDigest)
	{
		if (msgDigest == null || file == null || file.isFile() == false)
		{
			return null;
		}

		FileInputStream fileInputStream = null;
		boolean isReady = false;
		try
		{
			fileInputStream = new FileInputStream(file);

			final int nBufferSize = 1024;
			byte buffer[] = new byte[nBufferSize];
			int nReadLength = 0;

			while ((nReadLength = fileInputStream.read(buffer, 0, nBufferSize)) != -1)
			{
				msgDigest.update(buffer, 0, nReadLength);
			}

			isReady = true;
		} catch (Exception e)
		{
			isReady = false;
		} finally
		{
			try
			{
				fileInputStream.close();
			} catch (Exception ec)
			{

			}
		}

		if (isReady == false)
		{
			return null;
		}

		return convert_bytes_to_hex_string(msgDigest.digest(), true);
	}


	/**
	 * 获取字节码的MD5值（大写）
	 * @version added in [ 1, 1.0.0 ]
	 * @param datas 字节码
	 * @return 字节码的MD5值（大写）
	 */
	public static String md5_create(byte[] datas)
	{
		if (datas == null || datas.length <= 0)
		{
			return null;
		}

		MessageDigest msgDigest = get_md5_instance();
		if (msgDigest == null)
		{
			return null;
		}

		return convert_bytes_to_hex_string(msgDigest.digest(datas), true);
	}


	/**
	 * 获取字节码的SHA1值（大写）
	 * @version added in [ 1, 1.0.0 ]
	 * @param datas 字节码
	 * @return 字节码的SHA1值（大写）
	 */
	public static String sha1_create(byte[] datas)
	{
		if (datas == null || datas.length <= 0)
		{
			return null;
		}

		MessageDigest msgDigest = get_sha1_instance();
		if (msgDigest == null)
		{
			return null;
		}

		return convert_bytes_to_hex_string(msgDigest.digest(datas), true);
	}


	/**
	 * 将字节码转为16进制
	 * @version added in [ 1, 1.0.0 ]
	 * @param datas 字节码
	 * @param isUpper true：大写，false：小写
	 * @return 字节码的16进制
	 */
	public static String convert_bytes_to_hex_string( byte[] datas, boolean isUpper)
	{
		if ( isUpper )
		{
			return convert_bytes_to_hex_string( HEX_UPPER, datas );
		}

		return convert_bytes_to_hex_string( HEX_LOWER, datas );
	}


	/**
	 * 将字节码转为16进制
	 * @version added in [ 1, 1.0.0 ]
	 * @param hexs 16进制值的范围
	 * @param datas 字节码
	 * @return 字节码的16进制
	 */
	private static String convert_bytes_to_hex_string( char [] hexs, byte[] datas )
	{
		if ( hexs == null || datas == null )
		{
			return null;
		}
		
		char[] hex_result = new char[datas.length << 1];

		int index = 0;
		
		for (byte item : datas)
		{
			hex_result[index++] = hexs[0xF & (item >>> 4)];
			hex_result[index++] = hexs[0xF & item];
		}

		return new String(hex_result);

	}


	/**
	 * 获取java.security.MessageDigest的MD5实例
	 * @version added in [ 1, 1.0.0 ]
	 * @return java.security.MessageDigest的MD5实例
	 * @see java.security.MessageDigest
	 */
	public static MessageDigest get_md5_instance()
	{
		try
		{
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e)
		{
		}
		return null;
	}


	/**
	 * 获取java.security.MessageDigest的SHA1实例
	 * @version added in [ 1, 1.0.0 ]
	 * @return java.security.MessageDigest的SHA1实例
	 * @see java.security.MessageDigest
	 */
	public static MessageDigest get_sha1_instance()
	{
		try
		{
			return MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e)
		{
		}
		return null;
	}

}
