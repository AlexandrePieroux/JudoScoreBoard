package view.controlScreen.mainScreen.controlHandler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 06-11-16.
 */
public class ControlCombatStartHandler implements EventHandler<MouseEvent> {

    private final Controller controller;
    private final Button immoblization;

    public ControlCombatStartHandler(Controller controller, Button immobilization){
        this.controller = controller;
        this.immoblization = immobilization;
  }

    @Override
    public void handle(MouseEvent event) {
        if(!this.controller.combatTimerSuspendedProperty().get()){
            this.controller.combatTimerPause();
        } else if(this.controller.immobilizationTimerSuspendedProperty().get()
                && !this.controller.combatSuspendedProperty().get()){
            this.controller.combatTimerResume();
        }
    }
}
