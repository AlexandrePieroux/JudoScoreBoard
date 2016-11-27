package controller.clockTimer;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;

/**
 * Created by alexp on 03-11-16.
 */
public class ClockTimer {

    private ClockTimerThread clock;

    public boolean isSuspended() {
        return this.clock.isSuspended();
    }

    public ReadOnlyBooleanProperty readOnlySuspendedProperty() {
        return this.clock.suspendedProperty();
    }

    public boolean isActive() {
        return this.clock.isActive();
    }

    public ReadOnlyBooleanProperty readOnlyActiveProperty() {
        return this.clock.activeProperty();
    }

    public ReadOnlyIntegerProperty readOnlyMinutesProperty() {
        return this.clock.minutesProperty();
    }

    public ReadOnlyIntegerProperty readOnlySecondsProperty() {
        return this.clock.secondsProperty();
    }

    public void setTime(long time){
        this.clock.setTime(time);
    }

    public void setChronometer(){
        this.clock.setChronometer();
    }

    public void start(){
        this.clock.start();
    }

    public void pause() {
        this.clock.timerPause();
    }

    public void resume() {
        this.clock.timerResume();
    }

    public synchronized void reset() {
        this.clock.resetTimer();
    }

    public void stop() {
        this.clock.timerStop();
    }


    public ClockTimer(){
        this.clock = new ClockTimerThread();
        this.clock.setDaemon(true);
        this.clock.setPriority(Thread.MAX_PRIORITY);
    }

}
