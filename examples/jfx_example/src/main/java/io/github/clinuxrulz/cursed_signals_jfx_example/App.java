package io.github.clinuxrulz.cursed_signals_jfx_example;

import io.github.clinuxrulz.cursed_signals.CS;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static io.github.clinuxrulz.cursed_signals_jfx_backend.HyperJfx.h;

public class App {
    public static void main() {
        var s = CS.createSignal(0);
        var node = CS.<Node>createRoot((dispose) -> {
            return h(
                Pane::new,
                (attr) -> {},
                CS.createMemo(() -> new Node[] {
                    h(
                        Label::new,
                        (attr) -> {
                            CS.createEffect(() -> {
                            });
                        },
                        CS.createMemo(() -> new Node[0])
                    ),
                    h(
                        Button::new,
                        (attr) -> {},
                        CS.createMemo(() -> new Node[0])
                    )
                })
            );
        });
    }
}
