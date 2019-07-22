package com.practice.races.tool;

import com.practice.races.controller.TrackController;
import com.practice.races.model.Bug;
import com.practice.races.model.FinisherStatus;
import com.practice.races.model.Track;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.practice.races.RacesGameConstant.BUG_HEIGHT;
import static com.practice.races.RacesGameConstant.BUG_WIDTH;
import static com.practice.races.RacesGameConstant.RACE_HEIGHT;
import static com.practice.races.RacesGameConstant.RACE_WIDTH;
import static com.practice.races.RacesGameConstant.START_POSITION_X;
import static com.practice.races.RacesGameConstant.START_POSITION_Y;

public class MainPane extends Pane {

    //list of bugs
    private List<Bug> bugList;
    private List<TrackPane> bugPaneList;
    //list of fields for bug name
    private List<TextField> textFieldList;
    //list of finished bugs
    private Queue<Bug> winners;
    //list of threads
    private List<TrackController> threads;

    private StartInterruptButton button;
    private Label leaderLabel;
    private int amount;

    public MainPane(int amount) {
        this.amount = amount;
        bugList = new ArrayList<>();
        bugPaneList = new ArrayList<>();
        textFieldList = new ArrayList<>();
        winners = new LinkedList<>();
        threads = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Track track = new Track(i);

            //bug
            Bug bug = track.getBug();
            TrackPane bugPane = new TrackPane(bug, BUG_WIDTH, BUG_HEIGHT);
            bugPane.setOnMouseClicked(event -> { //listener for double click
                if (event.getClickCount() == 2 && bug.getFlagFinisher() == FinisherStatus.STARTED) {
                    bug.extraMoveX();
                }
            });

            //bugs name
            TextField textField = new BugNameTextField(bug);

            //bugs trace
            Label traceLabel = new Label("Race №" + i);
            traceLabel.setFont(Font.font("Arial", 12));
            traceLabel.setTranslateX(START_POSITION_X - 60);
            traceLabel.setTranslateY(START_POSITION_Y * i + RACE_HEIGHT + 10);

            getChildren().addAll(new TrackPane(track.getRace(), RACE_WIDTH, RACE_HEIGHT),
                    bugPane, textField, traceLabel);

            bugList.add(bug);
            bugPaneList.add(bugPane);
            textFieldList.add(textField);
        }


        button = new StartInterruptButton(bugList, winners, textFieldList, threads);
        leaderLabel = setLeaderLabel();
        getChildren().addAll(button, leaderLabel);

        setScrollBar();
    }

    private void setScrollBar() {
        ScrollBar scrollBar = new ScrollBar();
        if (amount > 4) {
            scrollBar.setPrefHeight((RACE_HEIGHT * 2 - 19) * amount);
            scrollBar.setOrientation(Orientation.VERTICAL);
            scrollBar.setVisible(false);
            getChildren().add(scrollBar);
        }
    }

    /**
     * initial Label for leader
     */
    private Label setLeaderLabel() {
        Label label = new Label();
        label.setText("Current leader: none");
        label.setFont(Font.font("Arial", 12));
        label.setTranslateY(START_POSITION_Y - RACE_HEIGHT - 20);
        label.setTranslateX(START_POSITION_X + RACE_WIDTH - BUG_WIDTH - 20);
        return label;
    }

    /**
     * checks who running first
     * the first finished automatically become leader
     */
    public void checkLeader() {
        if (button.getUserData().equals("interrupt") && winners.isEmpty()) {
            Bug leader = bugList.get(0);
            for (Bug bug : bugList) {
                if (bug.getX() > leader.getX()) {
                    leader = bug;
                }
            }
            leaderLabel.setText("Current leader: " + leader.getName());
        } else if (!winners.isEmpty()) {
            leaderLabel.setText("Current leader: " + winners.peek().getName());
        } else {
            leaderLabel.setText("Current leader: none");
        }
    }

    /**
     * checks race finish
     *
     * @param primaryStage main stage
     */
    public void checkWinners(Stage primaryStage) {
        if (winners.size() == bugList.size()) {
            System.out.println("Race is finished");
            button.changeToRestart();
            ResultStage resultStage = new ResultStage(winners);
            resultStage.initOwner(primaryStage);
            resultStage.initModality(Modality.WINDOW_MODAL);
            resultStage.setResizable(false);
            resultStage.show();
        }
    }

    public List<Bug> getBugList() {
        return bugList;
    }

    public List<TrackPane> getBugPaneList() {
        return bugPaneList;
    }


}
