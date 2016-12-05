package view.controlScreen.mainScreen;

import controller.Controller;
import javafx.beans.binding.Bindings;
import view.ScreenExitHandler;
import view.controlScreen.historyScreen.HistoryController;
import view.controlScreen.mainScreen.controlHandler.*;
import view.controlScreen.winnerScreen.winningScreen.ScreenCancelWinningHandler;
import view.controlScreen.winnerScreen.winningScreen.ScreenOkWinningHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import view.controlScreen.noCombatScreen.NoCombatScreenController;
import view.controlScreen.winnerScreen.WinnerScreenController;
import view.displayScreen.DisplayScreenController;
import view.controlScreen.newCombatForm.NewCombatController;

import java.util.ResourceBundle;

/**
 * Created by alexp on 26-10-16.
 */
public class ControlScreenController {

    @FXML
    private StackPane container;
    @FXML
    private BorderPane scoreBoard;
    @FXML
    private GridPane newCombat;

    @FXML
    private Button combatBtn;
    @FXML
    private Button historyBtn;
    @FXML
    private Button styleBtn;
    @FXML
    private Button helpBtn;
    @FXML
    private Button maximizeBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button combatClock;
    @FXML
    private Button immobilization;
    @FXML
    private Button immobilizationSuspension;

    @FXML
    private GridPane controlDisplay;
    @FXML
    private NewCombatController newCombatFormController;
    @FXML
    private DisplayScreenController controlDisplayController;
    @FXML
    private WinnerScreenController winnerFormController;
    @FXML
    private NoCombatScreenController noCombatScreenController;
    @FXML
    private HistoryController historyScreenController;

    public void init(Controller controller) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.Resource");


        // Creation of the handlers of the controls
        ScreenExitHandler exitHandler = new ScreenExitHandler();
        ControlCombatStartHandler combatHandler = new ControlCombatStartHandler(controller, this.immobilization);
        ControlImmobilizationHandler immobilizationHandler = new ControlImmobilizationHandler(controller, this.combatClock);
        ControlImmobilizationSuspensionHandler suspensionHandler = new ControlImmobilizationSuspensionHandler(controller);
        ControlNewCombatHandler newCombatHandler = new ControlNewCombatHandler(this.newCombatFormController.getContainer());
        ScreenCancelWinningHandler cancelWinningHandler = new ScreenCancelWinningHandler(this.winnerFormController.getContainer());
        ScreenOkWinningHandler okWinningHandler = new ScreenOkWinningHandler(this.winnerFormController, controller);
        ControlHistoryHandler controlHistoryHandler = new ControlHistoryHandler(this.historyScreenController);


        // Adding buttons to the control panel
        setCtrl(controller, true);
        setCtrl(controller, false);

        // Disable main control of the combat until it is initialized
        this.combatClock.disableProperty().bind(Bindings.or(controller.initProperty().not(),
                Bindings.or(controller.immobilizationTimerSuspendedProperty().not(), controller.combatSuspendedProperty())));
        this.immobilization.disableProperty().bind(Bindings.or(controller.initProperty().not(),
                Bindings.or(Bindings.and(controller.combatTimerSuspendedProperty(),
                        controller.immobilizationTimerSuspendedProperty()), controller.combatSuspendedProperty())));

        // Button label
        controller.combatTimerSuspendedProperty().addListener((obs, o, n) -> {
            if (n)
                this.combatClock.setText(resourceBundle.getString("TOOLBAR_clock_resume"));
            else
                this.combatClock.setText(resourceBundle.getString("TOOLBAR_clock_stop"));
        });

        controller.immobilizationTimerSuspendedProperty().addListener((obs, o, n) -> {
            if (n)
                this.immobilization.setText(resourceBundle.getString("TOOLBAR_clock_immobilization"));
            else
                this.immobilization.setText(resourceBundle.getString("TOOLBAR_clock_liberation"));
        });

        // Setting the buttons handlers
        this.exitBtn.setOnMouseClicked(exitHandler);
        this.combatClock.setOnMouseClicked(combatHandler);
        this.immobilization.setOnMouseClicked(immobilizationHandler);
        this.immobilizationSuspension.setOnMouseClicked(suspensionHandler);
        this.combatBtn.setOnMouseClicked(newCombatHandler);
        this.winnerFormController.getCancelBtn().setOnMouseClicked(cancelWinningHandler);
        this.winnerFormController.getOkBtn().setOnMouseClicked(okWinningHandler);
        this.historyBtn.setOnMouseClicked(controlHistoryHandler);

        // Blocking access to controls when there is no combat set
        controller.initProperty().addListener((obs, o, n) -> {
            if (n)
                this.noCombatScreenController.getContainer().toBack();
            else
                this.noCombatScreenController.getContainer().toFront();
        });

        // Showing suspension button only on immobilization
        this.immobilizationSuspension.visibleProperty().bind(Bindings.or(controller.immobilizationTimerSuspendedProperty().not(),
                controller.combatSuspendedProperty()));

        // Initialisation
        this.controlDisplayController.init(controller);
        this.newCombatFormController.init(controller);
        this.winnerFormController.init(controller);
        this.historyScreenController.init(controller);
    }

    private void setCtrl(Controller controller, boolean firstJudoka) {
        // Getting which judoka to add the controls
        ResourceBundle bundle = ResourceBundle.getBundle("resource.Resource");
        HBox judokaBox;
        Text wazaAri;
        Text yuko;
        GridPane row;
        HBox shidoBox;

        if (firstJudoka) {
            judokaBox = this.controlDisplayController.getFirstJudokaPoints();
            wazaAri = this.controlDisplayController.getFirstJudokaWazaAri();
            yuko = this.controlDisplayController.getFirstJudokaYuko();
            row = this.controlDisplayController.getTopRow();
            shidoBox = this.controlDisplayController.getFirstJudokaShido();
        } else {
            judokaBox = this.controlDisplayController.getSecondJudokaPoints();
            wazaAri = this.controlDisplayController.getSecondJudokaWazaAri();
            yuko = this.controlDisplayController.getSecondJudokaYuko();
            row = this.controlDisplayController.getMiddleRow();
            shidoBox = this.controlDisplayController.getSecondJudokaShido();
        }

        // Adding "winner" button
        ControlDeclareWinnerHandler winnerHandler = new ControlDeclareWinnerHandler(controller, firstJudoka, this.winnerFormController);

        VBox judokaWin = new VBox();
        Button winBtn = new Button(bundle.getString("CTRL_winner"));
        winBtn.getStyleClass().add("windowControl");
        winBtn.setPrefWidth(70);
        winBtn.setPrefHeight(70);
        winBtn.setStyle("-fx-font-size: 15px;");
        winBtn.setOnMouseClicked(winnerHandler);
        winBtn.disableProperty().bind(Bindings.or(controller.combatTimerSuspendedProperty().not(),
                Bindings.and(controller.combatTimerSuspendedProperty(), controller.immobilizationTimerSuspendedProperty().not())));

        judokaWin.getChildren().add(winBtn);
        judokaWin.setAlignment(Pos.CENTER);
        row.add(judokaWin, 0, 0, 1, 2);

        // Adding shido controls
        VBox shidoLayout = new VBox(10);
        HBox shidoBtn = new HBox(10);
        Button incShido = new Button("+");
        incShido.getStyleClass().add("windowControl");
        incShido.setPrefWidth(40);
        incShido.setPrefHeight(40);
        incShido.setStyle("-fx-font-size: 20px;");

        Button decShido = new Button("-");
        decShido.getStyleClass().add("windowControl");
        decShido.setPrefWidth(40);
        decShido.setPrefHeight(40);
        decShido.setStyle("-fx-font-size: 20px;");

        shidoBtn.getChildren().addAll(incShido, decShido);
        shidoBtn.setAlignment(Pos.CENTER);
        shidoBtn.setPadding(new Insets(0, 0, 5, 0));
        shidoLayout.getChildren().addAll(shidoBox, shidoBtn);
        shidoLayout.setVgrow(shidoBox, Priority.ALWAYS);
        shidoLayout.setAlignment(Pos.BOTTOM_CENTER);
        row.add(shidoLayout, 2, 0, 1, 2);


        // Adding waza ari and shido controls
        judokaBox.getChildren().clear();

        Button wazaAriInc = new Button();
        wazaAriInc.setText("+");
        wazaAriInc.getStyleClass().add("windowControl");
        wazaAriInc.setPrefWidth(40);
        wazaAriInc.setPrefHeight(40);
        wazaAriInc.setStyle("-fx-font-size: 20px;");

        Button wazaAriDec = new Button();
        wazaAriDec.setText("-");
        wazaAriDec.getStyleClass().add("windowControl");
        wazaAriDec.setPrefWidth(40);
        wazaAriDec.setPrefHeight(40);
        wazaAriDec.setStyle("-fx-font-size: 20px;");

        Button yukoInc = new Button();
        yukoInc.setText("+");
        yukoInc.getStyleClass().add("windowControl");
        yukoInc.setPrefWidth(40);
        yukoInc.setPrefHeight(40);
        yukoInc.setStyle("-fx-font-size: 20px;");

        Button yukoDec = new Button();
        yukoDec.setText("-");
        yukoDec.getStyleClass().add("windowControl");
        yukoDec.setPrefWidth(40);
        yukoDec.setPrefHeight(40);
        yukoDec.setStyle("-fx-font-size: 20px;");

        VBox wazaAriBtn = new VBox(10);
        wazaAriBtn.getChildren().addAll(wazaAriInc, wazaAriDec);
        wazaAriBtn.setAlignment(Pos.CENTER);

        VBox yukoBtn = new VBox(10);
        yukoBtn.getChildren().addAll(yukoInc, yukoDec);
        yukoBtn.setAlignment(Pos.CENTER);

        HBox wazaAriCtrl = new HBox(10);
        wazaAriCtrl.getChildren().add(wazaAriBtn);
        wazaAriCtrl.getChildren().add(wazaAri);
        wazaAriCtrl.setAlignment(Pos.CENTER_RIGHT);

        HBox yukoCtrl = new HBox(10);
        yukoCtrl.getChildren().add(yuko);
        yukoCtrl.getChildren().add(yukoBtn);
        yukoCtrl.setAlignment(Pos.CENTER_RIGHT);

        // Setting handlers
        if (firstJudoka) {
            incShido.setOnMouseClicked((MouseEvent e) -> controller.incFirstJudokaShidoProperty());
            wazaAriInc.setOnMouseClicked((MouseEvent e) -> controller.incFirstJudokaWazaAriProperty());
            yukoInc.setOnMouseClicked((MouseEvent e) -> controller.incFirstJudokaYukoProperty());

            decShido.setOnMouseClicked((MouseEvent e) -> controller.decFirstJudokaShidoProperty());
            wazaAriDec.setOnMouseClicked((MouseEvent e) -> controller.decFirstJudokaWazaAriProperty());
            yukoDec.setOnMouseClicked((MouseEvent e) -> controller.decFirstJudokaYukoProperty());
        } else {
            incShido.setOnMouseClicked((MouseEvent e) -> controller.incSecondJudokaShidoProperty());
            wazaAriInc.setOnMouseClicked((MouseEvent e) -> controller.incSecondJudokaWazaAriProperty());
            yukoInc.setOnMouseClicked((MouseEvent e) -> controller.incSecondJudokaYukoProperty());

            decShido.setOnMouseClicked((MouseEvent e) -> controller.decSecondJudokaShidoProperty());
            wazaAriDec.setOnMouseClicked((MouseEvent e) -> controller.decSecondJudokaWazaAriProperty());
            yukoDec.setOnMouseClicked((MouseEvent e) -> controller.decSecondJudokaYukoProperty());
        }

        judokaBox.getChildren().addAll(wazaAriCtrl, yukoCtrl);
    }
}
