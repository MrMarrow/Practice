package com.practice.races.tool;

import com.practice.races.model.TrackObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TrackPane extends Pane {

    private ImageView imageView;

    public TrackPane(TrackObject object, int width, int height) {
        setTranslateX(object.getX());
        setTranslateY(object.getY());

        Image image = new Image(this.getClass().getResourceAsStream("/" + object.getImageName()));
        imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        if (object.getUserData().equals("bug")) {
            imageView.setViewport(new Rectangle2D(0, 0, width, height));
        }
        getChildren().add(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
