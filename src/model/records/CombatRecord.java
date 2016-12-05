package model.records;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;

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

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public int getCategory() {
        return category.get();
    }

    public ObservableObjectValue<Integer> categoryProperty() {
        return category.asObject();
    }

    public void setCategory(int category) {
        this.category.set(category);
    }


    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty remainingTime =  new SimpleIntegerProperty();

    private SimpleStringProperty gender = new SimpleStringProperty();

    private SimpleIntegerProperty category = new SimpleIntegerProperty();

    private SimpleStringProperty winnerName = new SimpleStringProperty();
    private SimpleStringProperty winnerBy = new SimpleStringProperty();

    private SimpleStringProperty date = new SimpleStringProperty();

    private JudokaRecord firstJudoka;
    private JudokaRecord secondJudoka;
}
