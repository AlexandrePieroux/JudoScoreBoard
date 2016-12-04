package model.states;

import controller.clockTimer.ClockTimer;
import javafx.beans.property.*;

/**
 * Created by alexp on 03-11-16.
 */
public final class CombatState {

    private static final CombatState instance = new CombatState();

    private final JudokaState firstJudoka = new JudokaState(null);
    private final JudokaState secondJudoka = new JudokaState(null);

    private ReadOnlyBooleanWrapper maleGender = new ReadOnlyBooleanWrapper(this, "maleGender", true);
    private ReadOnlyIntegerWrapper weight = new ReadOnlyIntegerWrapper(this, "category", 0);

    private ClockTimer combatTimer = new ClockTimer();
    private ClockTimer immobilizationTimer = new ClockTimer();

    private ReadOnlyBooleanWrapper combatSuspended = new ReadOnlyBooleanWrapper(this, "combatSuspended", false);

    private JudokaState winner = null;


    // Getter and setter
    public JudokaState getFirstJudoka() {
        return firstJudoka;
    }

    public JudokaState getSecondJudoka() {
        return secondJudoka;
    }

    public JudokaState getWinner() {
        return winner;
    }

    public ClockTimer getCombatTimer() {
        return combatTimer;
    }

    public ClockTimer getImmobilizationTimer() {
        return immobilizationTimer;
    }


    // Properties
    public ReadOnlyBooleanWrapper maleGenderProperty() {
        return maleGender;
    }

    public ReadOnlyIntegerWrapper weightProperty(){
        return weight;
    }

    public ReadOnlyBooleanWrapper combatSuspendedProperty(){
        return combatSuspended;
    }



    public void setWinner(JudokaState winner) {
        this.winner = winner;
    }

    // Constructor
    private CombatState(){
        this.combatTimer.start();
        this.immobilizationTimer.setChronometer();
        this.immobilizationTimer.start();
    }

    public static CombatState getInstance(){
        return instance;
    }
}
