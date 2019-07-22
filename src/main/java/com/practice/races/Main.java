package com.practice.races;

import com.practice.races.tool.MainPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static com.practice.races.RacesGameConstant.MAIN_SCENE_HEIGHT;
import static com.practice.races.RacesGameConstant.MAIN_SCENE_WIDTH;
import static com.practice.races.RacesGameConstant.RACE_HEIGHT;
import static com.practice.races.RacesGameUtil.checkBugPositionsX;


public class Main extends Application {

    private static final String THREAD_AMOUNT_FLAG = "--threadAmount";
    private static int threadAmount = 10;

    private MainPane appRoot;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        //initial elements for main scene
        appRoot = new MainPane(threadAmount);

        ScrollPane scrollPane = new ScrollPane(appRoot);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMaxHeight(MAIN_SCENE_HEIGHT + RACE_HEIGHT * 2 * (threadAmount - 4));

        Scene scene = new Scene(scrollPane, MAIN_SCENE_WIDTH, MAIN_SCENE_HEIGHT);

        primaryStage.setTitle("Races");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        //timer to follow the race
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkBugPositionsX(appRoot.getBugPaneList(), appRoot.getBugList());
                appRoot.checkWinners(primaryStage);
                appRoot.checkLeader();
            }
        };
        timer.start();

    }

    public static void main(String[] args) {
        for (String flag : args) {
            tryToSetThreadAmount(flag);
        }
        launch(args);
    }

    private static void tryToSetThreadAmount(String flag) {
        String[] splitFlag = flag.split("=");
        if (splitFlag.length == 2) {
            if (splitFlag[0].equals(THREAD_AMOUNT_FLAG)) {
                threadAmount = Integer.parseInt(splitFlag[1]);
            }
        } else {
            try {
                threadAmount = Integer.parseInt(splitFlag[0]);
            } catch (NumberFormatException e) {
                System.out.println("Uncorrected input");
                System.out.println("Default value 10 was used");
                e.printStackTrace();
            }
        }
    }

}
