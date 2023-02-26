import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    static String HOME = "home";
    static String GUESTS = "guests";
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game(HOME, GUESTS);
    }

    @Test
    void gameConstructorWithScoresTest() {
        Game gameWithScores = new Game(HOME, GUESTS, 1, 1);
        assertEquals(1, gameWithScores.getHomeTeamScore());
        assertEquals(1, gameWithScores.getAwayTeamScore());
    }

    @Test
    void gameConstructorTest () {
        assertEquals(HOME, game.getHomeTeamName());
        assertEquals(GUESTS, game.getAwayTeamName());
        assertEquals(0, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
        assertNotNull(game.getCreationTime());
    }

    @Test
    void gameEqualityTest() {
        Game sameGame = new Game(HOME, GUESTS);
        Game sameGameAnotherScore = new Game(HOME, GUESTS, 1, 1);
        Game anotherGame = new Game("Argentina", "Germany");
        assertEquals(game, sameGame);
        assertEquals(game, sameGameAnotherScore);
        assertNotEquals(game, anotherGame);
    }
}
