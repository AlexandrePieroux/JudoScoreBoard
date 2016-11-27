package view.controlScreen.mainScreen.controlHandler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 06-11-16.
 */
public class ControlImmobilizationSuspensionHandler implements EventHandler<MouseEvent> {

    private Controller controller;

    public ControlImmobilizationSuspensionHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        if(!this.controller.immobilizationTimerSuspendedProperty().get()){
            this.controller.immobilizationTimerPause();
            this.controller.suspendCombat();
        } else if(this.controller.combatSuspendedProperty().get()){
            this.controller.immobilizationTimerResume();
            this.controller.resumeCombat();
        }
    }
}
