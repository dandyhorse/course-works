package com.epam.hadoop.hw4;

import com.epam.hadoop.hw4.mapreduce.Map;
import com.epam.hadoop.hw4.mapreduce.utils.OSPartitioner;
import com.epam.hadoop.hw4.mapreduce.Reduce;
import com.epam.hadoop.hw4.mapreduce.utils.CustomGroupComparartor;
import com.epam.hadoop.hw4.mapreduce.utils.CustomKey;
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

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJobName("training");
        job.setJarByClass(Main.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(CustomKey.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setPartitionerClass(OSPartitioner.class);

        FileInputFormat.setInputDirRecursive(job, true);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setGroupingComparatorClass(CustomGroupComparartor.class);
        job.addCacheFile(new URI("src/main/resources/city.en.txt"));
        return job.waitForCompletion(true) ? 1 : 0;
    }

    private static final String baseUrl = "hdfs://sandbox.hortonworks.com";

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println(" Usage: Main < input path > < output path >");
            System.exit(-1);
        }
        Configuration conf = new Configuration();
        conf.set("fs.default.name", baseUrl);
        int result = ToolRunner.run(conf, new Main(), args);
        System.exit(result);
    }
}
