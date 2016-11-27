package view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 29-10-16.
 */
public class ScreenExitHandler implements EventHandler<MouseEvent> {

    public ScreenExitHandler(){
        super();
    }

    @Override
    public void handle(MouseEvent e){
        Platform.exit();
    }

}
