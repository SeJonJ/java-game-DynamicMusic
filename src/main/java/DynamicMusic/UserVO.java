package DynamicMusic;

public class UserVO {
    String name;
    int scoreDF;
    int scoreLYS;
    int scoreTFR;

    public UserVO(String name, int score){
        this.name = name;
        this.scoreTFR = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScoreDF() {
        return scoreDF;
    }

    public void setScoreDF(int scoreDF) {
        this.scoreDF = scoreDF;
    }

    public int getScoreLYS() {
        return scoreLYS;
    }

    public void setScoreLYS(int scoreLYS) {
        this.scoreLYS = scoreLYS;
    }

    public int getScoreTFR() {
        return scoreTFR;
    }

    public void setScoreTFR(int scoreTFR) {
        this.scoreTFR = scoreTFR;
    }
}
