package Leisure.passy;

import Leisure.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * @author Jacob
 * @since 8/2/2015
 */
public class PassUI extends Application {

    private static final String lowers = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";
    private static final String specials = ")!@#$%^&*(";

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox box = new HBox(20);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10, 10, 10, 10));
        TextField passField = new TextField();
        passField.setEditable(false);
        Random random = new Random();
        StringBuilder builder = new StringBuilder(16);
        Button generate = new Button("Generate");
        generate.setOnAction(t -> {
            builder.setLength(0);
            for (int i = 0; i < random.nextInt(12, 16); i++) {
                if (random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        builder.append(String.valueOf(lowers.charAt(random.nextInt(0, lowers.length()))).toUpperCase());
                    } else {
                        builder.append(lowers.charAt(random.nextInt(0, lowers.length())));
                    }
                } else {
                    if (random.nextBoolean()) {
                        builder.append(numbers.charAt(random.nextInt(0, numbers.length())));
                    } else {
                        builder.append(specials.charAt(random.nextInt(0, specials.length())));
                    }
                }
            }
            passField.setText(builder.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(builder.toString()), null);
        });
        box.getChildren().addAll(passField, generate);
        Scene scene = new Scene(box);
        primaryStage.setTitle("Passy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
