package com.practice.races.model;

import static com.practice.races.RacesGameConstant.BUG_HEIGHT;
import static com.practice.races.RacesGameConstant.BUG_WIDTH;

public class Bug extends TrackObject {

    private FinisherStatus flagFinisher;
    private int checkMove = -1;
    private String name;


    public Bug(int number) {
        setNumber(number);
        setImageName("SimpleBug.png");
        setWidth(BUG_WIDTH);
        setHeight(BUG_HEIGHT);
        setUserData("bug");
        flagFinisher = FinisherStatus.NOT_STARTED;
        name = "Bug №" + number;
    }

    /**
     * simple move without click
     */
    public void moveX() {
        int newX = (int) (Math.random() * 5);
        setX(getX() + newX);
    }

    /**
     * boost move after click
     */
    public void extraMoveX() {
        int newX = (int) (Math.random() * 10) * 5;
        setX(getX() + newX);
    }

    @Override
    public void setPosition(int x, int y) {
        setX(x);
        setY(y * getNumber() + 55);
    }

    public FinisherStatus getFlagFinisher() {
        return flagFinisher;
    }

    public void setFlagFinisher(FinisherStatus flagFinisher) {
        this.flagFinisher = flagFinisher;
    }

    public int getCheckMove() {
        return checkMove;
    }

    public void setCheckMove() {
        checkMove = -checkMove;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
