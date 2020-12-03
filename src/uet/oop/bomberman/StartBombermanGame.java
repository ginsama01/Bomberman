package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uet.oop.bomberman.sound.AudioPlayer;

import java.io.IOException;
import java.io.InputStream;


public class StartBombermanGame extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AudioPlayer startMusic = new AudioPlayer("renai");
        startMusic.run();
        StackPane layout = new StackPane();
        layout.setMaxSize(1000, 500);
        layout.setPrefSize(1000, 500);
        layout.setMinSize(1000, 500);

        //Set image background
        InputStream input = this.getClass().getResourceAsStream("/image/background.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setPickOnBounds(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(500);


        //Set Vbox
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setPrefWidth(100);
        vBox.setPrefHeight(200);

        //Set Button
        //Play button
        Button playButton = new Button("Play");
        playButton.setMaxWidth(150);
        playButton.setMinWidth(150);
        playButton.setPrefWidth(150);
        playButton.setOnAction(event -> {
            startMusic.stop();
            BombermanGame newGame = new BombermanGame();
            try {
                newGame.start(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Double Play button
        Button doublePlay = new Button("2 players");
        doublePlay.setMaxWidth(150);
        doublePlay.setMinWidth(150);
        doublePlay.setPrefWidth(150);

        //Sound button
        Button soundButton = new Button();
        if (AudioPlayer.isMuted()) {
            soundButton.setText("Sound: Off");
        } else {
            soundButton.setText("Sound: On");
        }
        soundButton.setPrefWidth(150);
        soundButton.setMinWidth(150);
        soundButton.setMaxWidth(150);
        soundButton.setOnAction(event -> {
            if (AudioPlayer.isMuted()) {
                soundButton.setText("Sound: On");
            } else {
                soundButton.setText("Sound: Off");
            }
            startMusic.mute();
        });

        //Exit button
        Button exitButton = new Button("Exit");
        exitButton.setMaxWidth(150);
        exitButton.setMinWidth(150);
        exitButton.setPrefWidth(150);
        exitButton.setOnAction(event -> {
            primaryStage.close();
        });


        vBox.getChildren().addAll(playButton, doublePlay, soundButton, exitButton);
        VBox.setMargin(playButton, new Insets(0, 0, 30, 0));
        VBox.setMargin(doublePlay, new Insets(0, 0, 30, 0));
        VBox.setMargin(soundButton, new Insets(0, 0, 30, 0));
        VBox.setMargin(exitButton, new Insets(0, 0, 80, 0));


        layout.getChildren().add(imageView);
        layout.getChildren().add(vBox);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
