package hello.container;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.yarn.annotation.OnContainerStart;
import org.springframework.yarn.annotation.YarnComponent;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 10/6/16.
 */
@YarnComponent
public class SortingPojo {

    private static final Text EMPTY = new Text();
    private static final Log log = LogFactory.getLog(SortingPojo.class);

    @Autowired
    @Value("${app.input}")
    private String input;
    @Autowired
    @Value("${app.output}")
    private String output;
    @Autowired
    private Configuration configuration;

    protected static class MapperSort extends Mapper<Object, Text, LongWritable, Text> {
        private final LongWritable LONG = new LongWritable();

        @Override
        public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {
            LONG.set(Long.parseLong(value.toString()));
            context.write(LONG, EMPTY);
        }
    }

    protected static class SortReducer extends Reducer<LongWritable, Text, LongWritable, Text> {
        private Configuration conf;

        @Override
        protected void setup(final Context context) {
            conf = context.getConfiguration();
        }

        public void reduce(final LongWritable key, final Iterable<Text> values, final Context context)
                throws IOException, InterruptedException {
            final Counter counter = context.getCounter("limit", "counter");
            if (counter.getValue() <= 100L) {
                context.write(key, EMPTY);
                counter.increment(1);
            }
        }
    }

    @OnContainerStart
    public void publicVoidNoArgsMethod() throws Exception {
        log.info("ArraySorting has began..." + input + "; " + output);
        final Job job = Job.getInstance(configuration, "sorting");
        job.setJarByClass(SortingPojo.class);
        job.setMapperClass(MapperSort.class);
        job.setCombinerClass(SortReducer.class);
        job.setReducerClass(SortReducer.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);
        //job.setNumReduceTasks(1);
        job.setSortComparatorClass(LongWritable.DecreasingComparator.class);

        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        log.info("ArraySorting finished. Is completed: " + job.waitForCompletion(true));
    }

}
