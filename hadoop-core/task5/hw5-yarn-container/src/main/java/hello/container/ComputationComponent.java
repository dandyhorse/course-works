package hello.container;

import org.springframework.yarn.annotation.OnContainerStart;
import org.springframework.yarn.annotation.YarnComponent;

/**
 * @author Anton_Solovev
 * @since 8/17/2016.
 */

@YarnComponent
public class ComputationComponent {

    @OnContainerStart
    public long calculateFactorial(String num) {
        long n = Long.valueOf(num);
        long fact = 1L;
        for (long c = 1; c <= n; c++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fact = fact * c;
        }
        return fact;
    }

}