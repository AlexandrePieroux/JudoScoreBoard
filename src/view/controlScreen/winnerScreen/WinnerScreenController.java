package view.controlScreen.winnerScreen;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ResourceBundle;

/**
 * Created by alexp on 12-11-16.
 */
public class WinnerScreenController {

    @FXML private GridPane container;
    @FXML private Text winnerName;
    @FXML private VBox winninOptions;
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;

    private Controller controller;

    public GridPane getContainer() {
        return container;
    }

    public VBox getWinninOptions() {
        return winninOptions;
    }

    public Button getOkBtn() {
        return okBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public void init(Controller controller){
        this.controller = controller;
    }

    public void setWinningOptions() {
        this.controller.combatTimerPause();
        this.controller.immobilizationTimerPause();

        this.winnerName.setText(this.controller.winnerJudokaNameProperty().get());

        this.winninOptions.getChildren().clear();
        ToggleGroup group = new ToggleGroup();
        ResourceBundle bundle = ResourceBundle.getBundle("resource.Resource");


        // Default ippon option
        RadioButton ippon = new RadioButton("Ippon");
        ippon.setToggleGroup(group);
        this.getWinninOptions().getChildren().add(ippon);

        // Waza-Ari Awazate Ippon
        if(this.controller.winnerJudokaWazaAriProperty().get() == 1){
            RadioButton wazaAriWazate = new RadioButton("Waza-Ari Awazate Ippon");
            wazaAriWazate.setToggleGroup(group);
            this.getWinninOptions().getChildren().add(wazaAriWazate);
        }

        // Hansoku Make
        if(this.controller.firstJudokaShidoProperty().get() == 3 ||
                this.controller.secondJudokaShidoProperty().get() == 3) {
            RadioButton hansokuMake = new RadioButton("Hansoku Make");
            hansokuMake.setToggleGroup(group);
            this.getWinninOptions().getChildren().add(hansokuMake);
        }

        // Default forfeit
        RadioButton forfeit = new RadioButton(bundle.getString("WINNER_forfeit"));
        forfeit.setToggleGroup(group);
        this.getWinninOptions().getChildren().add(forfeit);
    }
}