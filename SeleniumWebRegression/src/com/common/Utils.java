package com.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.asprise.util.ocr.OCR;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Utils {

	public Utils() {
	}

	// check the string, if is number return true
	public static boolean isNumeric(String str) {
		
		if ("".equals(str) || str == null)
			return false;
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;

	}

	// add zero to string
	public static String AddZeroToString(String oldstr, int limit) {
		return AddZeroToString(oldstr, limit, true);
	}

	public static String AddZeroToString(String oldstr, int maxlen,
			boolean onbegin) {
		String NewString = "";
		if (oldstr != null && !oldstr.equals("")) {
			String dd_str = oldstr.toString();
			int dd_len = dd_str.length();
			if (dd_len < maxlen) {
				for (int i = 0; i < maxlen - dd_len; i++) {
					if (onbegin)
						dd_str = "0" + dd_str;
					else
						dd_str = dd_str + "0";
				}
			}
			NewString = dd_str;
		}
		return NewString;
	}

	// split string into integer array
	public static Integer[] splitStringToIntArray(String str, String splitcode) {
		String[] stra = str.split(splitcode);
		Integer[] ida = new Integer[stra.length];
		int j = 0;
		for (int i = 0; i < stra.length; i++) {
			if (isNumeric(stra[i])) {
				ida[j++] = Integer.parseInt(stra[i]);
			}
		}
		return ida;
	}

	public static String[] splitStringToStringArray(String str, String splitcode) {
		String[] stra = str.split(splitcode);
		String[] tmp = new String[stra.length];
		int j = 0;
		for (String string : stra) {
			tmp[j++] = string;
		}
		return tmp;
	}

	public static String String2SHA(String strInput) {
		String strOutput = new String("");
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(strInput.getBytes());
			byte b[] = md.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				strOutput += new String(ob);
			}
		} catch (NoSuchAlgorithmException nsae) {
			System.out.println("Generate SHA Error.");
			nsae.printStackTrace();
		}

		return strOutput;
	}

	public static String String2MD5(String strInput) {
		String strOutput = new String("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strInput.getBytes());
			byte b[] = md.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				strOutput += new String(ob);
			}
		} catch (NoSuchAlgorithmException nsae) {
			System.out.println("Genrate MD5 Error.");
			nsae.printStackTrace();
		}

		return strOutput;
	}

	public static String getDateTime() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " "
				+ "HH:mm:ss:ms");
		String dtime = tempDate.format(new java.util.Date());
		return dtime;
	}

	public static String getCurrentDate() {
		return getDate(new Date());
	}

	public static String getDate(Date date) {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		String odate = tempDate.format(date);
		return odate;
	}

	public static String getDateFolderName() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		String dtime = tempDate.format(new java.util.Date());
		return dtime;
	}

	public static String getDateTimeFolderName() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " "
				+ "HH:mm:ss:ms");
		String dtime = tempDate.format(new java.util.Date());
		dtime = dtime.replace(":", "-");
		return dtime;
	}

	public static String CurrentDateAddDay(int day) {
		Calendar cc = Calendar.getInstance();
		Date trialTime = new Date();
		cc.setTime(trialTime);
		cc.add(Calendar.DATE, day);
		String ymd = "";
		int yyyy = cc.get(Calendar.YEAR);
		int mm = cc.get(Calendar.MONTH) + 1;
		String mmstr = new Integer(mm).toString();
		mmstr = String.format("%02d", mm);
		int dd = cc.get(Calendar.DATE);
		String ddstr = String.format("%02d", dd);
		ymd = new Integer(yyyy).toString() + "-" + mmstr + "-" + ddstr;
		return ymd;
	}

	public static int DateSubDate(Date date1, Date date2) {
		LocalDate ldate1 = new LocalDate(date1);
		LocalDate ldate2 = new LocalDate(date2);
		int d = Days.daysBetween(ldate2, ldate1).getDays();
		return d;
	}

	public static int DateSubDate(String date1, String date2) {
		Date ddate1 = Utils.formateString2Date(date1);
		Date ddate2 = Utils.formateString2Date(date2);
		int d = DateSubDate(ddate2, ddate1);
		return d;
	}

	public static String CurrentTimeAddMinutes(int minutes) {
		Calendar cc = Calendar.getInstance();
		Date trialTime = new Date();
		cc.setTime(trialTime);
		cc.add(Calendar.MINUTE, minutes);
		String ymd = "";
		int yyyy = cc.get(Calendar.YEAR);
		int mm = cc.get(Calendar.MONTH) + 1;
		String mmstr = new Integer(mm).toString();
		mmstr = String.format("%02d", mm);
		int dd = cc.get(Calendar.DATE);
		String ddstr = String.format("%02d", dd);
		int h = cc.get(Calendar.HOUR_OF_DAY);
		String hstr = String.format("%02d", h);
		int m = cc.get(Calendar.MINUTE);
		String mstr = String.format("%02d", m);
		int s = cc.get(Calendar.SECOND);
		String sstr = String.format("%02d", s);
		ymd = new Integer(yyyy).toString() + "-" + mmstr + "-" + ddstr + " "
				+ hstr + ":" + mstr + ":" + sstr;
		return ymd;
	}

	public static String DateAddDay(Date date, int days) {
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		cc.add(Calendar.DATE, days);
		String ymd = "";
		int yyyy = cc.get(Calendar.YEAR);
		int mm = cc.get(Calendar.MONTH) + 1;
		String mmstr = new Integer(mm).toString();
		mmstr = String.format("%02d", mm);
		int dd = cc.get(Calendar.DATE);
		String ddstr = String.format("%02d", dd);
		ymd = new Integer(yyyy).toString() + "-" + mmstr + "-" + ddstr;
		return ymd;
	}

	public static String getTime() {
		SimpleDateFormat tempDate = new SimpleDateFormat("HH:mm:ss:ms");
		String odate = tempDate.format(new java.util.Date());
		return odate;
	}

	public static String formatDate2String(Date date) {
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		if (cc.get(Calendar.YEAR) == -1) {
			return cc.get(Calendar.HOUR_OF_DAY) + ":" + cc.get(Calendar.MINUTE);
		}
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		String odate = tempDate.format(date);
		return odate;
	}

	public static Date formateString2Date(String str) {
		Date d = new Date();
		if (str.indexOf('.') != -1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			try {
				d = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (str.indexOf('-') != -1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				d = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return d;
	}

	public static String Clob2String(Clob c) {
		StringBuffer sb = new StringBuffer(1024);
		Reader instream = null;
		try {
			instream = c.getCharacterStream();
			char[] buffer = new char[(int) c.length()];
			while (instream.read(buffer) != -1) {
				sb.append(buffer);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (instream != null)
					instream.close();
			} catch (Exception dx) {
				instream = null;
			}
		}
		return sb.toString();
	}

	public static String getLocalIP() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			byte[] ipAddr = addr.getAddress();

			String ipAddrStr = "";
			for (int i = 0; i < ipAddr.length; i++) {
				if (i > 0) {
					ipAddrStr += ".";
				}
				ipAddrStr += ipAddr[i] & 0xFF;
			}
			return ipAddrStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static List<String> getAllIPs() throws SocketException {
		// get all NetWorks all IPs
		List<String> list = new ArrayList<String>();
		Enumeration<NetworkInterface> netInterfaces = null;
		netInterfaces = NetworkInterface.getNetworkInterfaces();
		while (netInterfaces.hasMoreElements()) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> ips = ni.getInetAddresses();
			while (ips.hasMoreElements()) {
				list.add(ips.nextElement().getHostAddress());
			}
		}
		return list;
	}

	public static String getExtFromFile(File f) {
		return (f != null) ? getExtFromString(f.getName()) : "";
	}

	public static String getExtFromString(String filename) {
		String extName = "";
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				extName = filename.substring(i + 1);
			}
		}
		return extName;
	}

	public static String getFileName(File f) {
		return (f != null) ? getFileName(f.getName()) : "";
	}

	public static String getFileName(String filename) {
		String prexName = "";
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				prexName = filename.substring(0, i);
			}
		}
		return prexName;
	}

	public static List<String> getListFilter(String filterString) {
		List<String> caseFilter = new ArrayList<String>();
		if (!filterString.equals("")) {
			String[] caseFilters = filterString.split(",");
			for (String f : caseFilters) {
				if (!f.contains("-") && !caseFilter.toString().contains(f))
					caseFilter.add(f);
				else if (f.contains("-")) {
					String[] ft = f.split("-");
					int ft0 = Integer.valueOf(ft[0]);
					int ftN = Integer.valueOf(ft[1]);
					for (int i = ft0; i <= ftN; i++) {
						if (!caseFilter.toString().contains(String.valueOf(i)))
							caseFilter.add(String.valueOf(i));
					}
				}
			}
		}
		return caseFilter;
	}

	public static List<File> searchDirs(File f, Integer level) {
		List<File> listFiles = new ArrayList<File>();
		if (level > 0) {
			File[] list = f.listFiles();
			for (int i = 0; i < list.length; i++) {
				File second = list[i];
				if (second.isDirectory() && !second.isHidden()) {
					listFiles.add(second);
					List<File> tmp = searchDirs(second, level - 1);
					for (File tm : tmp) {
						listFiles.add(tm);
					}
				}
			}
		}

		return listFiles;
	}

	public static boolean RenameFileNameWithSuffix(List<File> files, String suffixName) {
		boolean flag=false;
		try {
			for (File file : files) {
				String prexName = Utils.getFileName(file);
				String extName = Utils.getExtFromFile(file);
				String path = file.getParent();
				File dest = new File(path + File.separator + prexName+ suffixName + "." + extName);
				file.renameTo(dest);
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean RenameFileNameWithSubflag(List<File> files) {
		boolean flag=false;
		try {
			for (File file : files) {
				String[] names = file.getName().split("_");
				String newName="";
				if(file.getName().contains("post")) 
				{
					for(int i=0;i<names.length;i++)
					{
						if(i==0) newName = names[0];
						if(i==1) 
							newName +="_2";
						if(i!=1 && i!=0) 
							newName+="_"+names[i];
					}
				}else
				{
					for(int i=0;i<names.length;i++)
					{
						if(i==0) newName = names[0];
						if(i==1) 
							newName +="_1";
						if(i!=1 && i!=0) 
							newName+="_"+names[i];
					}
				}
				String path = file.getParent();
				File dest = new File(path + File.separator + newName);
				file.renameTo(dest);
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// search files from the Direction
	// param: directory
	// param: how deep
	public static List<File> searchFilesAndDirs(File f, Integer level) {
		List<File> listFiles = new ArrayList<File>();
		if (f.isFile()) {
			listFiles.add(f);
		} else {
			if (level > 0) {
				File[] list = f.listFiles();
				for (int i = 0; i < list.length; i++) {
					File second = list[i];
					if (second.isDirectory() && !second.isHidden()) {
						listFiles.add(second);
						List<File> tmps = searchFilesAndDirs(second, level - 1);
						for (File tmp : tmps) {
							listFiles.add(tmp);
						}
					} else if (!second.isHidden()) {
						listFiles.add(second);
					}
				}
			}
		}
		return listFiles;
	}

	public static List<File> searchFilesAndDirs(String path, Integer level) {
		List<File> listFiles = new ArrayList<File>();
		File f = new File(path);
		if (f.exists()) {
			listFiles = searchFilesAndDirs(f, level);
		} else {
			System.out.println("The File Path not found.");
		}
		return listFiles;
	}

	public static List<File> searchFiles(File f, Integer level,
			List<String> filters) {
		List<File> listFiles = new ArrayList<File>();
		if (f.isFile()) {
			listFiles.add(f);
		} else {
			if (level > 0) {
				File[] list = f.listFiles();
				for (int i = 0; i < list.length; i++) {
					File second = list[i];
					if (second.isDirectory() && !second.isHidden()) {
						List<File> tmps = searchFiles(second, level - 1,filters);
						for (File tmp : tmps) {
							if (tmp.isFile()) {
								String extfilename = Utils.getExtFromFile(tmp);
								for (String filter : filters) {
									String filterext = filter;
									if ("".equals(filterext)) {
										listFiles.add(tmp);
									} else if (!"".equals(filterext) && extfilename.equals(filterext)) {
										listFiles.add(tmp);
									}
								}
							}
						}
					}
					if (second.isFile() && !second.isHidden()) {
						String extfilename = Utils.getExtFromFile(second);
						for (String filter : filters) {
							String filterext = filter;
							if ("".equals(filterext)) {
								listFiles.add(second);
							}
							if (!"".equals(filterext) && extfilename.equals(filterext)) {
								listFiles.add(second);
							}
						}
					}
				}
			}
		}
		return listFiles;
	}

	public static List<File> searchFiles(String path, Integer level,
			List<String> filter) {
		List<File> listFiles = new ArrayList<File>();
		File f = new File(path);
		if (f.exists()) {
			listFiles = searchFiles(f, level, filter);
		} else {
			System.out.println("The File Path not found.");
		}
		return listFiles;
	}

	public static List<File> searchFiles(String path, Integer level) {
		List<String> filters = new ArrayList<String>();
		filters.add("");
		return searchFiles(path, level, filters);
	}

	public static List<File> searchFiles(File path, Integer level) {
		List<String> filters = new ArrayList<String>();
		filters.add("");
		return searchFiles(path, level, filters);
	}

	public static void delFileOrFolder(String filepath) throws IOException {
		File f = new File(filepath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						delFileOrFolder(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();
				}
			}
		}
	}

	public static Class<?> getGenericClass(Class<?> clazz) {
		return getGenericClass(clazz, 0);
	}

	private static Class<?> getGenericClass(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] genericTypes = ((ParameterizedType) type)
				.getActualTypeArguments();
		if (index < 0 || index >= genericTypes.length)
			throw new IndexOutOfBoundsException("Index " + index
					+ " out of array bounds :" + genericTypes.length);
		return (Class<?>) genericTypes[index];
	}

	public static boolean isNotNull(String str) {
		if (str != null && !"".equals(str))
			return true;
		return false;
	}

	public static Properties getPropertyFrom(String propertyFile) {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(propertyFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;

	}

	public static Properties getPropertyFromFile(File file) {
		Properties props = new Properties();
		try {
			FileReader freader = new FileReader(file);
			props.load(freader);
			freader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static String[] getPropertyValues(String propertyFile, String Key) {
		Properties props = Utils.getPropertyFrom(propertyFile);
		String val_string = (String) props.get(Key);
		String[] vals = Utils.splitStringToStringArray(val_string, ",");
		return vals;
	}

	public static String[] getPropertyValues(String propertyFile, String Key,
			String separator) {
		Properties props = Utils.getPropertyFrom(propertyFile);
		String val_string = (String) props.get(Key);
		String[] vals = Utils.splitStringToStringArray(val_string, separator);
		return vals;
	}

	public static String getPropertyValue(String propertyFile, String Key) {
		Properties props = Utils.getPropertyFrom(propertyFile);
		String val_string = (String) props.get(Key);
		return val_string;
	}

	public static String[] getPropertyValues(File file, String Key) {
		Properties props = Utils.getPropertyFromFile(file);
		String val_string = (String) props.get(Key);
		String[] vals = Utils.splitStringToStringArray(val_string, ",");
		return vals;
	}

	public static String[] getPropertyValues(File file, String Key,
			String separator) {
		Properties props = Utils.getPropertyFromFile(file);
		String val_string = (String) props.get(Key);
		String[] vals = Utils.splitStringToStringArray(val_string, separator);
		return vals;
	}

	public static String getPropertyValue(File file, String Key) {
		Properties props = Utils.getPropertyFromFile(file);
		String val_string = (String) props.get(Key);
		return val_string;
	}

	public static boolean addPropertyValue(File file, String Akey,
			String Avalue) throws Exception {
		boolean flag = false;
		Properties props = Utils.getPropertyFromFile(file);
		Set<Object> keys = props.keySet();
		for (Object key : keys) {
			String tvalue = props.getProperty((String) key);
			props.setProperty((String) key, tvalue);
		}
		props.setProperty(Akey, Avalue);
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream(file);
			props.store(outputStream, "");
			outputStream.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean addPropertyValueList(File file, List<String> Akeys,
			List<String> Avalues) throws Exception {
		boolean flag = false;
		Properties props = Utils.getPropertyFromFile(file);
		Set<Object> keys = props.keySet();
		for (Object key : keys) {
			String tvalue = props.getProperty((String) key);
			props.setProperty((String) key, tvalue);
		}
		for (int i = 0; i < Akeys.size(); i++) {
			props.setProperty(Akeys.get(i), Avalues.get(i));
		}
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream(file);
			props.store(outputStream, "");
			outputStream.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void scpGet(String host, String username, String password,
			String remoteFile, String localDir) throws IOException {
		Connection conn = getOpenedConnection(host, username, password);
		SCPClient client = new SCPClient(conn);
		client.get(remoteFile, localDir);
		conn.close();
		System.out.println("scp [" + remoteFile + "] from " + host + " to "
				+ localDir);
	}

	public static void scpPut(String host, String username, String password,
			String localFile, String remoteFileName, String remoteDir)
			throws IOException {
		Connection conn = getOpenedConnection(host, username, password);
		SCPClient client = new SCPClient(conn);
		client.put(localFile, remoteFileName, remoteDir + "/", "0755");
		conn.close();
		System.out.println(getDateTime() + ": scp [" + localFile + "] to "
				+ host + ":" + remoteDir + "/" + remoteFileName);
	}

	public static Connection getOpenedConnection(String host, String username,
			String password) throws IOException {
		Connection conn = new Connection(host);
		conn.connect(); // make sure the connection is opened
		boolean isAuthenticated = conn.authenticateWithPassword(username,
				password);
		if (isAuthenticated == false) {
			System.out.println("Authentication failed.");
			throw new IOException("Authentication failed.");
		}
		return conn;
	}

	public static void copyFileTo(File source, File dest) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(source));
			outBuff = new BufferedOutputStream(new FileOutputStream(dest));
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} finally {
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}

	}

	public static void copyFileTo(String source, String path,
			String destfilename) {
		try {
			File sfile = new File(source);
			File destfile = new File(path + "/" + destfilename);
			copyFileTo(sfile, destfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFileTo(String source, String dest) {
		try {
			File sfile = new File(source);
			File destfile = new File(dest);
			copyFileTo(sfile, destfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		(new File(targetDir)).mkdirs();
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				File sourceFile = file[i];
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFileTo(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				String dir1 = sourceDir + "/" + file[i].getName();
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	public static void runLocal(String cmd) throws Exception {
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd /C start " + cmd);
		// Process p = rt.exec("cmd /C start "+cmd);
		// InputStream stdout = new StreamGobbler(p.getInputStream());
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(stdout));
		// while (true) {
		// String line = br.readLine();
		// if (line == null)
		// break;
		// }
		// int exitVal = p.waitFor();
		// System.out.println("exitVal: "+exitVal);
		// return exitVal;
	}

	public static int runSSH(String host, String username, String password,
			String cmd) throws IOException {
		Connection conn = getOpenedConnection(host, username, password);
		Session sess = conn.openSession();
		sess.execCommand(cmd);
		InputStream stdout = new StreamGobbler(sess.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
		while (true) {
			// attention: do not comment this block, or you will hit
			// NullPointerException
			// when you are trying to read exit status
			String line = br.readLine();
			if (line != null)
				System.out.println(line);
			if (line == null)
				break;
		}
		br.close();
		sess.close();
		conn.close();
		return sess.getExitStatus().intValue();
	}

	public static void AddOrModifyProperties(String[] keys, String[] values,
			File file) throws Exception {
		for (int i = 0; i < keys.length; i++) {
			AddOrModifyProperty(keys[i], values[i], file);
		}
	}

	public static void DeleteProperties(String[] keys, File file)
			throws Exception {
		for (int i = 0; i < keys.length; i++) {
			DeleteProperty(keys[i], file);
		}
	}

	public static void AddOrModifyProperty(String key1, String value1, File file)
			throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader(file));
		Map<String, String> pMap = new HashMap<String, String>();
		Set<Object> keys = prop.keySet();
		for (Iterator<Object> itr = keys.iterator(); itr.hasNext();) {
			String key = (String) itr.next();
			String value = prop.getProperty(key);
			pMap.put(key, value);
		}
		prop.clear();
		pMap.put(key1, value1);
		OutputStream out = null;
		out = new FileOutputStream(file);
		prop.putAll(pMap);
		prop.store(out, null);
		out.close();
	}

	public static void DeleteProperty(String key1, File file) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader(file));
		Map<String, String> pMap = new HashMap<String, String>();
		Set<Object> keys = prop.keySet();
		for (Iterator<Object> itr = keys.iterator(); itr.hasNext();) {
			String key = (String) itr.next();
			String value = prop.getProperty(key);
			pMap.put(key, value);
		}
		prop.clear();
		pMap.remove(key1);
		OutputStream out = null;
		out = new FileOutputStream(file);
		prop.putAll(pMap);
		prop.store(out, null);
		out.close();
	}

	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			// 濡傛灉瑙ｅ帇鏂囦欢鍩烘湰鐩綍缁撴瀯涓嶅瓨鍦�鏂板缓
			if (subDirectory == "" && fl.exists() != true) {
				fl.mkdir();
			}
			// 涓昏鍒涘缓瀛愮洰褰�
			else if (subDirectory != "") {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false) {
						subFile.mkdir();
					}
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean unZip(File zipFileName) {
		return unZip(zipFileName.getAbsolutePath(), zipFileName.getParent());
	}

	public static boolean unZip(String zipFileName, String outputDirectory) {
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			Enumeration e = zipFile.entries();
			ZipEntry zipEntry = null;
			createDirectory(outputDirectory, "");
			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				// 判断是否为一个文件夹
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName().trim();
					// 因为后面带有一个/,所有要去掉
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					if (!f.exists()) {
						f.mkdir();
					}
				} else {
					String fileName = zipEntry.getName();
					fileName = fileName.replace('\\', '/');

					// 判断子文件是否带有目录,有创建,没有写文件
					if (fileName.indexOf("/") != -1) {
						createDirectory(outputDirectory, fileName.substring(0,
								fileName.lastIndexOf("/")));
						fileName = fileName
								.substring(fileName.lastIndexOf("/") + 1);
					}

					File f = new File(outputDirectory + File.separator
							+ zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);

					byte[] by = new byte[1024];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					in.close();
					out.close();
				}
			}
			zipFile.close();
			File tmp = new File(zipFileName);
			tmp.delete();
			System.out.println(zipFileName + " uncompress success.");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static boolean compress(String infile, String outfile) {
		FileInputStream instr;
		try {
			instr = new FileInputStream(new File(infile));
			GZIPOutputStream outGZ = new GZIPOutputStream(new FileOutputStream(
					outfile));
			int a;
			while ((a = instr.read()) != -1)
				outGZ.write(a);
			instr.close();
			outGZ.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String readGZipFile(String infile) {
		InputStream instream;
		String content = "";
		try {
			instream = new FileInputStream(infile);
			BufferedReader bufr = new BufferedReader(new InputStreamReader(
					new GZIPInputStream(instream)));
			String tmpstr;
			while ((tmpstr = bufr.readLine()) != null) {
				content += tmpstr;
			}
			bufr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static boolean ListMatchList(List<String> kk, List<String> vv) {
		boolean rr = false;
		for (int i = 0; i < kk.size(); i++) {
			String k = kk.get(i);
			for (int j = 0; j < vv.size(); j++) {
				String v = vv.get(j);
				if (v.equals(k)) {
					vv.remove(j);
					rr = true;
				}
				if (vv.size() > 0)
					rr = false;
			}
		}
		return rr;
	}

	public static String formatSting2Json(String src) {
		String jsonStr = "{";
		if (src != null && !src.equals("")) {
			String[] ssrc = src.split(",");
			for (int i = 0; i < ssrc.length - 1; i++) {
				String[] tmp = ssrc[i].split(":");
				jsonStr += "\"" + tmp[0] + "\"" + ":\"" + tmp[1] + "\",";
			}
			String[] tmp = ssrc[ssrc.length - 1].split(":");
			jsonStr += "\"" + tmp[0] + "\"" + ":\"" + tmp[1] + "\"";
		}
		return jsonStr += "}";
	}

	/*
	 * Convert byte[] to hex
	 * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src byte[] data
	 * 
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode2Hex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode2Str(String bytes) {
		bytes = bytes.toUpperCase();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	public static String HttpGet(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		response.close();
		httpclient.close();
		return content;
	}

	public static String HttpPost(String url, String params,String charset) throws Exception {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		String[] datas = params.split("&");
		for (int i = 0; i < datas.length; i++) {
			String[] kv = datas[i].split("=");
			if(kv.length<2) kv=new String[]{kv[0],""};
			nvps.add(new BasicNameValuePair(kv[0], kv[1]));
		}
		return HttpPost(url, nvps,charset);
	}

	public static String HttpPost(String url, List<NameValuePair> params,String charset)
			throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params,charset));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		response.close();
		httpclient.close();
		return content;
	}
	/**
	 * 设置页面元素值
	 * @param driver
	 * @param dataList
	 * @throws Exception
	 */
	public static void SetElementValue(WebDriver driver,List<String[]> dataList) throws Exception {
		final MyWebDriverWait ww = new MyWebDriverWait(driver,10,1000L);
		for (int i = 0; i < dataList.size(); i++) {
			String[] dl = dataList.get(i);
			String val = dl[2];
			String etag = dl[0];
			String eid = dl[1];
			WebElement element = null;
			switch(etag)
			{
			   case "iframe":
				    List<WebElement>  lfram = driver.findElements(By.tagName("iframe"));
				    for(WebElement we : lfram)
				    {
				    	String frameid = we.getAttribute("id");
				    	String framename = we.getAttribute("name");
				    	if(frameid.equals(eid) || framename.equals(eid))
				    	{
				    		driver.switchTo().frame(we);
				    		break;
				    	}
				    }
				break;
			   case "id":
				    if(eid.equals("vehicleLength")||eid.equals("vehicleWidth")||eid.equals("vehicleHeight"))
				    	eid += " ";
				    ww.until(ExpectedConditions.presenceOfElementLocated(By.id(eid)));
				    element = driver.findElement(By.id(eid));
				    break;
			   case "name":
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.name(eid)));
				   element = driver.findElement(By.name(eid));
				   
				   break;
			   case "class":
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.className(eid)));
				   element = driver.findElement(By.className(eid));
				   
				   break;	   
			   case "xpath":
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(eid)));
				   element = driver.findElement(By.xpath(eid));
				   break;	
			   case "linkText":
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.linkText(eid)));
				   element = driver.findElement(By.linkText(eid));
				   break;
			   case "waitTime":
				   int et = Integer.parseInt(eid);
				   Thread.sleep(et*1000);
				   break;
			   case "waitById":
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.id(eid)));
			       break;
			   case "waitByxpath":
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(eid)));
			       break;
			   case "checkTextByid":
				   Boolean re = ww.until(ExpectedConditions.textToBePresentInElement(By.id(eid), val));
				   if(!re)  
					   Assert.fail(val +" 无法 在 "+eid+"中找到");
				   break;
			   case "checkTextByxpath":
//				   System.out.println(eid+"----"+val);
				   Boolean re1 = ww.until(ExpectedConditions.textToBePresentInElement(By.xpath(eid), val));
				   if(!re1) Assert.fail(val +" 无法 在 "+eid+"中找到");
				   break;
			   case "cssSelector": 
				   ww.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(eid)));
				   element = driver.findElement(By.cssSelector(eid));
				   break;
			   case "waitForInputByid":
				   ww.setEid(eid);
				   ww.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
				        	 Boolean result = false;  
				             try {
				            	 WebElement imgCode = d.findElement(By.id(ww.getEid()));
				            
				 				 imgCode.click();
					             if(imgCode!=null)
					             { 
					            	 if(imgCode.getAttribute("value").length()==4)
					            		 result = true;
					             }       
				             } catch(Exception e){ 
				            	 Assert.fail("waitForInputByid异常:"+ww.getEid());
				             }
				             return result;  
				            }
					});
				   break;
			   case "waitForInputByname":
				   ww.setEid(eid);
				   ww.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
				        	 Boolean result = false;  
				             try {
				            	 WebElement imgCode = d.findElement(By.name(ww.getEid()));
				 				 imgCode.click();
					             if(imgCode!=null)
					             { 
					            	 if(imgCode.getAttribute("value").length()==4)
					            		 result = true;
					             }       
				             } catch(Exception e){ 
				            	 Assert.fail("waitForInputByname异常:"+ww.getEid());
				             }
				             return result;  
				            }
					});
				   break;
			}
			if (element != null) {
				String elementType = element.getTagName();
				if (val.equals("") || val.equals("click")) {
					element.click();
					Thread.sleep(3500);
				} else {
					switch (elementType) {
					case "input":
						if(!val.equals("") && !val.equals("click"))
							try{
						      element.clear();
							}catch(Exception e)
							{
							}
						element.sendKeys(val);
						break;
					case "select":
						new Select(element).selectByVisibleText(val);
						break;
					}
				}
			}
		}
	}
	
	public static List<String[]> getDataFromxlsx(File filename,String sheetname) throws Exception
	{
		return getDataFormxlsx(filename,sheetname,"start","end");
	}
	
	public static List<String[]> getDataFromxlsx(String filename,String sheetname) throws Exception
	{
		File data = new File(filename);
		if(data.isFile())
		   return getDataFormxlsx(data,sheetname,"start","end");
		else 
			return null;
	}
	
	public static List<String[]> getDataFormxlsx(File testdata,String sheetname) throws Exception
	{
		return getDataFormxlsx(testdata,sheetname,"start","end");
	}
	
	public static List<String[]> getDataFormxlsx(File testdata,String sheetname,String startFlag,String endFlag) throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();
		Workbook wb = new XSSFWorkbook(new FileInputStream(testdata));
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetname);
		int rowNum = sheet.getPhysicalNumberOfRows();
		int allColNum = sheet.getRow(0).getPhysicalNumberOfCells();
		int colStart = 0;
		int colEnd = 0;
		for (int i = 0; i < allColNum; i++) {
			XSSFCell tmp = sheet.getRow(0).getCell(i);
			String colTitle = tmp.getStringCellValue();
			if (colTitle.equals(startFlag))
				colStart = tmp.getColumnIndex() + 1;
			if (colTitle.equals(endFlag))
				colEnd = tmp.getColumnIndex() - 1;
		}
		for (int i = 1; i < rowNum; i++) {	
			int colNum = colEnd-colStart+1;
			String[] ccs = new String[colNum];
			for (int j = 0; j <colNum ; j++) {
				XSSFCell cc = sheet.getRow(i).getCell(j+colStart);
				if (cc != null) {
					String cv = cc.getStringCellValue();
					ccs[j] = cv;
				} else {
					ccs[j]="";
				}
			}
			 dataList.add(ccs);
		}
		return dataList;
	}
	
	public Map<String,List<String[]>> getGroupDataFormxlsx(File testdata,String sheetname,String startFlag,String endFlag) throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();
		Map<String,List<String[]>> alldata = new HashMap<String,List<String[]>>();
		
		Workbook wb = new XSSFWorkbook(new FileInputStream(testdata));
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetname);
		int rowNum = sheet.getPhysicalNumberOfRows();
		int allColNum = sheet.getRow(0).getPhysicalNumberOfCells();
		int colStart = 0;
		int colEnd = 0;
		for (int i = 0; i < allColNum; i++) {
			XSSFCell tmp = sheet.getRow(0).getCell(i);
			String colTitle = tmp.getStringCellValue();
			if (colTitle.equals(startFlag))
				colStart = tmp.getColumnIndex() + 1;
			if (colTitle.equals(endFlag))
				colEnd = tmp.getColumnIndex() - 1;
		}
		String tmp = "";
		for (int i = 1; i < rowNum; i++) {	
			int colNum = colEnd-colStart;
			String[] ccs = new String[colNum];
			String caseflag="";

			for (int j = 1; j <=colNum ; j++) {
				XSSFCell caseflagcc = sheet.getRow(i).getCell(1);
				XSSFCell cc = sheet.getRow(i).getCell(j+colStart);
				if (cc != null) {
					caseflag = caseflagcc.getStringCellValue();
					String cv = cc.getStringCellValue();
					ccs[j-1] = cv;
				} else {
					ccs[j-1]="";
				}
			}
			 dataList.add(ccs);
			   if(tmp.equals("")) 
			   {
					tmp = caseflag;
			   }
			   else
			   {
				   if(!tmp.equals(caseflag)) 
					   {
					   	 alldata.put(tmp, dataList);
					     tmp = caseflag;
					     dataList = new ArrayList<String[]>();
					   }else
					   {
						   if(i==rowNum-1)
						   {
							   alldata.put(tmp, dataList); 
							   tmp = caseflag;
							   dataList = new ArrayList<String[]>();
						   }
					   }
			   }
		}
		return alldata;
	}
	public static List<String[]> getDataFormxlsxByCaseName(File testdata,String sheetname,String caseName) throws Exception
	{
		return getDataFormxlsxByCaseName(testdata,sheetname,caseName,"start","end");
	}
	public static List<String[]> getDataFormxlsxByCaseName(File testdata,String sheetname,String caseName,String startFlag,String endFlag) throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();
		Workbook wb = new XSSFWorkbook(new FileInputStream(testdata));
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetname);
		int rowNum = sheet.getPhysicalNumberOfRows();
		int allColNum = sheet.getRow(0).getPhysicalNumberOfCells();
		int colStart = 0;
		int colEnd = 0;
		for (int i = 0; i < allColNum; i++) {
			XSSFCell tmp = sheet.getRow(0).getCell(i);
			String colTitle = tmp.getStringCellValue();
			if (colTitle.equals(startFlag))
				colStart = tmp.getColumnIndex() + 1;
			if (colTitle.equals(endFlag))
				colEnd = tmp.getColumnIndex() - 1;
		}
		for (int i = 1; i < rowNum; i++) {	
			int colNum = colEnd-colStart;
			String[] ccs = new String[colNum];
			for (int j = 1; j <=colNum ; j++) {
				XSSFCell cc = sheet.getRow(i).getCell(j+colStart);
				if (cc != null) {
					cc.setCellType(cc.CELL_TYPE_STRING);//读取数据之前将cell内数据设置为String类型
					String cv = cc.getStringCellValue();		
					ccs[j-1] = cv;
				} else {
					ccs[j-1]="";
				}
			}
			
			XSSFCell cname = sheet.getRow(i).getCell(1);
			if(cname.getStringCellValue().equals(caseName))
			      dataList.add(ccs);
		}
		return dataList;
	}
	
	
	public static Map<String,List<String[]>> getAllDataFormxlsx(File testdata,String sheetname) throws Exception
	{
		return getAllDataFormxlsx(testdata,sheetname,"start","end");
	}
	
	public static Map<String,List<String[]>> getAllDataFormxlsx(File testdata,String sheetname,String startFlag,String endFlag) throws Exception {
		Map<String,List<String[]>> allData = new HashMap<String,List<String[]>>();
		
		Workbook wb = new XSSFWorkbook(new FileInputStream(testdata));
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetname);
		int rowNum = sheet.getPhysicalNumberOfRows();
		int allColNum = sheet.getRow(0).getPhysicalNumberOfCells();
		int colStart = 0;
		int colEnd = 0;
		for (int i = 0; i < allColNum; i++) {
			XSSFCell tmp = sheet.getRow(0).getCell(i);
			String colTitle = tmp.getStringCellValue();
			if (colTitle.equals(startFlag))
				colStart = tmp.getColumnIndex() + 1;
			if (colTitle.equals(endFlag))
				colEnd = tmp.getColumnIndex() - 1;
		}
		
		
		List<String[]> dataList = new ArrayList<String[]>();
		String caseName="";
		for (int i = 1; i < rowNum; i++) {	
			int colNum = colEnd-colStart;
			String[] ccs = new String[colNum];
			for (int j = 1; j <=colNum ; j++) {
				XSSFCell cc = sheet.getRow(i).getCell(j+colStart);
				if (cc != null) {
					String cv = cc.getStringCellValue();
					ccs[j-1] = cv;
				} else {
					ccs[j-1]="";
				}
			}
			
			XSSFCell cname = sheet.getRow(i).getCell(1);
			caseName= cname.getStringCellValue();

			if(caseName.equals((cname.getStringCellValue().toString())))
			{
				 dataList.add(ccs);	 
			}
		 
		}
		allData.put(caseName, dataList);
		return allData;
	}
	
	
	public static void Execute(Properties props,String sql) throws Exception {
		String dburl = props.getProperty("oracle.url");
		String driver = props.getProperty("oracle.driverClass");
		String username = props.getProperty("oracle.user");
		String passwd = props.getProperty("oralce.password");
		Class.forName(driver);
		java.sql.Connection conn = DriverManager.getConnection(dburl,username,passwd);
		 
		Statement st =  conn.createStatement();
		st.executeQuery(sql);
	}
	
	public static String getRecogniseStr(File imageFile) {
		String s = "";
		try {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getTileWidth();
			int height = image.getTileHeight();
			image = image.getSubimage(1, 1, width - 2, height - 2);
			s = new OCR().recognizeEverything(image);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error happened");
		}
		
		if (s != null && s.length() > 0) {
			s = s.toLowerCase();
			s = s.replaceAll("q", "9");
			s = s.replaceAll("o", "0");
			s = s.replaceAll("l", "1");
			s = s.replaceAll("!", "1");
			s = s.replaceAll("b", "8");
			s = s.replaceAll("g", "5");
			s = s.replaceAll(",", "");
		}
		String realStr = "";
		int slen = s.length();
		for(int i=0;i<slen;i++)
		{
			String tmps = s.substring(i,i+1);
			if(Utils.isNumeric(tmps))
				realStr += tmps;
		}
		return realStr;
	}
	
	public static String getImgFromUrl(String urlstr, String savefile)
    {
        int num = urlstr.indexOf('/',8);
        int extnum = urlstr.lastIndexOf('.');
        String u = urlstr.substring(0,num);
        String ext = urlstr.substring(extnum+1,urlstr.length());
        try{
            //long curTime = System.currentTimeMillis();
            //Random random = new Random(100000000);
            //String fileName = String.valueOf(curTime) + "_" + random.nextInt(100000000) + ext;
            // 图片的路径
            //String realPath = savepath;
            
            URL url = new URL(urlstr);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("referer", u);       //通过这个http头的伪装来反盗链
            BufferedImage image = ImageIO.read(connection.getInputStream());
            FileOutputStream fout=new FileOutputStream(savefile);
            if("gif".equals(ext)||"png".equals("png"))
            {
                 ImageIO.write(image, ext, fout);
            }
            ImageIO.write(image, "jpg", fout);
            fout.flush();
            fout.close();
                   
            return savefile;
        }       
        catch(Exception e)
        {
            System.out.print(e.getMessage().toString());
        }
        return "";
    }
	public static void TakePicFromScreen(int x,int y, int width,int height,String absfilename) throws Exception
	{
		Robot robot = new Robot();
		robot.delay(1000);
		Image image = robot.createScreenCapture(new Rectangle(x,y, width,height));
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		ImageIO.write(bi, "jpg", new File(absfilename));
	}
	public static int[] GetOffset(WebDriver driver,String BrowserFlag)
	{
		int[] offxy = new int[2];
		if(BrowserFlag.equals("IE"))
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			long offy = (Long)js.executeScript("return window.screenTop");
			long offx = (Long)js.executeScript("return window.screenLeft");
			offxy[0]=(int)offx;
			offxy[1]=(int)offy;
			return offxy;
		}
		if(BrowserFlag.equals("FF"))
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			long inx = (Long)js.executeScript("return window.innerWidth");
			long sw = (Long)js.executeScript("return window.screen.width");
			int offset_y = 65;
			offxy[0]=(int) (sw-inx);
			offxy[1] = offset_y;
		}
		return offxy;
	}
	
	
	public static void SysLogin(WebDriver driver,String url,String BrowserFlag,File xlsfile,String sheet,String caseName) throws Exception
	{
		driver.get(url);
	    List<String[]> data = Utils.getDataFormxlsxByCaseName(xlsfile, sheet, caseName);
		Utils.SetElementValue(driver, data);
	}
	
	 public static void findToast(WebDriver driver,String text){

		try {
		final WebDriverWait wait = new WebDriverWait(driver,10000);
		Assert.assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'"+ text + "')]"))));
		System.out.println("找到了toast");
		} catch (Exception e) {
			throw new AssertionError("找不到"+text);
		}
	}
	 
	 public static String getCurClassName() {
		// TODO Auto-generated method stub
		String classname=Thread.currentThread().getStackTrace()[1].getClassName();
		int temp=classname.lastIndexOf(".");
		String sname=classname.substring(temp+1);
		return sname;
	}
}
