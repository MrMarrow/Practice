package com.practice.races.model;

import static com.practice.races.RacesGameConstant.START_POSITION_X;
import static com.practice.races.RacesGameConstant.START_POSITION_Y;

public class Track {

    private Bug bug;
    private Race race;
    private int number;

    public Track(int number) {
        this.number = number;
        race = new Race(number);
        bug = new Bug(number);

        race.setPosition(START_POSITION_X, START_POSITION_Y);
        bug.setPosition(START_POSITION_X, START_POSITION_Y);
    }

    public Bug getBug() {
        return bug;
    }

    public Race getRace() {
        return race;
    }
}
