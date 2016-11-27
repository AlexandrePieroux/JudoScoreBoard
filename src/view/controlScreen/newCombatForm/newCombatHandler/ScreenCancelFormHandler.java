package view.controlScreen.newCombatForm.newCombatHandler;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 11-11-16.
 */
public class ScreenCancelFormHandler implements EventHandler<MouseEvent> {

    private Node node;

    public ScreenCancelFormHandler(Node node){
        this.node = node;
    }

    @Override
    public void handle(MouseEvent event) {
        this.node.toBack();
    }
}
