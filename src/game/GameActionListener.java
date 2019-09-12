package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {
    private GameButton button;

    public GameActionListener(GameButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();
        int row = button.getButtonIndex() / GameBoard.dimension;
        int cell = button.getButtonIndex() % GameBoard.dimension;

        if(board.isTurnable(row, cell)){
            button.getBoard().getGame().getCurrentPlayer().updateByPlayerData(button);

            if(board.isFull()){
                board.getGame().showMessage("Draw.");
                board.emptyField();
            }
            else if (!board.getGame().getCurrentPlayer().isRealPlayer()){
                button.getBoard().getGame().getCurrentPlayer().updateByAiData(button);
            }
        }
        else{
            board.getGame().showMessage("Invalid move");
        }
    }
}
