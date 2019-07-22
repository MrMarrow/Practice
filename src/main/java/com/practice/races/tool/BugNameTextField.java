package com.practice.races.tool;

import com.practice.races.model.Bug;
import javafx.scene.control.TextField;

import static com.practice.races.RacesGameConstant.START_POSITION_X;
import static com.practice.races.RacesGameConstant.START_POSITION_Y;


public class BugNameTextField extends TextField {

    public BugNameTextField(final Bug bug) {
        super();
        setText(bug.getName());
        setTranslateX(START_POSITION_X);
        setTranslateY(START_POSITION_Y * bug.getNumber() + 20);
        //listener if field was change
        textProperty().addListener((observable, oldValue, newValue) -> bug.setName(newValue));

    }

}
