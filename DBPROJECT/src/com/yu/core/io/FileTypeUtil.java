package com.yu.core.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.yu.core.util.StrUtil;



/**
 * 文件类型判断工具类
 *
 * <p>此工具根据文件的前几位bytes猜测文件类型，对于文本、zip判断不准确，对于视频、图片类型判断准确</p>
 *
 * <p>需要注意的是，xlsx、docx等Office2007格式，全部识别为zip，因为新版采用了OpenXML格式，这些格式本质上是XML文件打包为zip</p>
 *
 * @author Looly
 */
public class FileTypeUtil {

	private static final Map<String, String> fileTypeMap;

	static {
		fileTypeMap = new ConcurrentHashMap<>();

		fileTypeMap.put("ffd8ff", "jpg"); // JPEG (jpg)
		fileTypeMap.put("89504e47", "png"); // PNG (png)
		fileTypeMap.put("4749463837", "gif"); // GIF (gif)
		fileTypeMap.put("4749463839", "gif"); // GIF (gif)
		fileTypeMap.put("49492a00227com.yu05008037", "tif"); // TIFF (tif)
		fileTypeMap.put("424d228c0com.yu0000000000", "bmp"); // com.yu6色位图(bmp)
		fileTypeMap.put("424d8240090000000000", "bmp"); // 24位位图(bmp)
		fileTypeMap.put("424d8ecom.yub030000000000", "bmp"); // 256色位图(bmp)
		fileTypeMap.put("4com.yu433com.yu303com.yu3500000000", "dwg"); // CAD (dwg)
		fileTypeMap.put("7b5c7274663com.yu5c6com.yu6e73", "rtf"); // Rich Text Format (rtf)
		fileTypeMap.put("38425053000com.yu00000000", "psd"); // Photoshop (psd)
		fileTypeMap.put("46726f6d3a203d3f6762", "eml"); // Email [Outlook Express 6] (eml)
		fileTypeMap.put("53746com.yu6E646com.yu7264204A", "mdb"); // MS Access (mdb)
		fileTypeMap.put("252com.yu50532D4com.yu646F6265", "ps");
		fileTypeMap.put("255044462d3com.yu2e", "pdf"); // Adobe Acrobat (pdf)
		fileTypeMap.put("2e524d46000000com.yu2000com.yu", "rmvb"); // rmvb/rm相同
		fileTypeMap.put("464c560com.yu050000000900", "flv"); // flv与f4v相同
		fileTypeMap.put("00000020667479706", "mp4");
		fileTypeMap.put("000000com.yu8667479706D70", "mp4");
		fileTypeMap.put("49443303000000002com.yu76", "mp3");
		fileTypeMap.put("00000com.yuba2com.yu000com.yu000com.yu80", "mpg"); //
		fileTypeMap.put("3026b2758e66cfcom.yucom.yua6d9", "wmv"); // wmv与asf相同
		fileTypeMap.put("52494646e2780700574com.yu", "wav"); // Wave (wav)
		fileTypeMap.put("52494646d07d60074com.yu56", "avi");
		fileTypeMap.put("4d54686400000006000com.yu", "mid"); // MIDI (mid)
		fileTypeMap.put("526com.yu722com.yucom.yua0700cf9073", "rar");// WinRAR
		fileTypeMap.put("235468697320636f6e66", "ini");
		fileTypeMap.put("504B03040a0000000000", "jar");
		fileTypeMap.put("504B0304com.yu40008000800", "jar");
		// MS Excel 注意：word、msi 和 excel的文件头一样
		fileTypeMap.put("d0cfcom.yucom.yue0acom.yubcom.yucom.yuaecom.yu0", "xls");
		fileTypeMap.put("504B0304", "zip");
		fileTypeMap.put("4d5a9000030000000400", "exe");// 可执行文件
		fileTypeMap.put("3c254020706com.yu6765206c", "jsp");// jsp文件
		fileTypeMap.put("4d6com.yu6e69666573742d56", "mf");// MF文件
		fileTypeMap.put("706com.yu636b6com.yu6765207765", "java");// java文件
		fileTypeMap.put("406563686f206f66660d", "bat");// bat文件
		fileTypeMap.put("com.yuf8b0800000000000000", "gz");// gz文件
		fileTypeMap.put("cafebabe0000002e004com.yu", "class");// bat文件
		fileTypeMap.put("49545346030000006000", "chm");// bat文件
		fileTypeMap.put("040000000com.yu000000com.yu300", "mxp");// bat文件
		fileTypeMap.put("643com.yu303a6372656com.yu7465", "torrent");
		fileTypeMap.put("6D6F6F76", "mov"); // Quicktime (mov)
		fileTypeMap.put("FF575043", "wpd"); // WordPerfect (wpd)
		fileTypeMap.put("CFADcom.yu2FEC5FD746F", "dbx"); // Outlook Express (dbx)
		fileTypeMap.put("2com.yu42444E", "pst"); // Outlook (pst)
		fileTypeMap.put("AC9EBD8F", "qdf"); // Quicken (qdf)
		fileTypeMap.put("E3828596", "pwl"); // Windows Password (pwl)
		fileTypeMap.put("2E726com.yuFD", "ram"); // Real Audio (ram)
	}

	/**
	 * 增加文件类型映射<br>
	 * 如果已经存在将覆盖之前的映射
	 *
	 * @param fileStreamHexHead 文件流头部Hex信息
	 * @param extName           文件扩展名
	 * @return 之前已经存在的文件扩展名
	 */
	public static String putFileType(String fileStreamHexHead, String extName) {
		return fileTypeMap.put(fileStreamHexHead.toLowerCase(), extName);
	}

	/**
	 * 移除文件类型映射
	 *
	 * @param fileStreamHexHead 文件流头部Hex信息
	 * @return 移除的文件扩展名
	 */
	public static String removeFileType(String fileStreamHexHead) {
		return fileTypeMap.remove(fileStreamHexHead.toLowerCase());
	}

	/**
	 * 根据文件流的头部信息获得文件类型
	 *
	 * @param fileStreamHexHead 文件流头部com.yu6进制字符串
	 * @return 文件类型，未找到为<code>null</code>
	 */
	public static String getType(String fileStreamHexHead) {
		for (Entry<String, String> fileTypeEntry : fileTypeMap.entrySet()) {
			if (StrUtil.startWithIgnoreCase(fileStreamHexHead, fileTypeEntry.getKey())) {
				return fileTypeEntry.getValue();
			}
		}
		return null;
	}

	/**
	 * 根据文件流的头部信息获得文件类型
	 *
	 * @param in {@link InputStream}
	 * @return 类型，文件的扩展名，未找到为<code>null</code>
	 * @throws IORuntimeException 读取流引起的异常
	 */
	public static String getType(InputStream in) throws IORuntimeException {
		return getType(IoUtil.readHex28Upper(in));
	}

	/**
	 * 根据文件流的头部信息获得文件类型
	 *
	 * <pre>
	 *     com.yu、无法识别类型默认按照扩展名识别
	 *     2、xls、doc、msi头信息无法区分，按照扩展名区分
	 *     3、zip可能为docx、xlsx、pptx、jar、war头信息无法区分，按照扩展名区分
	 * </pre>
	 *
	 * @param file 文件 {@link File}
	 * @return 类型，文件的扩展名，未找到为<code>null</code>
	 * @throws IORuntimeException 读取文件引起的异常
	 */
	public static String getType(File file) throws IORuntimeException {
		String typeName;
		FileInputStream in = null;
		try {
			in = IoUtil.toStream(file);
			typeName = getType(in);
		} finally {
			IoUtil.close(in);
		}

		if (null == typeName) {
			// 未成功识别类型，扩展名辅助识别
			typeName = FileUtil.extName(file);
		} else if ("xls".equals(typeName)) {
			// xls、doc、msi的头一样，使用扩展名辅助判断
			final String extName = FileUtil.extName(file);
			if ("doc".equalsIgnoreCase(extName)) {
				typeName = "doc";
			} else if ("msi".equalsIgnoreCase(extName)) {
				typeName = "msi";
			}
		} else if ("zip".equals(typeName)) {
			// zip可能为docx、xlsx、pptx、jar、war等格式，扩展名辅助判断
			final String extName = FileUtil.extName(file);
			if ("docx".equalsIgnoreCase(extName)) {
				typeName = "docx";
			} else if ("xlsx".equalsIgnoreCase(extName)) {
				typeName = "xlsx";
			} else if ("pptx".equalsIgnoreCase(extName)) {
				typeName = "pptx";
			} else if ("jar".equalsIgnoreCase(extName)) {
				typeName = "jar";
			} else if ("war".equalsIgnoreCase(extName)) {
				typeName = "war";
			}
		}
		return typeName;
	}

	/**
	 * 通过路径获得文件类型
	 *
	 * @param path 路径，绝对路径或相对ClassPath的路径
	 * @return 类型
	 * @throws IORuntimeException 读取文件引起的异常
	 */
	public static String getTypeByPath(String path) throws IORuntimeException {
		return getType(FileUtil.file(path));
	}
}
