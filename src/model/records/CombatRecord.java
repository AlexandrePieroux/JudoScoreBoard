package model.records;

/**
 * Created by alexandrepieroux on 4/12/16.
 */
public class CombatRecord {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinnerBy() {
        return winnerBy;
    }

    public void setWinnerBy(String winnerBy) {
        this.winnerBy = winnerBy;
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

    private int remainingTime;

    private String winnerName;
    private String winnerBy;

    private JudokaRecord firstJudoka;
    private JudokaRecord secondJudoka;
}
