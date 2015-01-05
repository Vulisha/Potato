package com.awfa.potato;

import com.awfa.potato.components.*;
import com.awfa.potato.systems.RenderSystem;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Screen;

public class PotatoGameScreen implements Screen {
	private final PotatoGame game;
	
	public PotatoGameScreen(final PotatoGame game) {
		this.game = game;
		
		Entity player = new Entity();
		TextureComponent playerTexture = new TextureComponent();
		playerTexture.region = PotatoAssets.playerTexture;
		
		PositionComponent playerPosition = new PositionComponent();
		player.add(playerTexture);
		player.add(playerPosition);
		
		game.engine.addEntity(player);
		
		RenderSystem renderSystem = new RenderSystem(game);
		game.engine.addSystem(renderSystem);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		game.engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
