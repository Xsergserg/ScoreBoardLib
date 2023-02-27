import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {
    @Getter
    private final List <Game> games;
    private int currentGameId;

    public ScoreBoard() {
        this.games = new ArrayList<>();
        this.currentGameId = 0;
    }

    public Game startNewGame(String homeTeamName, String awayTeamName) {
        Game game = new Game(homeTeamName, awayTeamName, currentGameId);
        if (games.contains(game))
            throw new ScoreBoardException("Can't add same game twice");
        games.add(game);
        currentGameId += 1;
        return game;
    }

    public void updateScoreByTeamNames(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        getGameByTeamNames(homeTeamName, awayTeamName).setNewScore(homeTeamScore, awayTeamScore);
    }

    public void updateScoreByGameId(int gameId, int homeTeamScore, int awayTeamScore) {
        getGameById(gameId).setNewScore(homeTeamScore, awayTeamScore);
    }

    public void finishGame(String homeTeamName, String awayTeamName) {
        games.remove(getGameByTeamNames(homeTeamName, awayTeamName));
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

    public Game getGameByTeamNames(String homeTeamName, String awayTeamName) {
        return games.stream()
                .filter(g -> g.getHomeTeamName().equals(homeTeamName) && g.getAwayTeamName().equals(awayTeamName))
                .findFirst()
                .orElseThrow(() -> new ScoreBoardException("Game with specified teams doesn't exist"));
    }

    public Game getGameById(int gameId) {
        return games.stream()
                .filter(g -> g.getId() == gameId)
                .findFirst()
                .orElseThrow(() -> new ScoreBoardException("Game with specified Id doesn't exist"));
    }
}
