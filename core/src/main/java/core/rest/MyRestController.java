package core.rest;

import core.coach.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @Value("${configuration.property.injection.example}")
    private String propertyExample;

    private final Coach coach;
    private Coach anotherCoach;

    public MyRestController(@Qualifier("swimCoach") Coach coach) {
        this.coach = coach;
    }

    @Autowired
    public void setAnotherCoach(@Qualifier("cricketCoach") Coach coach) {
        this.anotherCoach = coach;
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/getPropertyExample")
    public ResponseEntity<String> getPropertyExample() {
        return ResponseEntity.ok(propertyExample);
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }

    @GetMapping("/testScope")
    public String testBeanScope() {
        return "Comparing beans: coach = anotherCoach is " + (coach == anotherCoach);
    }

}
