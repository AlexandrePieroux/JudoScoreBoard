package controller;

import javafx.beans.property.*;
import model.states.CombatState;
import model.dataManager.DataManager;

/**
 * Created by alexp on 26-10-16.
 */
public final class Controller {

    // Members
    private static final Controller instance = new Controller();
    private CombatState state = CombatState.getInstance();
    private ReadOnlyBooleanWrapper init = new ReadOnlyBooleanWrapper(this, "init", false);
    private String winningCondition = "";

    // Properties
    // First judoka
    public ReadOnlyStringProperty firstJudokaNameProperty(){
        return this.state.getFirstJudoka().nameProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty firstJudokaYukoProperty(){
        return this.state.getFirstJudoka().yukoProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty firstJudokaWazaAriProperty(){
        return this.state.getFirstJudoka().wazaAriProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty firstJudokaShidoProperty(){
        return this.state.getFirstJudoka().shidoProperty();
    }

    // Second judoka
    public ReadOnlyStringProperty secondJudokaNameProperty(){
        return this.state.getSecondJudoka().nameProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty secondJudokaYukoProperty(){
        return this.state.getSecondJudoka().yukoProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty secondJudokaWazaAriProperty(){
        return this.state.getSecondJudoka().wazaAriProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty secondJudokaShidoProperty(){
        return this.state.getSecondJudoka().shidoProperty().getReadOnlyProperty();
    }

    //Winner judoka
    public ReadOnlyStringProperty winnerJudokaNameProperty(){
        if(this.state.getWinner() != null)
            return this.state.getWinner().nameProperty();
        else
            return null;
    }

    public ReadOnlyIntegerProperty winnerJudokaYukoProperty(){
        if(this.state.getWinner() != null)
            return this.state.getWinner().yukoProperty().getReadOnlyProperty();
        else
            return null;
    }

    public ReadOnlyIntegerProperty winnerJudokaWazaAriProperty(){
        if(this.state.getWinner() != null)
            return this.state.getWinner().wazaAriProperty().getReadOnlyProperty();
        else
            return null;
    }

    public ReadOnlyIntegerProperty winnerJudokaShidoProperty(){
        if(this.state.getWinner() != null)
            return this.state.getWinner().shidoProperty().getReadOnlyProperty();
        else
            return null;
    }


    // State
    public ReadOnlyBooleanProperty initProperty() {
        return init.getReadOnlyProperty();
    }

    public ReadOnlyBooleanProperty genderProperty(){
        return this.state.maleGenderProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty weightProperty() {
        return this.state.weightProperty().getReadOnlyProperty();
    }

    public ReadOnlyBooleanProperty combatSuspendedProperty() {
        return this.state.combatSuspendedProperty().getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty combatTimerMinutesProperty(){
        return this.state.getCombatTimer().readOnlyMinutesProperty();
    }

    public ReadOnlyIntegerProperty combatTimerSecondsProperty(){
        return this.state.getCombatTimer().readOnlySecondsProperty();
    }

    public ReadOnlyBooleanProperty combatTimerSuspendedProperty(){
        return this.state.getCombatTimer().readOnlySuspendedProperty();
    }

    public ReadOnlyIntegerProperty immobilizationTimerScecondProperty(){
        return this.state.getImmobilizationTimer().readOnlySecondsProperty();
    }

    public ReadOnlyBooleanProperty immobilizationTimerSuspendedProperty(){
        return this.state.getImmobilizationTimer().readOnlySuspendedProperty();
    }


    // Operations
    public void incFirstJudokaWazaAriProperty(){
        if(this.state.getFirstJudoka().wazaAriProperty().get() < 1)
            this.state.getFirstJudoka().wazaAriProperty().set(this.state.getFirstJudoka().wazaAriProperty().get() + 1);
    }

    public void decFirstJudokaWazaAriProperty() {
        if(this.state.getFirstJudoka().wazaAriProperty().get() > 0)
            this.state.getFirstJudoka().wazaAriProperty().set(this.state.getFirstJudoka().wazaAriProperty().get() - 1);
    }

    public void incFirstJudokaYukoProperty(){
        this.state.getFirstJudoka().yukoProperty().set(firstJudokaYukoProperty().get() + 1);
    }

    public void decFirstJudokaYukoProperty() {
        if(this.state.getFirstJudoka().yukoProperty().get() > 0)
            this.state.getFirstJudoka().yukoProperty().set(this.state.getFirstJudoka().yukoProperty().get() - 1);
    }

    public void incFirstJudokaShidoProperty(){
        if(this.state.getFirstJudoka().shidoProperty().get() < 3)
            this.state.getFirstJudoka().shidoProperty().set(this.state.getFirstJudoka().shidoProperty().get() + 1);
    }

    public void decFirstJudokaShidoProperty() {
        if(this.state.getFirstJudoka().shidoProperty().get() > 0)
            this.state.getFirstJudoka().shidoProperty().set(this.state.getFirstJudoka().shidoProperty().get() - 1);
    }


    public void incSecondJudokaWazaAriProperty(){
        if(this.state.getSecondJudoka().wazaAriProperty().get() < 1)
            this.state.getSecondJudoka().wazaAriProperty().set(this.state.getSecondJudoka().wazaAriProperty().get() + 1);
    }

    public void decSecondJudokaWazaAriProperty() {
        if(this.state.getSecondJudoka().wazaAriProperty().get() > 0)
            this.state.getSecondJudoka().wazaAriProperty().set(this.state.getSecondJudoka().wazaAriProperty().get() - 1);
    }

    public void incSecondJudokaYukoProperty(){
        this.state.getSecondJudoka().yukoProperty().set(this.state.getSecondJudoka().yukoProperty().get() + 1);
    }

    public void decSecondJudokaYukoProperty() {
        if(this.state.getSecondJudoka().yukoProperty().get() > 0)
            this.state.getSecondJudoka().yukoProperty().set(this.state.getSecondJudoka().yukoProperty().get() - 1);
    }

    public void incSecondJudokaShidoProperty(){
        if(this.state.getSecondJudoka().shidoProperty().get() < 3)
            this.state.getSecondJudoka().shidoProperty().set(this.state.getSecondJudoka().shidoProperty().get() + 1);
    }

    public void decSecondJudokaShidoProperty() {
        if(this.state.getSecondJudoka().shidoProperty().get() > 0)
            this.state.getSecondJudoka().shidoProperty().set(this.state.getSecondJudoka().shidoProperty().get() - 1);
    }


    public void combatTimerPause(){
        this.state.getCombatTimer().pause();
    }

    public void combatTimerResume(){
        this.state.getCombatTimer().resume();
    }

    public void combatTimerReset(){
        this.state.getCombatTimer().reset();
    }

    public void combatTimerSetTime(long time) {
        this.state.getCombatTimer().setTime(time);
    }

    public void immobilizationTimerPause(){
        this.state.getImmobilizationTimer().pause();
    }

    public void immobilizationTimerResume(){
        this.state.getImmobilizationTimer().resume();
    }

    public void immobilizationTimerReset(){
        this.state.getImmobilizationTimer().reset();
    }

    public void immobilizationTimerSetTime(long time){
        this.state.getImmobilizationTimer().setTime(time);
    }

    public void suspendCombat(){
        this.state.combatSuspendedProperty().setValue(true);
    }

    public void resumeCombat(){
        this.state.combatSuspendedProperty().setValue(false);
    }

    public void setFirstJudokaWinner(){
        this.state.setWinner(this.state.getFirstJudoka());
    }

    public void setSecondJudokaWinner(){
        this.state.setWinner(this.state.getSecondJudoka());
    }

    public void setMaleGender(){
        this.state.maleGenderProperty().setValue(true);
    }

    public void setFemaleGender(){
        this.state.maleGenderProperty().setValue(false);
    }

    public void setWeight(int weight){
        this.state.weightProperty().setValue(weight);
    }


    // Private constructor
    private Controller (){
        super();
    }

    public static Controller getInstance(){
        return instance;
    }


    // New Combat
    public void newCombat(String firstJudokaName, String secondJudoka){
        this.state.getFirstJudoka().reset();
        this.state.getFirstJudoka().nameProperty().setValue(firstJudokaName);

        this.state.getSecondJudoka().reset();
        this.state.getSecondJudoka().nameProperty().setValue(secondJudoka);

        this.state.setWinner(null);
        this.init.set(true);
    }

    public void combatToHistory() {
        DataManager.getInstance().currentCombatToRecord(this);
    }

    public void setWinningCondition(String winningCondition) {
        this.winningCondition = winningCondition;
    }

    public String getWinningCondition() {
        return winningCondition;
    }
}
