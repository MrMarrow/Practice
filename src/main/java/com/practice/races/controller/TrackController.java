package com.practice.races.controller;

import com.practice.races.model.Bug;
import com.practice.races.model.FinisherStatus;

import java.util.Queue;

import static com.practice.races.RacesGameConstant.BUG_WIDTH;
import static com.practice.races.RacesGameConstant.RACE_WIDTH;
import static com.practice.races.RacesGameConstant.START_POSITION_X;
import static com.practice.races.RacesGameConstant.START_POSITION_Y;


public class TrackController implements Runnable {

    //flag true when bug finish race or race was interrupted
    private volatile boolean flagFinish;

    private Bug bug;
    private Queue<Bug> winners;

    public TrackController(Bug bug, Queue<Bug> winners) {
        this.bug = bug;
        this.winners = winners;
        flagFinish = false;
    }

    @Override
    public void run() {
        while (true) {
            if (flagFinish) {
                bug.setPosition(START_POSITION_X, START_POSITION_Y);
                break;
            }
            //if bug finished
            if (bug.getX() >= START_POSITION_X + RACE_WIDTH - BUG_WIDTH) {
                bug.setFlagFinisher(FinisherStatus.FINISHED);
                System.out.println(bug.getName() + " finished");
                winners.add(bug);
                break;
            }

            bug.moveX();
            bug.setCheckMove();

            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public void setFlagFinish(boolean flagFinish) {
        this.flagFinish = flagFinish;
    }

}
