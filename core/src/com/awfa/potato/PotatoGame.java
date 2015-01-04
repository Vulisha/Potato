package com.awfa.potato;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PotatoGame extends Game {
	public SpriteBatch batch;
	public Engine engine = new Engine();
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		PotatoAssets.load();
		
		Screen gameScreen = new PotatoGameScreen(this);
		this.setScreen(gameScreen);
	}

	@Override
	public void render() {
		super.render();
	}
}
