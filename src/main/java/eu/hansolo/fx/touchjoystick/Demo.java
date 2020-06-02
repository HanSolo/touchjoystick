/*
 * Copyright (c) 2020 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.touchjoystick;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;

import java.util.Locale;


/**
 * User: hansolo
 * Date: 31.05.20
 * Time: 08:20
 */
public class Demo extends Application {
    private Joystick joystick;
    private VBox     properties;

    @Override public void init() {
        joystick = new Joystick();
        //joystick.setStickyMode(true);
        joystick.setStepSize(5);
        //joystick.setStepButtonsVisible(false);
        //joystick.setLockState(LockState.X_LOCKED);

        RadioButton unlocked = new RadioButton("Unlocked");
        unlocked.setTextFill(Color.WHITE);
        unlocked.setSelected(true);
        unlocked.setOnAction(e -> joystick.setLockState(LockState.UNLOCKED));
        RadioButton lockedX  = new RadioButton("x-axis locked");
        lockedX.setTextFill(Color.WHITE);
        lockedX.setOnAction(e -> joystick.setLockState(LockState.X_LOCKED));
        RadioButton lockedY  = new RadioButton("y-axis locked");
        lockedY.setTextFill(Color.WHITE);
        lockedY.setOnAction(e -> joystick.setLockState(LockState.Y_LOCKED));
        ToggleGroup lockStateGroup = new ToggleGroup();
        lockStateGroup.getToggles().setAll(unlocked, lockedX, lockedY);

        CheckBox stepButtonsVisible = new CheckBox("Step buttons");
        stepButtonsVisible.setTextFill(Color.WHITE);
        stepButtonsVisible.setSelected(true);
        stepButtonsVisible.setOnAction(e -> joystick.setStepButtonsVisible(stepButtonsVisible.isSelected()));

        CheckBox stickyMode = new CheckBox("Sticky mode");
        stickyMode.setTextFill(Color.WHITE);
        stickyMode.setOnAction(e -> joystick.setStickyMode(stickyMode.isSelected()));

        Label value = new Label("Value: ");
        value.setTextFill(Color.WHITE);

        Label angle = new Label("Angle: ");
        angle.setTextFill(Color.WHITE);

        Label valueX = new Label("x: ");
        valueX.setTextFill(Color.WHITE);

        Label valueY = new Label("y: ");
        valueY.setTextFill(Color.WHITE);


        joystick.valueProperty().addListener((o, ov, nv) -> value.setText(String.format(Locale.US, "Value: %.2f", nv)));
        joystick.angleProperty().addListener((o, ov, nv) -> angle.setText(String.format(Locale.US, "Angle: %.0f", nv)));
        joystick.xProperty().addListener((o, ov, nv) -> valueX.setText(String.format(Locale.US, "x: %.2f", nv)));
        joystick.yProperty().addListener((o, ov, nv) -> valueY.setText(String.format(Locale.US, "y: %.2f", nv)));

        properties = new VBox(10, unlocked, lockedX, lockedY, stepButtonsVisible, stickyMode, value, angle, valueX, valueY);
    }

    @Override public void start(Stage stage) {
        HBox pane = new HBox(20, joystick, properties);
        pane.setPadding(new Insets(20));
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);

        stage.setTitle("Touchjoystick");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
