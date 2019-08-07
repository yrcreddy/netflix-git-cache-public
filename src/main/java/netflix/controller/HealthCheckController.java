package netflix.controller;

import java.util.concurrent.atomic.AtomicLong;
import netflix.HealthCheck;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/healthcheck")
    public HealthCheck greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new HealthCheck(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
