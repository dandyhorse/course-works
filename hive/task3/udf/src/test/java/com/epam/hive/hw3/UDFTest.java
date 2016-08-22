package com.epam.hive.hw3;

import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardMapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anton_Solovev
 * @since 8/22/2016.
 */
public class UDFTest {

    private static final String BROWSER_TYPE = "Browser (mobile)";
    private static final String UNKNOWN_BROWSER_TYPE = "unknown";
    private static final String USER_AGENT_STRING =
            "Mozilla/5.0 (iPad; CPU OS 5_0_1 like Mac OS X) " +
                    "AppleWebKit/534.46 (KHTML, like Gecko) " +
                    "MQQBrowser/3.3 Mobile/9A405 Safari/7534.48.3";
    private static final String UA_TYPE = "UA Type";

    private StandardMapObjectInspector resultInspector;
    private CustomUserAgentParser udf;

    @Before
    public void setUp() throws Exception {
        udf = new CustomUserAgentParser();
        JavaStringObjectInspector inputString = PrimitiveObjectInspectorFactory.javaStringObjectInspector;
        resultInspector = (StandardMapObjectInspector) udf.initialize(new ObjectInspector[]{inputString});
    }

    @Test
    public void UserAgentParsedTest() throws Exception {
        Object resultObject = udf.evaluate(new GenericUDF.DeferredJavaObject[]{
                new GenericUDF.DeferredJavaObject(USER_AGENT_STRING)
        });
        Map<String, String> map = (Map<String, String>) resultObject;
        assertThat(BROWSER_TYPE, is(resultInspector.getMapValueElement(map, UA_TYPE)));
    }

    @Test
    public void UserAgentUnparsedTest() throws Exception {
        Object resultObject = udf.evaluate(new GenericUDF.DeferredJavaObject[]{
                new GenericUDF.DeferredJavaObject("Murka/9.0 Zabiaka-Mobile")
        });
        Map<String, String> map = (Map<String, String>) resultObject;
        assertThat(UNKNOWN_BROWSER_TYPE, is(resultInspector.getMapValueElement(map, UA_TYPE)));
    }

}