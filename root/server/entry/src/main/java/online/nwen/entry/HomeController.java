package online.nwen.entry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class HomeController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! API";
    }
}
