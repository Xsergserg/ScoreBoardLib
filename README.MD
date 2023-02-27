# ScoreBoard library
                                                                   
## Description:

Small library provides class ScoreBoard.
Class has one public constructor (without arguments) and 9 public methods:
1. startNewGame(String homeTeamName, String awayTeamName) - create new Game Object and add it to ScoreBoard, returns created Game object.
2. updateScoreByTeamNames(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) - update game score by team's names, returns void. 
3. updateScoreByGameId - update game score by Game id, returns void.
4. finishGameByTeamName(String homeTeamName, String awayTeamName) - finish Game by team's names, returns void.
5. finishGameByGameId(int gameId) - finish Game score by Game id, returns void.
6. getSummaryAsGameList() - return List<Game> - summary of games in progress ordered by their total score, games with the same total score will be returned by the most recently started match in the scoreboard.

Additional functions:

7. getSummaryAsStringList() return List<String> - summary of games as strings in progress ordered by their total score, games with the same total score will be returned by the most recently started match in the scoreboard.       
8. getGameByTeamNames(String homeTeamName, String awayTeamName) - return Game from ScoreBoard by team's name.
9. getGameById(int gameId) - return Game from ScoreBoard by Game id.

Class Game provide only getters as public methods (getHomeTeamName(), getHomeTeamScore(), getAwayTeamName(), getAwayTeamScore(), getId()).

Exception ScoreBoardException is possible in case of errors. 

## Using notes:

For build library from sources execute from root directory:

`./gradlew build`

For import classes:

```
import com.demo.Game;
import com.demo.ScoreBoard;
import com.demo.ScoreBoardException;
```

Library will locate in projectDirectory/build/libs/ScoreBoardLib-1.0.jar

For compile YourProgram.java: 

`javac -cp path/to/file/ScoreBoardLib-1.0.jar YourProgram.java`

For run program:                                                   

`java -cp .:path/to/file/ScoreBoardLib-1.0.jar YourProgram`


## Using example:

### Main.java:

```
import com.demo.ScoreBoard;
import com.demo.ScoreBoardException;

public class Main {
    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startNewGame("Mexico", "Canada");
        scoreBoard.updateScoreByTeamNames("Mexico", "Canada", 0, 5);
        scoreBoard.startNewGame("Spain", "Brazil");
        scoreBoard.updateScoreByTeamNames("Spain", "Brazil", 10, 2);
        scoreBoard.startNewGame("Germany", "France");
        scoreBoard.updateScoreByGameId(2, 2, 2);
        scoreBoard.startNewGame("Uruguay", "Italy");
        scoreBoard.updateScoreByGameId(3, 6, 6);
        scoreBoard.startNewGame("Argentina", "Australia");
        scoreBoard.updateScoreByGameId(4, 3, 1);
        scoreBoard.startNewGame("Romania", "Luxemburg");
        scoreBoard.finishGameByTeamName("Romania", "Luxemburg");
        
        try {
            scoreBoard.finishGameByTeamName("Romania", "Luxemburg");
        } catch (ScoreBoardException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println(scoreBoard.getGameByTeamNames("Mexico", "Canada"));
        System.out.println(scoreBoard.getGameById(0));
        System.out.println(scoreBoard.getSummaryAsGameList());
        
        scoreBoard.getSummaryAsStringList().forEach(System.out::println);
        }
}
```

### Bash commands:

`javac -cp ./ScoreBoardLib-1.0.jar Main.java`

`java -cp .:./ScoreBoardLib-1.0.jar YourProgram`

### Output:

```
Game with specified teams doesn't exist
Mexico 0 - Canada 5
Mexico 0 - Canada 5
[Uruguay 6 - Italy 6, Spain 10 - Brazil 2, Mexico 0 - Canada 5, Argentina 3 - Australia 1, Germany 2 - France 2]
Uruguay 6 - Italy 6
Spain 10 - Brazil 2
Mexico 0 - Canada 5
Argentina 3 - Australia 1
Germany 2 - France 2
```




