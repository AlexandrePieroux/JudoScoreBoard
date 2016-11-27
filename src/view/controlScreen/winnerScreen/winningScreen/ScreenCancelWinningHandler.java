package view.controlScreen.winnerScreen.winningScreen;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 12-11-16.
 */
public class ScreenCancelWinningHandler implements EventHandler<MouseEvent> {

    private final Node node;

    public ScreenCancelWinningHandler(Node node){
        this.node = node;
    }

    @Override
    public void handle(MouseEvent event) {
        this.node.toBack();
    }
}
