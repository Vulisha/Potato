package com.awfa.potato;

import com.awfa.potato.components.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PotatoGameScreen implements Screen {
	private final PotatoGame game;
	private OrthographicCamera camera;
	
	public PotatoGameScreen(final PotatoGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		
		float width = 1280f;
		float height = 720f;
		camera.setToOrtho(false, width, height);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		
		Entity player = new Entity();
		TextureComponent playerTexture = new TextureComponent();
		playerTexture.region = PotatoAssets.playerTexture;
		
		PositionComponent playerPosition = new PositionComponent();
		player.add(playerTexture);
		player.add(playerPosition);
		
		game.engine.addEntity(player);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.end();
		
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
