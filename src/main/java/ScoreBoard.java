import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private List <String> games = new ArrayList<>();

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

    public Game getGame(String homeTeamName, String awayTeamName) {
        return null;
    }
}
