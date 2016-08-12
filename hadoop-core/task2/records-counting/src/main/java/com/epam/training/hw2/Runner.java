package com.epam.training.hw2;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author Anton_Solovev
 * @since 8/10/2016.
 */
public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);
    private static final String baseUrl = "hdfs://sandbox.hortonworks.com";
    private Long counter;
    private FixedTreeSet iPinSet;

    public Runner() {
        counter = 0L;
        iPinSet = new FixedTreeSet(100, String::compareToIgnoreCase);
    }

    public void run(String[] args) throws IOException {
        Path inputDir = new Path(args[0]);
        Path outputFile = new Path(inputDir + File.separator + args[1]);
        Configuration conf = new Configuration();
        conf.set("fs.default.name", baseUrl);
        FileSystem fs = FileSystem.get(new Configuration());
        FileStatus[] listStatus = fs.listStatus(inputDir);
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        for (FileStatus s : listStatus) {
            Path inputFile = s.getPath();
            CompressionCodec codec = factory.getCodec(inputFile);
            streamingInput(codec, fs, inputFile);
        }
        streamingOutput(outputFile, fs);
    }

    private void streamingOutput(Path outputFile, FileSystem fs) throws IOException {
        fs.createNewFile(outputFile);
        FSDataOutputStream out = null;
        try {
            out = fs.create(outputFile);
            writeData(out);
        } finally {
            IOUtils.closeStream(out);
        }
    }

    private void streamingInput(CompressionCodec codec, FileSystem fs, Path inputPath)
            throws IOException {
        CompressionInputStream in = null;
        try {
            in = codec.createInputStream(fs.open(inputPath));
            DataProcessor dataProcessor = new DataProcessor(iPinSet, counter);
            counter = dataProcessor.readAndProcess(in);
            logger.info(" count of amount id : " + counter);
        } finally {
            IOUtils.closeStream(in);
        }
    }

    private void writeData(FSDataOutputStream out) throws IOException {
        out.writeUTF("Count of ipinyou ids: " + counter + "\nTop-100 iPinyou ID");
        for (String s : iPinSet) {
            out.writeUTF('\n' + s);
        }
    }
}
