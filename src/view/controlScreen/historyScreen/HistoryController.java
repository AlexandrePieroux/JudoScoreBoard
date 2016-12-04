package view.controlScreen.historyScreen;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.records.CombatRecord;
import view.controlScreen.historyScreen.historyHandler.HistoryOkHandler;

/**
 * Created by alexp on 11-11-16.
 */
public class HistoryController {

    @FXML private GridPane container;
    @FXML private TableView table;
    @FXML private TableColumn<CombatRecord, Integer> id;
    @FXML private TableColumn<CombatRecord, String> date;
    @FXML private TableColumn<CombatRecord, String > winner;
    @FXML private TableColumn<CombatRecord, String> firstJudokaName;
    @FXML private TableColumn<CombatRecord, Integer> firstJudokaWazaAri;
    @FXML private TableColumn<CombatRecord, Integer> firstJudokaYuko;
    @FXML private TableColumn<CombatRecord, Integer> firstJudokaShido;
    @FXML private TableColumn<CombatRecord, String> secondJudokaName;
    @FXML private TableColumn<CombatRecord, Integer> secondJudokaWazaAri;
    @FXML private TableColumn<CombatRecord, Integer> secondJudokaYuko;
    @FXML private TableColumn<CombatRecord, Integer> secondJudokaShido;
    @FXML private TableColumn<CombatRecord, Integer> remainingTime;
    @FXML private Button okBtn;

    public GridPane getContainer(){
        return this.container;
    }

    public void init(Controller controller){

        this.id.setCellValueFactory(r -> r.getValue().idProperty());
        this.date.setCellValueFactory(r -> r.getValue().dateProperty());
        this.winner.setCellValueFactory(r -> r.getValue().winnerNameProperty());

        this.firstJudokaName.setCellValueFactory(r -> r.getValue().getFirstJudoka().nameProperty());
        this.firstJudokaWazaAri.setCellValueFactory(r -> r.getValue().getFirstJudoka().wazaAriProperty());
        this.firstJudokaYuko.setCellValueFactory(r -> r.getValue().getFirstJudoka().yukoProperty());
        this.firstJudokaShido.setCellValueFactory(r -> r.getValue().getFirstJudoka().shidoProperty());

        this.secondJudokaName.setCellValueFactory(r -> r.getValue().getSecondJudoka().nameProperty());
        this.secondJudokaWazaAri.setCellValueFactory(r -> r.getValue().getFirstJudoka().wazaAriProperty());
        this.secondJudokaYuko.setCellValueFactory(r -> r.getValue().getSecondJudoka().yukoProperty());
        this.secondJudokaShido.setCellValueFactory(r -> r.getValue().getSecondJudoka().shidoProperty());

        this.remainingTime.setCellValueFactory(r -> r.getValue().remainingTimeProperty());

        this.table.setItems(controller.getHistory());
        HistoryOkHandler handler = new HistoryOkHandler(this);
        this.okBtn.setOnMouseClicked(handler);
    }
}
