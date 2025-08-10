package io.github.clinuxrulz.cursed_signals;

import java.util.function.Consumer;
import java.util.function.Supplier;

import io.github.clinuxrulz.cursed_signals.CS.Accessor;

public class Hyper {
    public interface H<N> {
        N h(
            Supplier<N> constructor,
            Consumer<N> attributes,
            CS.Accessor<N[]> children
        );
    }

    public static <N> H<N> makeH(HyperStrategy<N> strategy) {
        return new H<N>() {
            @Override
            public N h(Supplier<N> constructor, Consumer<N> attributes, Accessor<N[]> children) {
                var node = constructor.get();
                CS.createEffect(() -> CS.untrackVoid(() -> {
                    attributes.accept(node);
                    strategy.addChildren(node, children);
                }));
                return node;
            }
        };
    }
}
