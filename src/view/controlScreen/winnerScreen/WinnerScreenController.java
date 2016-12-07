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
    private ToggleGroup group = new ToggleGroup();

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
        ResourceBundle bundle = ResourceBundle.getBundle("resource.Resource");


        // Default ippon option
        RadioButton ippon = new RadioButton(bundle.getString("POINT_ippon"));
        ippon.setUserData("POINT_ippon");
        ippon.setToggleGroup(this.group);
        this.getWinninOptions().getChildren().add(ippon);

        // Waza-Ari Awazate Ippon
        if(this.controller.winnerJudokaWazaAriProperty().get() == 1){
            RadioButton wazaAriWazate = new RadioButton(bundle.getString("POINT_waai"));
            wazaAriWazate.setUserData("POINT_waai");
            wazaAriWazate.setToggleGroup(this.group);
            this.getWinninOptions().getChildren().add(wazaAriWazate);
        }

        // Hansoku Make
        if(this.controller.firstJudokaShidoProperty().get() == 3 ||
                this.controller.secondJudokaShidoProperty().get() == 3) {
            RadioButton hansokuMake = new RadioButton(bundle.getString("DISQUALIFIED"));
            hansokuMake.setUserData("DISQUALIFIED");
            hansokuMake.setToggleGroup(this.group);
            this.getWinninOptions().getChildren().add(hansokuMake);
        }

        // Timeout
        if(this.controller.combatTimerSecondsProperty().get() == 0 &&
                this.controller.combatTimerMinutesProperty().get() == 0){
            RadioButton timeout = new RadioButton(bundle.getString("WINNER_timeout"));
            timeout.setUserData("WINNER_timeout");
            timeout.setToggleGroup(this.group);
            this.getWinninOptions().getChildren().add(timeout);
        }

        // Default forfeit
        RadioButton forfeit = new RadioButton(bundle.getString("WINNER_forfeit"));
        forfeit.setUserData("WINNER_forfeit");
        forfeit.setToggleGroup(this.group);
        this.getWinninOptions().getChildren().add(forfeit);
    }

    public String getSubmitedWinningOptions(){
        return this.group.getSelectedToggle().getUserData().toString();
    }
}
