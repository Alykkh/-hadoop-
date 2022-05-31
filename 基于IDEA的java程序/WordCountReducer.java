package com.atguigu.mapreduce.wordcount2;


/*
 * KEYIN , reduce阶段输入的key的类型。Text
 * VALUEIN, reduce阶段该行的value内容 用IntWritable进行描述
 *  KEYOUT,reduce阶段输出的key类型，Text
 *  VALUEOUT。reduce阶段输出的value类型，IntWritable
 *
 * */
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer <Text, IntWritable, Text, IntWritable> {
    //先敲一个reduce，会出现一长串提示，不要犹豫，敲下去！然后你光针位置会有一串背景色代码，再敲回车 它就没了。这是我们想要的。
    IntWritable outv = new IntWritable();//只创建一次对象


    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
      int sum=0;
       //atguigui,(1,1)
        //ss,(1,1)
        for (IntWritable value : values) {
            sum+=value.get();

        }

        outv.set(sum);

        //写出
        context.write(key,outv);
    }
}
