package io.github.clinuxrulz.cursed_signals_jfx_backend;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.github.clinuxrulz.cursed_signals.CS;
import io.github.clinuxrulz.cursed_signals.HyperStrategy;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import io.github.clinuxrulz.cursed_signals.CS.Accessor;

public class JfxHyperStrategy implements HyperStrategy<Object> {
    @Override
    public void addChildren(Object parent_, Accessor<Object[]> children) {
        if (!(parent_ instanceof Pane)) {
            return;
        }
        Pane parent = (Pane)parent_;
        CS.createEffect(() -> {
            Object[] newChildren_ = children.get();
            if (!(newChildren_ instanceof Node[])) {
                return;
            }
            List<Node> newChildren = Arrays.asList((Node[])newChildren_);
            //
            // Get the live list of current children from the parent
            List<Node> currentChildren = parent.getChildrenUnmodifiable();

            // Remove children that are no longer in the new list
            List<Node> childrenToRemove = currentChildren.stream()
                    .filter(node -> !newChildren.contains(node))
                    .collect(Collectors.toList());
            parent.getChildren().removeAll(childrenToRemove);

            // Add new children that are not yet in the parent's children list
            for (int i = 0; i < newChildren.size(); i++) {
                Node newNode = newChildren.get(i);
                if (i >= parent.getChildren().size()) {
                    // If the new list is longer, add the new node to the end
                    parent.getChildren().add(newNode);
                } else {
                    Node oldNode = parent.getChildren().get(i);
                    if (!newNode.equals(oldNode)) {
                        // If the node at this position is different, replace it.
                        // This is a naive comparison. For more advanced cases, you would
                        // use a unique identifier or a more complex comparison logic.
                        if (!parent.getChildren().contains(newNode)) {
                            // Only add it if it's not already in the list
                            parent.getChildren().remove(i);
                            parent.getChildren().add(i, newNode);
                        }
                    }
                }
            }
        });
    }
}
