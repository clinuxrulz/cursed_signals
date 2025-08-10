package io.github.clinuxrulz.cursed_signals;

public interface HyperStrategy<N> {
    void addChildren(
        N parent,
        CS.Accessor<N[]> children
    );
}
