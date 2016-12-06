package view.controlScreen.newCombatForm.newCombatHandler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.controlScreen.newCombatForm.NewCombatController;

/**
 * Created by alexp on 10-11-16.
 */
public class ScreenOkFormHandler implements EventHandler<MouseEvent> {

    private NewCombatController formController;
    private Controller controller;

    public ScreenOkFormHandler(NewCombatController formController, Controller controller){
        this.formController = formController;
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!this.formController.validateform()) {
            return;
        }

        this.controller.newCombat(this.formController.getFirstJudokaName().getText().toUpperCase(),
                this.formController.getSecondJudokaName().getText().toUpperCase());
        this.controller.combatTimerSetTime((Integer.parseInt(this.formController.getCombatMinutes().getText()) * 60000) +
                (Integer.parseInt(this.formController.getCombatSeconds().getText()) * 1000));
        this.controller.immobilizationTimerSetTime(Integer.parseInt(this.formController.getImmobilizationSeconds().getText()) * 1000);

        switch (this.formController.getGenderToggleGroup().getSelectedToggle().getUserData().toString()) {
            case "M":
                this.controller.setMaleGender();
                break;
            case "F":
                this.controller.setFemaleGender();
        }

        this.controller.setWeight(Integer.parseInt(this.formController.getCategory().getText()));
        this.controller.resumeCombat();

        this.formController.getContainer().toBack();
        this.formController.getFirstJudokaName().setText("");
        this.formController.getSecondJudokaName().setText("");
    }
}
