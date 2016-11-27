package view.controlScreen.mainScreen.controlHandler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.controlScreen.winnerScreen.WinnerScreenController;

/**
 * Created by alexp on 12-11-16.
 */
public class ControlDeclareWinnerHandler implements EventHandler<MouseEvent> {

    private Controller controller;
    private boolean firstJudoka;
    private WinnerScreenController winnerFormScreen;

    public ControlDeclareWinnerHandler(Controller controller, boolean firstJudoka, WinnerScreenController winnerFormScreen) {
        this.controller = controller;
        this.firstJudoka = firstJudoka;
        this.winnerFormScreen = winnerFormScreen;
    }

    @Override
    public void handle(MouseEvent event) {
        if(this.firstJudoka)
            this.controller.setFirstJudokaWinner();
        else
            this.controller.setSecondJudokaWinner();

        this.winnerFormScreen.setWinningOptions();
        this.winnerFormScreen.getContainer().toFront();
    }
}
