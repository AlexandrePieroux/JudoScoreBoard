package controller.clockTimer;

import javafx.beans.property.*;

/**
 * Created by alexp on 06-11-16.
 */
public class ClockTimerThread extends Thread{

    private ReadOnlyBooleanWrapper suspended = new ReadOnlyBooleanWrapper(this, "suspended", true);
    private ReadOnlyBooleanWrapper active = new ReadOnlyBooleanWrapper(this, "active", true);
    private ReadOnlyIntegerWrapper seconds = new ReadOnlyIntegerWrapper(this, "minutes");
    private ReadOnlyIntegerWrapper minutes = new ReadOnlyIntegerWrapper(this, "seconds");

    private long time;
    private long elapsedTime = 0;
    private long timeLimit;
    private boolean increase = false;
    private boolean init = false;

    public ReadOnlyIntegerProperty secondsProperty() {
        return seconds.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty minutesProperty() {
        return minutes.getReadOnlyProperty();
    }

    public ReadOnlyBooleanProperty suspendedProperty() {
        return suspended.getReadOnlyProperty();
    }

    public ReadOnlyBooleanProperty activeProperty() {
        return active.getReadOnlyProperty();
    }


    public boolean isSuspended() {
        return suspended.get();
    }

    public boolean isActive() {
        return active.get();
    }


    public synchronized void setChronometer(){
        this.suspended.setValue(true);
        this.increase = true;
        this.time = 0;
    }

    public synchronized void setTime(long time){
        this.suspended.setValue(true);
        if(this.increase) {
            this.time = 0;
            this.timeLimit = time;
            this.minutes.set(0);
            this.seconds.set(0);
        } else {
            this.time = time;
            this.timeLimit = time;
            this.minutes.set((int) time / 60000);
            this.seconds.set(((int) time / 1000) % 60);
        }
        this.init = true;
    }

    public synchronized void resetTimer(){
        setTime(this.timeLimit);
    }

    public synchronized void timerPause(){
        this.suspended.setValue(true);
    }

    public synchronized void timerResume(){
        this.suspended.setValue(false);
        notify();
    }

    public synchronized void timerStop(){
        this.active.setValue(false);
    }


    @Override
    public void run(){
        long loopTime = System.currentTimeMillis();
        while(this.isActive()) {
            try {
                synchronized (this) {
                    while (this.isSuspended() || !this.init) {
                        wait();
                        loopTime = System.currentTimeMillis();
                    }
                }
                sleep(50);

                this.elapsedTime += System.currentTimeMillis() - loopTime;
                loopTime = System.currentTimeMillis();
                if(this.elapsedTime >= 1000) {
                    updateTime();
                    this.elapsedTime = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTime(){
        if(this.increase)
            this.time += this.elapsedTime;
        else
            this.time -= this.elapsedTime;

        if(this.time < 0){
            this.time = 0;
            this.timerPause();
        } else if(this.increase && (this.time >= this.timeLimit)){
            this.time = this.timeLimit;
            this.timerPause();
        }

        this.minutes.set((int)this.time/60000);
        this.seconds.set(((int)this.time/1000)%60);
    }
}
