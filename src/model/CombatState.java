package model;

import controller.clockTimer.ClockTimer;
import javafx.beans.property.*;

/**
 * Created by alexp on 03-11-16.
 */
public final class CombatState {

    private static final CombatState instance = new CombatState();

    private final Judoka firstJudoka = new Judoka(null);
    private final Judoka secondJudoka = new Judoka(null);

    private ReadOnlyBooleanWrapper maleGender = new ReadOnlyBooleanWrapper(this, "maleGender", true);
    private ReadOnlyIntegerWrapper weight = new ReadOnlyIntegerWrapper(this, "category", 0);

    private ClockTimer combatTimer = new ClockTimer();
    private ClockTimer immobilizationTimer = new ClockTimer();

    private ReadOnlyBooleanWrapper combatSuspended = new ReadOnlyBooleanWrapper(this, "combatSuspended", false);

    private Judoka winner = null;


    // Getter and setter
    public Judoka getFirstJudoka() {
        return firstJudoka;
    }

    public Judoka getSecondJudoka() {
        return secondJudoka;
    }

    public Judoka getWinner() {
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



    public void setWinner(Judoka winner) {
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
