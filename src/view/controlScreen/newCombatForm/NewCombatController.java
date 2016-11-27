package view.controlScreen.newCombatForm;

import controller.Controller;
import javafx.scene.control.*;
import view.controlScreen.newCombatForm.newCombatHandler.ScreenCancelFormHandler;
import view.controlScreen.newCombatForm.newCombatHandler.ScreenOkFormHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


/**
 * Created by alexp on 10-11-16.
 */
public class NewCombatController {

    @FXML private GridPane container;
    @FXML private TextField firstJudokaName;
    @FXML private TextField secondJudokaName;
    @FXML private ToggleGroup genderToggleGroup;
    @FXML private TextField category;
    @FXML private TextField combatMinutes;
    @FXML private TextField combatSeconds;
    @FXML private TextField immobilizationSeconds;

    @FXML private Button okBtn;
    @FXML private Button cancelBtn;

    public GridPane getContainer() {
        return container;
    }

    public TextField getFirstJudokaName() {
        return firstJudokaName;
    }

    public TextField getSecondJudokaName() {
        return secondJudokaName;
    }

    public ToggleGroup getGenderToggleGroup() {
        return genderToggleGroup;
    }

    public TextField getCategory() {
        return category;
    }

    public TextField getCombatMinutes() {
        return combatMinutes;
    }

    public TextField getCombatSeconds() {
        return combatSeconds;
    }

    public TextField getImmobilizationSeconds() {
        return immobilizationSeconds;
    }

    public void init(Controller controller){
        ScreenCancelFormHandler cancelFormHandler = new ScreenCancelFormHandler(this.container);
        ScreenOkFormHandler okFormHandler = new ScreenOkFormHandler(this, controller);

        setNumericTextField(this.category, 4);
        setPositiveNumericTextField(this.combatMinutes, 1);
        setPositiveNumericTextField(this.combatSeconds, 2);
        setPositiveNumericTextField(this.immobilizationSeconds, 2);

        this.okBtn.setOnMouseClicked(okFormHandler);
        this.cancelBtn.setOnMouseClicked(cancelFormHandler);
        controller.initProperty().addListener((obs, o, n) -> {
            if(n)
                this.container.toBack();
        });
    }

    public boolean validateform() {
        boolean result = checkTextField(this.firstJudokaName);
        result &= checkTextField(this.secondJudokaName);
        result &= checkTextField(this.category);
        result &= checkTextField(this.combatMinutes);
        result &= checkTextField(this.combatSeconds);
        result &= checkTextField(this.immobilizationSeconds);
        return result;
    }

    private boolean checkTextField(TextField t){
        if(t.getText().trim().length() == 0){
            if(!t.getStyleClass().contains("tferror"))
                t.getStyleClass().add("tferror");
            return false;
        }
        t.getStyleClass().remove("tferror");
        return true;
    }

    private void setNumericTextField(TextField t, int maxLength){
        t.textProperty().addListener((obs, o, n) -> {
                if (t.getText().length() > maxLength) {
                    String s = t.getText().substring(0, maxLength);
                    t.setText(s);
                }
                if (!n.matches("(-?)\\d*")) {
                    t.setText(n.replaceAll("[^(-?)\\d]", ""));
                }
            });
    }

    private void setPositiveNumericTextField(TextField t, int maxLength){
        t.textProperty().addListener((obs, o, n) -> {
                if (t.getText().length() > maxLength) {
                    String s = t.getText().substring(0, maxLength);
                    t.setText(s);
                }
                if (!n.matches("\\d*")) {
                    t.setText(n.replaceAll("[^\\d]", ""));
                }
            });
    }
}
