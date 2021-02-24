import java.util.HashMap;
import java.util.Map;

public class TennisGame2 implements TennisGame {
    private int player1Points = 0;
    private int player2Points = 0;

    private String player1Score = "";
    private String player2Score = "";
    private String playerName1;
    private String playerName2;

    private static final Map<Integer, String> scores = new HashMap();

    static{
        scores.put(0, "Love");
        scores.put(1, "Fifteen");
        scores.put(2, "Thirty");
        scores.put(3, "Forty");
    }

    public TennisGame2(String playerName1, String playerName2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
    }

    public String getScore(){

        if (this.isDraw()) {
            return this.getDraws();
        }

        if (this.isNotOverTime()) {
            return this.getPartialScore();
        }

        if(this.isOverTime()){
            return this.getAdvantage();
        }

        return this.getWinner();
    }

    public void wonPoint(String player) {
        if (player.equals(playerName1))
            incrementplayer1Score();
        else
            incrementPlayer2Score();
    }

    private void incrementplayer1Score(){
        player1Points++;
    }

    private void incrementPlayer2Score(){
        player2Points++;
    }

    private String getDraws(){
        return this.player1Points < 3? getPlayerScore(player1Points) + "-All": "Deuce";
    }

    private String getPlayerScore(int points){
        return scores.get(points);
    }

    private String getPartialScore(){
        this.player1Score = this.getPlayerScore(this.player1Points);
        this.player2Score = this.getPlayerScore(this.player2Points);

        return this.player1Score + "-" + this.player2Score;
    }

    private String getAdvantage(){
        return this.player1Points > this.player2Points? "Advantage " + this.playerName1 : "Advantage " + this.playerName2;
    }

    private String getWinner(){
        return this.player1Points > this.player2Points? "Win for " + this.playerName1 : "Win for " + this.playerName2;
    }

    private boolean isNotOverTime(){
        return (this.player1Points <= 3 && this.player2Points <= 3);
    }

    private boolean isOverTime(){
        boolean minimumPointsCondition = this.player1Points >= 3 || this.player2Points >=3;
        boolean pointDifferenceCondition = Math.abs(this.player1Points - this.player2Points) == 1;

        return minimumPointsCondition && pointDifferenceCondition;
    }


    private boolean isDraw(){
        return this.player1Points == this.player2Points;
    }
}