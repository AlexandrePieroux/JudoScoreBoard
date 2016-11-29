package model.DataManager;

import controller.Controller;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import org.json.*;
import java.util.ResourceBundle;

/**
 * Created by alexandrepieroux on 27/11/16.
 */
public final class DataManager {
    private static final DataManager instance = new DataManager();

    private BufferedWriter fileWriter;
    private BufferedReader fileReader;
    private ResourceBundle resourceBundle;
    private Integer idCounter = 0;

    private DataManager(){
        super();
        this.resourceBundle = ResourceBundle.getBundle("resource.Resource");

        FileWriter fileWriter;
        FileReader fileReader;
        try {
            fileWriter = new FileWriter(this.resourceBundle.getString("data_historyFile"), true);
            fileReader = new FileReader(this.resourceBundle.getString("data_historyFile"));
            this.fileWriter = new BufferedWriter(fileWriter);
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

    public void winnerJudokaToDataFile(Controller controller){
        JSONObject fight = new JSONObject();
        JSONObject winnerJudoka = new JSONObject();
        try {
            fight.put("id", this.idCounter++ );
            winnerJudoka.put("name", controller.winnerJudokaNameProperty().get());
            winnerJudoka.put("wazaAri", controller.winnerJudokaWazaAriProperty().get());
            winnerJudoka.put("yuko", controller.winnerJudokaYukoProperty().get());
            winnerJudoka.put("shido", controller.winnerJudokaShidoProperty().get());
            fight.put("winnerJudoka", winnerJudoka);
            fight.put("timeLeft", controller.combatTimerMinutesProperty().get() * 60 +
                    controller.combatTimerSecondsProperty().get());
            fight.put("immobilizationTime", controller.immobilizationTimerScecondProperty().get());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public LinkedList<JSONObject> getHistory(){
        return null;
    }
}
