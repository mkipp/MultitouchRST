package de.hsa.interaction;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Rotation, scale, translation (RST) for multitouch with Java + JavaFX.
 *
 * @author Michael Kipp
 */
public class MultitouchRST extends Application {

    @Override
    public void start(Stage stage) {

        // exit button
        Button bExit = new Button("Exit");
        bExit.setCancelButton(true);
        bExit.setMinWidth(200);
        bExit.setMinHeight(160);
        bExit.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        bExit.setFont(new Font("Helvetica", 36));
        bExit.setTextFill(Color.WHITE);

        HBox buttonPane = new HBox(bExit);
        buttonPane.setSpacing(10);
        buttonPane.setPadding(new Insets(8));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        bExit.setOnAction(e -> {
            Platform.exit();
        });

        // make pane
        Pane centerPane = new Pane();

        // get screen dimensions (may not work on mobile devices)
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double w = d.getWidth();
        double h = d.getHeight();
        
        // create touchable objects
        Rectangle o1 = new Rectangle(w/2, h/2, 300, 300);
        o1.setFill(Color.RED);
        new Touchable(o1);

        Rectangle o2 = new Rectangle(w/5, h/4, 350, 210);
        o2.setFill(Color.ORANGE);
        new Touchable(o2);

        Circle o3 = new Circle(3*w/4, 3*h/4, 100, Color.BROWN);
        new Touchable(o3);

        Polygon o4 = new Polygon(new double[]{
            0.0, 0.0,
            250.0, 10.0,
            280.0, 240.0,
            20.0, 210.0});
        o4.setFill(Color.GREEN);
        o4.setTranslateX(3*w/4);
        o4.setTranslateY(h/3);

        new Touchable(o4);

        centerPane.getChildren().addAll(o1, o2, o3, o4);

        // layout
        BorderPane root = new BorderPane(centerPane);
        root.setBottom(buttonPane);
        Scene scene = new Scene(root);
        stage.setTitle("Touch Lab");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
