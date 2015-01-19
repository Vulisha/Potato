package com.awfa.potato.systems;

import com.awfa.potato.components.PositionComponent;
import com.awfa.potato.components.TextComponent;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntitySystem {

	private ImmutableArray<Entity> textureEntities;
	private ImmutableArray<Entity> textEntities;
	private ComponentMapper<PositionComponent> positionMap = ComponentMapper
			.getFor(PositionComponent.class);
	private ComponentMapper<TextureComponent> textureMap = ComponentMapper
			.getFor(TextureComponent.class);
	private ComponentMapper<TextComponent> textMap = ComponentMapper
			.getFor(TextComponent.class);
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private BitmapFont arial15;
	

	public RenderSystem() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		arial15 = new BitmapFont();

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		camera.setToOrtho(false, width, height);
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void addedToEngine(Engine engine) {
		Family textureFamily = Family.all(PositionComponent.class,
				TextureComponent.class).get();
		Family textFamily = Family.all(PositionComponent.class,
				TextComponent.class).get();
		textureEntities = engine.getEntitiesFor(textureFamily);
		textEntities = engine.getEntitiesFor(textFamily);
	}

	@Override
	public void update(float deltaTime) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.begin();
		for (Entity entity : textureEntities) {
			TextureComponent texture = textureMap.get(entity);
			PositionComponent position = positionMap.get(entity);

			batch.draw(texture.region, position.x, position.y);
		}
		
		for (Entity entity : textEntities) {
			TextComponent text = textMap.get(entity);
			PositionComponent position = positionMap.get(entity);
			
			arial15.draw(batch, text.textBody, position.x, position.y);
		}
		batch.end();
	}
}
