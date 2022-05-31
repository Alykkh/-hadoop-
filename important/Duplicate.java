package com.cstor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ©2019 Mr. Tao. All rights reserved.
 *
 * @author TaoZhongYuan
 * @date 2020/3/17
 */
public class Duplicate {

	public static void main(String[] args) {
		// 需要处理数据的文件位置
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File("D:\\news.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Map<String, String> map = new HashMap<String, String>();
		String readLine = null;
		int i = 0;

		while (true) {
			try {
				if (!((readLine = bufferedReader.readLine()) != null)) break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 每次读取一行数据，与 map 进行比较，如果该行数据 map 中没有，就保存到 map 集合中
			if (!map.containsValue(readLine)) {
				map.put("key" + i, readLine);
				i++;
			}else {
				//打印重复内容
				System.out.println(readLine);
			}
		}

		//去重后的内容
//		for (int j = 0; j < map.size(); j++) {
//			System.out.println(map.get("key" + j));
//		}
	}
}
