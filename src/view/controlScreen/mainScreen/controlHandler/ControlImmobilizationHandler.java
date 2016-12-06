package view.controlScreen.mainScreen.controlHandler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 06-11-16.
 */
public class ControlImmobilizationHandler implements EventHandler<MouseEvent> {

    private Controller controller;


    public ControlImmobilizationHandler(Controller controller, Button combatClock){
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        if(this.controller.immobilizationTimerSuspendedProperty().not().get()){
            this.controller.immobilizationTimerPause();
            this.controller.combatTimerResume();
        } else if(this.controller.combatTimerSuspendedProperty().not().get()) {
            this.controller.combatTimerPause();
            this.controller.immobilizationTimerReset();
            this.controller.immobilizationTimerResume();
        }
    }
}
