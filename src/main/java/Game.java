import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {
    @EqualsAndHashCode.Include
    final private String homeTeamName;
    private int homeTeamScore;
    @EqualsAndHashCode.Include
    final private String awayTeamName;
    private int awayTeamScore;
    private final int id;

    protected Game(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore, int id) {
        this.homeTeamName = homeTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamName = awayTeamName;
        this.awayTeamScore = awayTeamScore;
        this.id = id;
    }

    public Game(String homeTeamName, String awayTeamName, int id) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.awayTeamScore = 0;
        this.homeTeamScore = 0;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);
    }
}
