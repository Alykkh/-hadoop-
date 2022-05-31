package com.atguigu.mapreduce.wordcount2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    //直接敲一个main，再回车，就出现了完整的main代码

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //1，获取job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);//这里ALT加回车抛出异常。


        //2，设置jar包路径
        job.setJarByClass(WordCountDriver.class);
        //3，关联reduccer，mapper
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //4，设置map输出的KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5，设置最终输出的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6，设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));//动态传送输入文件地址，从main方法里面传入的。
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //7，提交job。
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);


    }


}
