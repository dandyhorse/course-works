package hello.container;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Anton_Solovev
 * @since 8/17/2016.
 */
@RestController
public class ComputationRest {

    @Inject
    private ComputationComponent computationComponent;

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String process(@RequestParam String num) {
        long start = System.currentTimeMillis();
        long fact = computationComponent.calculateFactorial(num);
        long end = System.currentTimeMillis();
        return "Factorial for  " + num + " is " + fact + " Computation time: " + (end - start) + " milis.";
    }

}
