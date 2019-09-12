package game;

import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playersTurn;

    public Game() {
        this.board = new GameBoard(this);
        playersTurn = 0;
    }

    public void initGame(){
        gamePlayers[0] = new GamePlayer('X', true);
        gamePlayers[1] = new GamePlayer('O', false);

    }

    void passTurn(){
        if(playersTurn == 0)
            playersTurn = 1;
        else
            playersTurn = 0;
    }

    GamePlayer getCurrentPlayer(){
        return gamePlayers[playersTurn];
    }

    void showMessage(String textMessage){
        JOptionPane.showMessageDialog(board, textMessage);
    }
}

