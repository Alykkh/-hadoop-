package com.cstor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ©2019 Mr. Tao. All rights reserved.
 *
 * @author TaoZhongYuan
 * @date 2020/3/17
 */
public class SinaTopCommentNews {

	public static final String filePath = "D:\\top_comment_news.txt";
	public static final String url_prefix = "http://top.news.sina.com.cn/ws/GetTopDataList.php?top_type=day&top_cat=qbpdpl&top_time=";
	public static final String url_suffix = "&&top_show_num=100&top_order=DESC&js_var=comment_all_data";
	public static final String start_date = "2020-01-01";
	public static final String end_date = "2020-03-17";

	public static void main(String[] args) {
		DateIncrease();
	}

	/**
	 * 爬虫主要逻辑
	 * @param url 爬取网页的url
	 */
	public static void doGet(String url) {
		//访问url，获取内容
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//筛选出body元素里面的数据
		String body = document.select("body").html();
		body = body.replace("<strong>", "").replace("</strong>", "").replace("<p>", "").replace("</p>", "").trim();
		//将数据头尾多域字符删除
		String substring = body.substring(23, body.length() - 1);
		//格式化为json格式
		JSONObject jsonObject = JSONObject.parseObject(substring);
		//获取所有新闻数组
		JSONArray jsonArray = jsonObject.getJSONArray("data");

		//循环遍历新闻，将信息写入文件
		for (Object object : jsonArray) {
			JSONObject news = (JSONObject) object;
			String title = news.getString("title");
			String createDate = news.getString("create_date");
			SinaTopCommentNews.writeFile(createDate + "\t" + title + "\n");
		}

	}

	/**
	 * 写入文件
	 * @param content 内容
	 */
	public static void writeFile(String content) {
		FileWriter fileWritter = null;
		try{
			File file =new File(filePath);
			if(!file.exists()){
				file.createNewFile();
			}
			//使用true，即进行append file
			fileWritter = new FileWriter(file,true);
			fileWritter.write(content);
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				fileWritter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void DateIncrease() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			//起始日期
			Date startDate = sdf.parse(start_date);
			//结束日期
			Date endDate = sdf.parse(end_date);
			Date tempDate = startDate;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			//循环递增日期
			while (tempDate.getTime() < endDate.getTime()) {
				tempDate = calendar.getTime();
				String date = sdf.format(tempDate);

				date = date.replace("-", "");
				String url = url_prefix + date + url_suffix;
				System.out.println("doGet:" + url);
				SinaTopCommentNews.doGet(url);
				//天数+1
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
