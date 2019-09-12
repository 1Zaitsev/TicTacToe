package game;

import javax.swing.*;

public class GameButton extends JButton {
    private int buttonIndex;
    private GameBoard board;

    public GameButton(int buttonIndex, GameBoard board) {
        this.buttonIndex = buttonIndex;
        this.board = board;

        int row = buttonIndex / GameBoard.dimension;
        int cell = buttonIndex % GameBoard.dimension;

        setSize(GameBoard.cellSize - 5, GameBoard.cellSize - 5);
        addActionListener(new GameActionListener(this));
    }

    public int getButtonIndex() {
        return buttonIndex;
    }

    public GameBoard getBoard() {
        return board;
    }
}

