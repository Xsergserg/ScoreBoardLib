import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    @Getter
    private List <Game> games = new ArrayList<>();

    public Game startNewGame(String homeTeamName, String awayTeamName) {
        return null;
    }

    public void updateScore(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
    }

    public void finishGame(String homeTeamName, String awayTeamName) {}

    public List<Game> getSummeryAsGameList() {
        return null;
    }

    public List<String> getSummaryAsStringList() {
        return null;
    }
}
