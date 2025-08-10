package io.github.clinuxrulz.cursed_signals_jfx_example;

import io.github.clinuxrulz.cursed_signals.CS;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static io.github.clinuxrulz.cursed_signals_jfx_backend.HyperJfx.h;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        var s = CS.createSignal(0);
        var root = CS.<Node>createRoot((dispose) -> {
            return h(
                StackPane::new,
                (attr) -> {},
                CS.createMemo(() -> new Node[] {
                    h(
                        Label::new,
                        (attr) -> {
                            CS.createEffect(() -> {
                                attr.setText("Value: " + s.get().get());
                            });
                        },
                        CS.createMemo(() -> new Node[0])
                    ),
                    h(
                        Button::new,
                        (attr) -> {
                            attr.setText("+");
                            attr.setOnAction((e) -> s.set().accept(s.get().get() + 1));
                        },
                        CS.createMemo(() -> new Node[0])
                    ),
                    h(
                        Button::new,
                        (attr) -> {
                            attr.setText("-");
                            attr.setOnAction((e) -> s.set().accept(s.get().get() - 1));
                        },
                        CS.createMemo(() -> new Node[0])
                    )
                })
            );
        });
        Scene scene = new Scene((Parent) root, 300, 250);
        primaryStage.setTitle("JavaFX Button Click Event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
