package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    private Game game;

    static int dimension = 3;
    static int cellSize = 150;

    private char[][] gameField;
    private GameButton[] gameButtons;

    static char nullSymbol = '\u0000';

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();
    }

    private void initField(){
        setBounds(cellSize*dimension, cellSize*dimension, 400, 300);
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("New Game!");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize*dimension, 150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension,dimension));
        gameFieldPanel.setSize(cellSize*dimension, cellSize*dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        for (int i = 0; i < gameButtons.length; i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;

            add(controlPanel, BorderLayout.NORTH);
            add(gameFieldPanel, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    Game getGame(){
        return game;
    }

    void emptyField(){
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i].setText("");

            int row = i / GameBoard.dimension;
            int cell = i % GameBoard.dimension;

            gameField[row][cell] = nullSymbol;
        }
    }

    boolean isTurnable(int row, int cell){
        boolean result = false;

        if(gameField[cell][row] == nullSymbol)
            result = true;

        return result;
    }

    void updateGameField(int row, int cell){
        gameField [cell][row] = game.getCurrentPlayer().getPlayerSign();


    }

    boolean checkWin(){
        boolean result = false;

        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();

        if(checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)){
            result = true;
        }
        return result;
    }

    private boolean checkWinDiagonals(char playerSymbol){
        boolean leftRigth = true;
        boolean rightLeft = true;
        boolean result = false;

        for (int i = 0; i < dimension; i++) {
            leftRigth &= (gameField[i][i] == playerSymbol);
            rightLeft &= (gameField[dimension - i - 1][i] == playerSymbol);
        }
        if(leftRigth || rightLeft){
            result = true;
        }

        return result;
    }

    private boolean checkWinLines(char playerSymbol){
        boolean cols;
        boolean rows;
        boolean result = false;

        for (int col = 0; col < dimension ; col++) {
            cols = true;
            rows = true;

            for (int row = 0; row < dimension; row++) {
                cols &= (gameField[col][row] == playerSymbol);
                rows &= (gameField[row][col] == playerSymbol);
            }
            if (cols || rows) {
                result = true;
                break;
            }
            if (result) break;
        }
        return result;
    }

    boolean isFull(){
        boolean result = true;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(gameField[i][j] == nullSymbol)
                    result = false;
            }
        }
        return result;
    }

    GameButton getButton(int cellIndex) {
        return gameButtons[cellIndex];
    }

    public char[][] getGameField() {
        return gameField;
    }
}

