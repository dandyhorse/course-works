package com.epam.hive.hw3;

import eu.bitwalker.useragentutils.*;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton_Solovev
 * @since 8/22/2016.
 */
public class CustomUserAgentParser extends GenericUDF {

    private static final String UA_TYPE = "UA Type";
    private static final String UA_NAME = "UA name";
    private static final String UA_VERSION = "UA version";
    private static final String DEVICE = "Device";
    private static final String OS_NAME = "OS name";

    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if (objectInspectors.length != 1) {
            throw new UDFArgumentLengthException("func should has one argument");
        }
        ObjectInspector inspector = objectInspectors[0];
        if (!(inspector instanceof StringObjectInspector)) {
            throw new UDFArgumentTypeException(0, "arg should be String");
        }
        return ObjectInspectorFactory.getStandardMapObjectInspector(
                PrimitiveObjectInspectorFactory.javaStringObjectInspector,
                        PrimitiveObjectInspectorFactory.javaStringObjectInspector);
    }


    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        UserAgent userAgentParser = new UserAgent(deferredObjects[0].get().toString());
        Browser browser = userAgentParser.getBrowser();
        Version browserVer = userAgentParser.getBrowserVersion();
        OperatingSystem os = userAgentParser.getOperatingSystem();
        return getUserAgentParams(browser, browserVer, os);
    }

    private Map<String, String> getUserAgentParams(Browser browser, Version browserVer, OperatingSystem os) {
        HashMap<String, String> map = new HashMap<>();
        if (browser != null) {
            BrowserType type = browser.getBrowserType();
            if (type != null) {
                map.put(UA_TYPE, browser.getBrowserType().getName());
            }
            map.put(UA_NAME, browser.getName());
        }
        if (browserVer != null) {
            map.put(UA_VERSION, browserVer.getVersion());
        }
        if (os != null) {
            map.put(OS_NAME, os.getName());
            if (os.getDeviceType() != null) {
                map.put(DEVICE, os.getDeviceType().getName());
            }
        }
        return map;
    }

    @Override
    public String getDisplayString(String[] strings) {
        return "parseUserAgent(x: String): String[]";
    }

}
