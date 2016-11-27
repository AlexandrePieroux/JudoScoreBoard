package model;

import javafx.beans.property.*;

/**
 * Created by alexp on 05-11-16.
 */
public class Judoka {

    ReadOnlyStringWrapper name = new ReadOnlyStringWrapper(this, "nameProperty");
    ReadOnlyIntegerWrapper wazaAri = new ReadOnlyIntegerWrapper(this, "wazaAriProperty");
    ReadOnlyIntegerWrapper yuko = new ReadOnlyIntegerWrapper(this, "yukoProperty");
    ReadOnlyIntegerWrapper shido = new ReadOnlyIntegerWrapper(this, "shidoProperty");

    public ReadOnlyStringWrapper nameProperty() {
        return name;
    }

    public ReadOnlyIntegerWrapper wazaAriProperty() {
        return wazaAri;
    }

    public ReadOnlyIntegerWrapper yukoProperty() {
        return yuko;
    }

    public ReadOnlyIntegerWrapper shidoProperty() {
        return shido;
    }

    public Judoka(String name){
        this.name.setValue(name);
    }

    public void reset(){
        this.name.setValue(null);
        this.wazaAri.setValue(0);
        this.yuko.setValue(0);
        this.shido.setValue(0);
    }
}
