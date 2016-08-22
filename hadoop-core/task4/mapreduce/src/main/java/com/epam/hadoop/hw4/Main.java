package com.epam.hadoop.hw4;

import com.epam.hadoop.hw4.mapreduce.*;
import com.epam.hadoop.hw4.mapreduce.PriceMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class Main extends Configured implements Tool {
    private static final String baseUrl = "hdfs://sandbox.hortonworks.com";

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJobName("training");
        job.setJarByClass(Main.class);

        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(CustomKey.class);
        job.setMapOutputValueClass(CustomValue.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(PriceMapper.class);
        job.setReducerClass(PriceReducer.class);

        job.setPartitionerClass(OSPartitioner.class);
        job.setGroupingComparatorClass(CustomGroupComparator.class);
        job.setSortComparatorClass(CustomSortComparator.class);
        job.setNumReduceTasks(6);

        FileInputFormat.setInputDirRecursive(job, true);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.addCacheFile(new URI(baseUrl + args[2]));
        job.addCacheFile(new URI(baseUrl + args[3]));
        return job.waitForCompletion(true) ? 1 : 0;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.err.println(" Usage: Main < input path > < output path > < city codes > < regions codes >");
            System.exit(-1);
        }
        Configuration conf = new Configuration();
        conf.set("fs.default.name", baseUrl);

        int result = ToolRunner.run(conf, new Main(), args);
        System.exit(result);
    }

}
