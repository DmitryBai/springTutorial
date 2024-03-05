package core.coach.impl;

import core.coach.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoachConfig {

    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
