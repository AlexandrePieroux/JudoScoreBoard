package model.records;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;

/**
 * Created by alexandrepieroux on 4/12/16.
 */
public class CombatRecord {
    public int getId() {
        return id.get();
    }

    public ObservableObjectValue<Integer> idProperty() {
        return id.asObject();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getRemainingTime() {
        return remainingTime.get();
    }

    public ObservableObjectValue<Integer> remainingTimeProperty() {
        return remainingTime.asObject();
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime.set(remainingTime);
    }

    public String getWinnerName() {
        return winnerName.get();
    }

    public SimpleStringProperty winnerNameProperty() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName.set(winnerName);
    }

    public String getWinnerBy() {
        return winnerBy.get();
    }

    public SimpleStringProperty winnerByProperty() {
        return winnerBy;
    }

    public void setWinnerBy(String winnerBy) {
        this.winnerBy.set(winnerBy);
    }

    public JudokaRecord getFirstJudoka() {
        return firstJudoka;
    }

    public void setFirstJudoka(JudokaRecord firstJudoka) {
        this.firstJudoka = firstJudoka;
    }

    public JudokaRecord getSecondJudoka() {
        return secondJudoka;
    }

    public void setSecondJudoka(JudokaRecord secondJudoka) {
        this.secondJudoka = secondJudoka;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty remainingTime =  new SimpleIntegerProperty();

    private SimpleStringProperty winnerName = new SimpleStringProperty();
    private SimpleStringProperty winnerBy = new SimpleStringProperty();

    private SimpleStringProperty date = new SimpleStringProperty();

    private JudokaRecord firstJudoka;
    private JudokaRecord secondJudoka;
}
