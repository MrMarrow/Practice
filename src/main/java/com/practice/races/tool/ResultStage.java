package com.practice.races.tool;

import com.practice.races.model.Bug;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Queue;

import static com.practice.races.RacesGameConstant.RESULT_SCENE_HEIGHT;
import static com.practice.races.RacesGameConstant.RESULT_SCENE_WIDTH;

public class ResultStage extends Stage {

    private Queue<Bug> bugList; //list of winners
    private Scene scene;

    public ResultStage(Queue<Bug> bugList) {
        super();
        this.bugList = bugList;
        scene = new Scene(initialContent(), RESULT_SCENE_WIDTH, RESULT_SCENE_HEIGHT);
        setScene(scene);
        setTitle("Results");
    }

    private ScrollPane initialContent() {
        FlowPane root = new FlowPane();

        root.setOrientation(Orientation.VERTICAL);
        int n = bugList.size();
        root.setPrefHeight(25 * n);
        for (int i = 0; i < n; i++) {
            Bug bug = bugList.poll();
            Label label = new Label(i + 1 + ": " + bug.getName());
            label.setFont(Font.font("Arial", 20));
            root.getChildren().add(label);

        }
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return scrollPane;
    }

}
