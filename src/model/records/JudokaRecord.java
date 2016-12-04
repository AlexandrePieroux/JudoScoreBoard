package model.records;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;

/**
 * Created by alexandrepieroux on 4/12/16.
 */
public class JudokaRecord {
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getWazaAri() {
        return wazaAri.get();
    }

    public ObservableObjectValue<Integer> wazaAriProperty() {
        return wazaAri.asObject();
    }

    public void setWazaAri(int wazaAri) {
        this.wazaAri.set(wazaAri);
    }

    public int getYuko() {
        return yuko.get();
    }

    public ObservableObjectValue<Integer> yukoProperty() {
        return yuko.asObject();
    }

    public void setYuko(int yuko) {
        this.yuko.set(yuko);
    }

    public int getShido() {
        return shido.get();
    }

    public ObservableObjectValue<Integer> shidoProperty() {
        return shido.asObject();
    }

    public void setShido(int shido) {
        this.shido.set(shido);
    }

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty wazaAri = new SimpleIntegerProperty();
    private SimpleIntegerProperty yuko = new SimpleIntegerProperty();
    private SimpleIntegerProperty shido = new SimpleIntegerProperty();
}
