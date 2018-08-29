package online.nwen.entry;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HomeController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! API";
    }
}
