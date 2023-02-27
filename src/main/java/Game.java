import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {
    @EqualsAndHashCode.Include
    private final String homeTeamName;
    private int homeTeamScore;
    @EqualsAndHashCode.Include
    private final String awayTeamName;
    private int awayTeamScore;
    private final int id;

    protected Game(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore, int id) {
        checkTeamNames(homeTeamName, awayTeamName);
        this.homeTeamName = homeTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamName = awayTeamName;
        this.awayTeamScore = awayTeamScore;
        this.id = id;
    }

    protected Game(String homeTeamName, String awayTeamName, int id) {
        checkTeamNames(homeTeamName, awayTeamName);
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.awayTeamScore = 0;
        this.homeTeamScore = 0;
        this.id = id;
    }

    protected void setNewScore (int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore < 0 || awayTeamScore < 0)
            throw new ScoreBoardException("Score can't be negative value");
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    private void checkTeamNames (String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || homeTeamName.isEmpty() || awayTeamName == null || awayTeamName.isEmpty())
            throw new ScoreBoardException("Team name can't be null or empty field");
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);
    }
}
