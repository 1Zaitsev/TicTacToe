package game;

import java.util.Random;

public class GamePlayer {
    private char playerSign;
    private boolean isRealPlayer;

    GamePlayer(char playerSign, boolean isRealPlayer) {
        this.playerSign = playerSign;
        this.isRealPlayer = isRealPlayer;
    }

    char getPlayerSign() {
        return playerSign;
    }

    public boolean isRealPlayer() {
        return isRealPlayer;
    }

    void updateByPlayerData(GameButton button){
        int row = button.getButtonIndex() / GameBoard.dimension;
        int cell = button.getButtonIndex() % GameBoard.dimension;

        button.getBoard().updateGameField(row, cell);

        button.setText(Character.toString(button.getBoard().getGame().getCurrentPlayer().getPlayerSign()));

        if(button.getBoard().checkWin()){
            button.getBoard().getGame().showMessage("You won!");
            button.getBoard().emptyField();
        }
        else
            button.getBoard().getGame().passTurn();
    }

    public void updateByAiData(GameButton button) {
        int maxScoreRow = -1;
        int maxScoreCell = -1;
        int maxScore = 0;

        char [][] map = button.getBoard().getGameField();
        char currentPlayerSign = button.getBoard().getGame().getCurrentPlayer().getPlayerSign();
        int row = -1;
        int cell = -1;

        Random random = new Random();

        for (int i = 0; i < GameBoard.dimension; i++) {
            for (int j = 0; j < GameBoard.dimension; j++) {
                int fieldScore = 0;

                if(map[i][j] == GameBoard.dimension){

                    if(i - 1 >= 0 && j - 1 >= 0 && map[i-1][j-1] == currentPlayerSign)
                        fieldScore++;
                    if(i - 1 >= 0 && map[i-1][j] == currentPlayerSign)
                        fieldScore++;
                    if(i - 1 >= 0 && j + 1 < GameBoard.dimension && map[i-1][j+1] == currentPlayerSign)
                        fieldScore++;
                    if(j - 1 >= 0 && map[i][j-1] == currentPlayerSign)
                        fieldScore++;
                    if(j + 1 < GameBoard.dimension && map[i][j+1] == currentPlayerSign)
                        fieldScore++;
                    if(i + 1 < GameBoard.dimension && j - 1 >= 0 && map[i+1][j-1] == currentPlayerSign)
                        fieldScore++;
                    if(i + 1 < GameBoard.dimension && map[i+1][j] == currentPlayerSign)
                        fieldScore++;
                    if(i + 1 < GameBoard.dimension && j + 1 < GameBoard.dimension && map[i+1][j+1] == currentPlayerSign)
                        fieldScore++;

                }

                if(fieldScore > maxScore){
                    maxScore = fieldScore;
                    maxScoreRow = j;
                    maxScoreCell = i;
                }
            }
        }

        if(maxScoreRow == -1){
            do{
                row = random.nextInt(GameBoard.dimension);
                cell = random.nextInt(GameBoard.dimension);
            }
            while(!button.getBoard().isTurnable(row, cell));
        }

        if(maxScoreRow != -1){
            row = maxScoreRow;
            cell = maxScoreCell;
        }

        button.getBoard().updateGameField(row, cell);

        int cellIndex = GameBoard.dimension * row + cell;
        button.getBoard().getButton(cellIndex).setText(Character.toString(button.getBoard().getGame().getCurrentPlayer().getPlayerSign()));

        if(button.getBoard().checkWin()) {
            button.getBoard().getGame().showMessage("You lose.");
            button.getBoard().emptyField();
        }
        else
            button.getBoard().getGame().passTurn();

    }
}



