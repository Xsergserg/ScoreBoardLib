import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScoreBoard {
    @Getter
    private final List <Game> games = new ArrayList<>();
    private int currentGameId = 0;

    public Game startNewGame(String homeTeamName, String awayTeamName) {
        checkTeamNames(homeTeamName, awayTeamName);
        Game game = new Game(homeTeamName, awayTeamName, currentGameId);
        if (games.contains(game))
            throw new ScoreBoardException("Can't add same game twice");
        games.add(game);
        currentGameId += 1;
        return game;
    }

    public void updateScore(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        checkTeamNames(homeTeamName, awayTeamName);
        if (homeTeamScore < 0 || awayTeamScore < 0)
            throw new ScoreBoardException("Score can't be negative value");
        Optional<Game> game = getGame(homeTeamName, awayTeamName);
        checkGameForExisting(game);
        game.get().setHomeTeamScore(homeTeamScore);
        game.get().setAwayTeamScore(awayTeamScore);
    }

    public void finishGame(String homeTeamName, String awayTeamName) {
        checkTeamNames(homeTeamName, awayTeamName);
        Optional<Game> game = getGame(homeTeamName, awayTeamName);
        checkGameForExisting(game);
        games.remove(game.get());
    }

    public List<Game> getSummaryAsGameList() {
        games.sort(Comparator.comparing((Game game) -> game.getHomeTeamScore() + game.getAwayTeamScore())
                .thenComparing(Game::getId)
                .reversed());
        return games;
    }

    public List<String> getSummaryAsStringList() {
        return getSummaryAsGameList().stream().map(Game::toString).collect(Collectors.toList());
    }

    public Optional<Game> getGame(String homeTeamName, String awayTeamName) {
        return games.stream().filter(g -> g.getHomeTeamName().equals(homeTeamName) && g.getAwayTeamName().equals(awayTeamName)).findFirst();
    }

    public void checkTeamNames (String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || homeTeamName.isEmpty() || awayTeamName == null || awayTeamName.isEmpty())
            throw new ScoreBoardException("Team name can't be null or empty field");
    }

    private void checkGameForExisting(Optional<Game> game) {
        if (!game.isPresent())
            throw new ScoreBoardException("Game with specified teams doesn't exist");
    }
}
