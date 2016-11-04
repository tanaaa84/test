package com.ibm.ect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchDeviceType {

	public static void main(String[] args) {
		try {

			HashMap<String, String> bList = readvipcard();
			ArrayList<String> aList = readFileByLines();
			long l = 0l;

			for (int i = 0; i < aList.size(); i++) {
				
//				for (int j = 0; j < bList.size(); j++) {
//					if (bList.get(j) != null && !"".equals(bList.get(j)) && bList.get(j).equals(aList.get(i))) {
				
			
//					l = appendMethodA("out.txt",
//							aList.get(i) + "," + bList.get(aList.get(i)), l);
				
					String sstString =	bList.get(aList.get(i));
					
					if(sstString != null){
						l = appendMethodA("out-card.txt",sstString, l);
					}
				    	
				
					
					System.out.println(aList.get(i) + "^&^&^&^&^&^&^");
//					break;
//					}
//				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public static ArrayList<String> readFileByLines() throws Exception {

		String path = "/Users/tanyanbing/Desktop/";

		File file = new File(path + "卡号.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			long l = 0l;

//			HashMap<String, String> hs = new HashMap<String, String>();

//			 HashSet<String> al = new HashSet<String>();
			
			ArrayList<String> al =new ArrayList<String>();

			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);

				if (!tempString.trim().equals("")) {

//					String mobileNo = tempString.split(",")[0];
//					if (vipNo.length() == 9 || vipNo.length() == 12) {
//						if (vipNo.length() == 12) {
//							tempString = vipNo.substring(3) + ","
//									+ tempString.split(",")[1];
//						}

						System.out.println("line " + line + ": " + tempString);
//						hs.put(tempString.split(",")[0], tempString.split(",")[1]);
//						hs.put(tempString, tempString);
						
						al.add(tempString);
						
//					}

					// System.out.println("line " + line + ": " + tempString);
					// // if(!al.contains(tempString)){
					// al.add(tempString);
					// }

				}

				line++;
			}
			reader.close();

			System.out.println("%%%%" + al.size());

			return al;
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

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public static  HashMap<String,String>  readvipcard() throws Exception {

		String path = "/Users/tanyanbing/Desktop/";

		File file = new File(path + "user1111.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			long l = 0l;

//			ArrayList<String> al = new ArrayList<String>();

			// HashSet<String> al = new HashSet<String>();
			 HashMap<String,String> al = new HashMap<String,String>();

			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				
				if(tempString.split(",").length > 2){
				String tString = tempString.split(",")[2];

//				if (!tempString.trim().equals("") && tempString.trim().length()==11) {

//					if (tempString.length() == 12) {
//						tempString = tempString.substring(3);
//					}
				
				if (tString!=null && !tString.equals("")) {

					System.out.println("line " + line + ": " + tempString);
					al.put(tString,tempString);
				}
				}
				// System.out.println("line " + line + ": " + tempString);
				// // if(!al.contains(tempString)){
				// al.add(tempString);
				// }

				line++;
			}
			reader.close();

			System.out.println("%%%%" + al.size());

			return al;
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

	/**
	 * A方法追加文件：使用RandomAccessFile
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            追加的内容
	 * @throws IOException
	 */
	public static long appendMethodA(String fileName, String content, long l)
			throws IOException {
		System.out.println(l);
		RandomAccessFile randomFile = new RandomAccessFile(
				"/Users/tanyanbing/Desktop/" + fileName, "rw");
		try {
			// 打开一个随机访问文件流，按读写方式

			// 文件长度，字节数
			long fileLength = l;
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);

			content += "\r\n";
			byte buffer[] = new byte[2048];
			buffer = content.getBytes();

			randomFile.write(buffer);
			return randomFile.getFilePointer();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			randomFile.close();
		}

		return 0;
	}

}
