package hello.appmaster.controllers;

import com.google.inject.Inject;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.yarn.client.YarnClient;

import javax.inject.Named;
import javax.inject.Qualifier;

/**
 * @author Anton_Solovev
 * @since 8/17/2016.
 */
@Controller
@RequestMapping("/")
public class WebController {

    @Inject
    @Named(value = "computation")
    private YarnClient client;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    private void processForm(@RequestParam("numVal") String num) {
        ApplicationId applicationId = client.submitApplication();
        ApplicationReport report = client.getApplicationReport(applicationId);
        System.err.println(report);
    }

}
