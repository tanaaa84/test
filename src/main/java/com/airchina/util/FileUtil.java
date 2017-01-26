package com.airchina.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * @throws Exception 
	 */
	public static String readFileByPath(String path) throws Exception {
		File file = new File(path);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			long l = 0l;
			StringBuffer sbBuffer = new StringBuffer();
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				if(!tempString.trim().equals("")){
					sbBuffer.append(tempString);
				}
				line++;
			}
			reader.close();
			return sbBuffer.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		StringBuffer sb= new StringBuffer();
		
		sb.append("1");
		sb.append("2");
		sb.append("3");
		sb.append("4");
		sb.append("5");
		sb.append("6");
		String str =  sb.substring(0, sb.length()-1);
		sb.setLength(0);
		
		System.out.println(str);
	}
	
	
	
}
