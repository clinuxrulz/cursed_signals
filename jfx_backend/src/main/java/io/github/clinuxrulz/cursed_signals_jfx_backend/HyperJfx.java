package io.github.clinuxrulz.cursed_signals_jfx_backend;

import java.util.function.Consumer;
import java.util.function.Supplier;

import io.github.clinuxrulz.cursed_signals.CS;
import io.github.clinuxrulz.cursed_signals.Hyper;
import javafx.scene.Node;

public class HyperJfx {
    private static JfxHyperStrategy strategy = new JfxHyperStrategy();
    private static Hyper.H<Object> h_ = Hyper.makeH(strategy);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <A extends Node> A h(
        Supplier<A> constructor,
        Consumer<A> attributes,
        CS.Accessor<Node[]> children
    ) {
        return (A)h_.h(
            (Supplier)constructor,
            (Consumer)attributes,
            (CS.Accessor)children
        );
    }
}
