package com.demo.pattern.command.player;

import com.demo.pattern.command.player.actions.PauseAction;
import com.demo.pattern.command.player.actions.PlayAction;
import com.demo.pattern.command.player.actions.SpeedAction;
import com.demo.pattern.command.player.actions.StopAction;

public class Test {

    public static void main(String[] args) {
        GPlayer gPlayer = new GPlayer();
        Controller controller = new Controller();
        controller.execute(new PauseAction(gPlayer));


        controller.addAction(new PauseAction(gPlayer));
        controller.addAction(new StopAction(gPlayer));
        controller.addAction(new SpeedAction(gPlayer));
        controller.addAction(new PlayAction(gPlayer));

        controller.executes();
    }
}
