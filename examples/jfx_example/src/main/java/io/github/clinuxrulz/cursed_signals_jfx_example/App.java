package io.github.clinuxrulz.cursed_signals_jfx_example;

import io.github.clinuxrulz.cursed_signals.CS;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static io.github.clinuxrulz.cursed_signals_jfx_backend.HyperJfx.h;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        var s = CS.createSignal(0);
        var root = CS.<Node>createRoot((dispose) -> {
            return h(
                VBox::new,
                (attr) -> {},
                CS.createMemo(() -> new Node[] {
                    h(
                        Label::new,
                        (attr) -> {
                            Label attr2 = (Label)attr;
                            CS.createEffect(() -> {
                                attr2.setText("Value: " + s.get().get());
                            });
                        },
                        CS.createMemo(() -> new Node[0])
                    ),
                    h(
                        Button::new,
                        (attr) -> {
                            Button attr2 = (Button)attr;
                            attr2.setText("+");
                            attr2.setOnAction((e) -> s.set().accept(s.get().get() + 1));
                        },
                        CS.createMemo(() -> new Node[0])
                    ),
                    h(
                        Button::new,
                        (attr) -> {
                            Button attr2 = (Button)attr;
                            attr2.setText("-");
                            attr2.setOnAction((e) -> s.set().accept(s.get().get() - 1));
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

    public static void main(String[] args) {
        launch(args);
    }
}
