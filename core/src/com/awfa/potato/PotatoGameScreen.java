package com.awfa.potato;

import com.awfa.potato.messages.MessageSystem;
import com.awfa.potato.minigames.MinigameManager;
import com.awfa.potato.systems.RenderSystem;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Screen;

public class PotatoGameScreen implements Screen {
	private final PotatoGame game;
	private MessageSystem messageSystem;
	private MinigameManager minigameManager;
	
	public PotatoGameScreen(final PotatoGame game) {
		this.game = game;
		messageSystem = new MessageSystem();
		minigameManager = new MinigameManager(messageSystem);
		
		Entity player = EntityFactory.createPlayer();
		
		game.engine.addEntity(player);
		
		RenderSystem renderSystem = new RenderSystem();
		game.engine.addSystem(renderSystem);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		minigameManager.update(delta);
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
