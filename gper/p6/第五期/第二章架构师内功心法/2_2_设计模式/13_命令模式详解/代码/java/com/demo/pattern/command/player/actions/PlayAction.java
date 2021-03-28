package com.demo.pattern.command.player.actions;

import com.demo.pattern.command.player.GPlayer;
import com.demo.pattern.command.player.IAction;

public class PlayAction implements IAction {

    private GPlayer gPlayer;

    public PlayAction(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void execute() {
        gPlayer.play();
    }
}
