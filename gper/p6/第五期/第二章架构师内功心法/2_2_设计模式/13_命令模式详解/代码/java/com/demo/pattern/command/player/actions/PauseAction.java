package com.demo.pattern.command.player.actions;

import com.demo.pattern.command.player.GPlayer;
import com.demo.pattern.command.player.IAction;

public class PauseAction implements IAction {

    private GPlayer gPlayer;

    public PauseAction(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void execute() {
        gPlayer.pause();
    }
}
