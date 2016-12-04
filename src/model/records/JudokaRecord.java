package model.records;

/**
 * Created by alexandrepieroux on 4/12/16.
 */
public class JudokaRecord {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWazaAri() {
        return wazaAri;
    }

    public void setWazaAri(int wazaAri) {
        this.wazaAri = wazaAri;
    }

    public int getYuko() {
        return yuko;
    }

    public void setYuko(int yuko) {
        this.yuko = yuko;
    }

    public int getShido() {
        return shido;
    }

    public void setShido(int shido) {
        this.shido = shido;
    }

    private String name;
    private int wazaAri;
    private int yuko;
    private int shido;
}
