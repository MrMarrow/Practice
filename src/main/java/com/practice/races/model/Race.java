package com.practice.races.model;

import static com.practice.races.RacesGameConstant.RACE_HEIGHT;
import static com.practice.races.RacesGameConstant.RACE_WIDTH;

public class Race extends TrackObject {

    public Race(int number) {
        setImageName("Race.png");
        setNumber(number);
        setWidth(RACE_WIDTH);
        setHeight(RACE_HEIGHT);
        setUserData("race");
    }

    @Override
    public void setPosition(int x, int y) {
        setX(x);
        setY(y * getNumber() + 50);
    }
}
