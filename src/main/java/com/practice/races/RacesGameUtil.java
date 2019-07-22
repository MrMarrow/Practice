package com.practice.races;

import com.practice.races.model.Bug;
import com.practice.races.tool.TrackPane;
import javafx.geometry.Rectangle2D;

import java.util.List;

import static com.practice.races.RacesGameConstant.BUG_HEIGHT;

public final class RacesGameUtil {

    private RacesGameUtil() {}

    public static void checkBugPositionsX(List<TrackPane> bugPanes, List<Bug> bugs){
        for (int i = 0; i < bugs.size(); i++) {
            Bug bug = bugs.get(i);
            bugPanes.get(i).setTranslateX(bug.getX());
            int newX;
            if (bug.getCheckMove() == -1) {
                newX = 0;
            } else {
                newX = 100;
            }
            bugPanes.get(i).getImageView().setViewport(new Rectangle2D(newX, 0, 100, BUG_HEIGHT));
        }
    }

}
