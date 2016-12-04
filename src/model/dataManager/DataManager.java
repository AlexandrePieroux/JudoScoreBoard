package model.dataManager;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import controller.Controller;
import model.records.CombatRecord;
import model.records.JudokaRecord;

import java.io.*;

import java.util.ResourceBundle;

/**
 * Created by alexandrepieroux on 27/11/16.
 */
public final class DataManager {
    private static final DataManager instance = new DataManager();

    private JsonWriter fileWriter;
    private BufferedReader fileReader;
    private ResourceBundle resourceBundle;
    private Integer idCounter = 0;
    private Gson gson = new Gson();

    private DataManager(){
        super();
        this.resourceBundle = ResourceBundle.getBundle("resource.Resource");
        FileWriter fileWriter;
        FileReader fileReader;
        try {
            fileWriter = new FileWriter(this.resourceBundle.getString("data_historyFile"), true);
            fileReader = new FileReader(this.resourceBundle.getString("data_historyFile"));
            this.fileWriter = new JsonWriter(fileWriter);
            this.fileWriter.setIndent("  ");
            this.fileReader = new BufferedReader(fileReader);

        } catch (IOException e) {
            e.printStackTrace();
            this.fileWriter = null;
            this.fileReader = null;
        }
    }

    public static DataManager getInstance(){
        return instance;
    }

    public void currentCombatToRecord(Controller controller){
        this.gson.toJson(getCurrentCombatRecord(controller), CombatRecord.class, this.fileWriter);
        try {
            this.fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CombatRecord getCurrentCombatRecord(Controller controller){
        CombatRecord currentCombat = new CombatRecord();
        currentCombat.setId(this.idCounter++);
        currentCombat.setRemainingTime(controller.combatTimerMinutesProperty().get() * 60 +
                                       controller.combatTimerSecondsProperty().get());

        currentCombat.setWinnerName(controller.winnerJudokaNameProperty().get());
        currentCombat.setWinnerBy(controller.getWinningCondition());

        JudokaRecord firstJudoka = new JudokaRecord();
        JudokaRecord secondJudoka = new JudokaRecord();

        firstJudoka.setName(controller.firstJudokaNameProperty().get());
        firstJudoka.setWazaAri(controller.firstJudokaWazaAriProperty().get());
        firstJudoka.setYuko(controller.firstJudokaYukoProperty().get());
        firstJudoka.setShido(controller.firstJudokaShidoProperty().get());

        secondJudoka.setName(controller.secondJudokaNameProperty().get());
        secondJudoka.setWazaAri(controller.secondJudokaWazaAriProperty().get());
        secondJudoka.setYuko(controller.secondJudokaYukoProperty().get());
        secondJudoka.setShido(controller.secondJudokaShidoProperty().get());

        currentCombat.setFirstJudoka(firstJudoka);
        currentCombat.setSecondJudoka(secondJudoka);
        return currentCombat;
    }
}
