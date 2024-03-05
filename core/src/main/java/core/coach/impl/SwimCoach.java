package core.coach.impl;

import core.coach.Coach;

public class SwimCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Spend 1hr swimming";
    }
}
