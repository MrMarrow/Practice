package com.practice.races.tool;

import com.practice.races.controller.TrackController;
import com.practice.races.model.Bug;
import com.practice.races.model.FinisherStatus;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Queue;

import static com.practice.races.RacesGameConstant.RACE_HEIGHT;
import static com.practice.races.RacesGameConstant.RACE_WIDTH;
import static com.practice.races.RacesGameConstant.START_POSITION_X;
import static com.practice.races.RacesGameConstant.START_POSITION_Y;

public class StartInterruptButton extends Button {

    private List<Bug> bugList;
    private Queue<Bug> winners;
    private List<TextField> textFieldList;
    private List<TrackController> threads;

    public StartInterruptButton(List<Bug> bugList, Queue<Bug> winners,
                                List<TextField> textFieldList, List<TrackController> threads) {
        super();

        this.bugList = bugList;
        this.winners = winners;
        this.textFieldList = textFieldList;
        this.threads = threads;

        changeToStart();

        setWidth(50);
        setTranslateX(START_POSITION_X + RACE_WIDTH / 2 - getWidth() / 2);
        setTranslateY(RACE_HEIGHT / 4);

        //if button clicked
        setOnAction(event -> {
            if (getUserData().equals("start")) {
                startRacing();
                changeToReset();
                setTextFieldsStatus(false);
                System.out.println("Race is started");
            } else if (getUserData().equals("interrupt")) {
                interruptRacing();
                changeToStart();
                setTextFieldsStatus(true);
                System.out.println("Race is interrupted");
            } else if (getUserData().equals("restart")) {
                interruptRacing();
                changeToStart();
                setTextFieldsStatus(true);
                System.out.println("Race is restarted");
            }
        });
    }

    /**
     * if race is started all fields for name
     * become unable to redact
     *
     * @param b flag of starting race
     */
    private void setTextFieldsStatus(boolean b) {
        for (TextField textField : textFieldList) {
            textField.setEditable(b);
        }
    }

    /**
     * change button and it status on start
     */
    private void changeToStart() {
        setText("Start race");
        setUserData("start");
    }

    /**
     * change button and it status on interrupt
     */
    private void changeToReset() {
        setText("Interrupt race");
        setUserData("interrupt");
    }

    /**
     * change button and it status on restart
     */
    public void changeToRestart() {
        setText("Restart race");
        setUserData("restart");
    }

    /**
     * interrupt race and threads
     */
    private void interruptRacing() {
        for (int i = 0; i < bugList.size(); i++) {
            //destroy thread
            threads.get(i).setFlagFinish(true);
            //move bug on start
            bugList.get(i).setPosition(START_POSITION_X, START_POSITION_Y);
            bugList.get(i).setFlagFinisher(FinisherStatus.NOT_STARTED);
            winners.clear();
        }
        threads.clear();
    }

    /**
     * starts race
     * open threads
     */
    private void startRacing() {
        for (Bug bug : bugList) {
            bug.setFlagFinisher(FinisherStatus.STARTED);
            //create thread
            TrackController runnable = new TrackController(bug, winners);
            Thread thread = new Thread(runnable);
            threads.add(runnable);
            thread.setDaemon(true);
            thread.start();
        }

    }



}
