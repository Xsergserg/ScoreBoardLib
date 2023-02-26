import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    final private LocalDateTime creationTime;

    public Game(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.creationTime = LocalDateTime.now();
        this.awayTeamScore = 0;
        this.homeTeamScore = 0;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);
    }
}
