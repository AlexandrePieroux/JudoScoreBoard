package view.controlScreen.mainScreen.controlHandler;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 08-11-16.
 */
public class ControlNewCombatHandler implements EventHandler<MouseEvent> {

    private Node node;

    public ControlNewCombatHandler(Node node){
        this.node = node;
    }

    @Override
    public void handle(MouseEvent event) {
        this.node.toFront();
    }
}
