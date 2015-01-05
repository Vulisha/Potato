package com.awfa.potato.systems;

import com.awfa.potato.PotatoGame;
import com.awfa.potato.components.PositionComponent;
import com.awfa.potato.components.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;
	private ComponentMapper<PositionComponent> posMap = ComponentMapper
			.getFor(PositionComponent.class);
	private ComponentMapper<TextureComponent> texMap = ComponentMapper
			.getFor(TextureComponent.class);
	private PotatoGame game;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public RenderSystem(PotatoGame game) {
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		camera.setToOrtho(false, width, height);
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void addedToEngine(Engine engine) {
		Family family = Family.all(PositionComponent.class,
				TextureComponent.class).get();
		entities = engine.getEntitiesFor(family);
	}

	@Override
	public void update(float deltaTime) {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.begin();
		for (Entity entity : entities) {
			TextureComponent texture = texMap.get(entity);
			PositionComponent position = posMap.get(entity);

			batch.draw(texture.region, position.x, position.y);
		}
		batch.end();
	}
}
