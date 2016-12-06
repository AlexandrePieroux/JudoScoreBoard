package model.dataManager;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.records.CombatRecord;
import model.records.JudokaRecord;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by alexandrepieroux on 27/11/16.
 */
public final class DataManager {

    private static final DataManager instance = new DataManager();

    private Integer idCounter = 0;
    private ObservableList<CombatRecord> combats = FXCollections.observableArrayList();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd-MM-yyyy");
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.Resource");

    private DataManager(){
        super();
    }

    public static DataManager getInstance(){
        return instance;
    }

    public void currentCombatToRecord(Controller controller){
        this.combats.add(getCurrentCombatRecord(controller));
    }

    public ObservableList<CombatRecord> retrieveHistory(){
        return this.combats;
    }

    public void toJsonFile(){
        Gson gson = new Gson();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(this.resourceBundle.getString("data_historyFile"), true);
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            jsonWriter.setIndent("  ");
            jsonWriter.beginArray();

            for(CombatRecord c : this.combats)
                gson.toJson(c, CombatRecord.class, jsonWriter);

            jsonWriter.endArray();
            jsonWriter.close();

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
        currentCombat.setWinnerBy(this.resourceBundle.getString(controller.getWinningCondition()));

        currentCombat.setCategory(controller.getWeight());

        String gender;
        if(controller.genderProperty().get())
            gender = this.resourceBundle.getString("LABEL_male");
        else
            gender = this.resourceBundle.getString("LABEL_female");

        currentCombat.setGender(gender);

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

        currentCombat.setDate(dateFormat.format(System.currentTimeMillis()));
        return currentCombat;
    }
}
