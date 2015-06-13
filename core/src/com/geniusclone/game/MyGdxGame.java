package com.geniusclone.game;

import com.badlogic.gdx.Game;
import com.geniusclone.screens.SplashScreen;

public class MyGdxGame extends Game {
	@Override
	public void create () {
        setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
