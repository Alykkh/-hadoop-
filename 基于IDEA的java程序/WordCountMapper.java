package com.atguigu.mapreduce.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
* KEYIN , map阶段输入的key的类型。longWritable
* VALUEIN, 该行的value内容 用Text进行描述
*  KEYOUT,map阶段输出的key类型，Text
*  VALUEOUT。map阶段输出的value类型，IntWritable
*
* */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text outk = new Text();//为什么要把这段带代码放在函数外面，因为这样可以避免map函数被调用很多次，直接提高效率。
    private IntWritable outv=new IntWritable(1);//map阶段不对数据进行聚合 我们只进行分割的粗加工


    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        //自己重写方法.//首先 获取一行
        String line = value.toString();

        //2，进行切割，
        String[] words = line.split(" ");//这里一定注意，里面是有一个空格的，不然结果就是输出1每一个字母出现的次数，就很苦恼。

        //循环写出
           for (String word : words) {
            //封装outk
            outk.set(word);

            //写出
            context.write(outk,outv);
        }
    }
}
