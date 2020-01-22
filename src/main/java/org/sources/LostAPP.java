package org.sources;

import org.emulators.XOFacade;
import org.emulators.XOGame;

public class LostAPP {
    public static void main(String[] args) {
        XOFacade xoFacade = new XOFacade();
        XOGame xoGame = xoFacade.startNewGame();
        xoFacade.getXOGameField();
        int[] validAction = xoGame.getValidAction();
        for (int i : validAction) {
            System.out.print(i+";");
        }
        System.out.println();
        xoGame.action(0);
        xoGame.action(3);
        xoGame.action(1);
        xoGame.action(4);
        xoGame.action(2);
        xoGame.action(5);
        xoFacade.getXOGameField();
        xoFacade.showStats();

    }
}
