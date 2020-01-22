package org.emulators;

import java.util.LinkedList;
import java.util.List;

public class XOFacade {

    private List<XOGame> xoGames = new LinkedList<XOGame>();

    public XOGame startNewGame() {
        XOGame xoGame = new XOGame();
        xoGames.add(xoGame);
        return xoGame;
    }

    public void showStats() {
        for (XOGame xoGame : xoGames) {
            System.out.println("[INFO] xoGame uuid  : ["+xoGame.getGameUUID()+"]");
            System.out.println("[INFO] xoGame state  : ["+xoGame.getXoLifeCycle()+"]");
        }

    }

    public String getXOGameField() {
        XOGame currentGame = getCurrentGame();
        int[][] field = currentGame.getField();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(field[i][j] + " ");
            }
        }
        System.out.println();
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(field[i][j] + " ");
            }
        }
        System.out.println();
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(field[i][j] + " ");
            }
        }
        System.out.println();
        return "";
    }

    public XOGame getCurrentGame() {
        int size = xoGames.size();
        return size > 0 ? xoGames.get(size - 1) : null;
    }

}
