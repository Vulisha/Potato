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

public class RenderSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;
	private ComponentMapper<PositionComponent> posMap = ComponentMapper
			.getFor(PositionComponent.class);
	private ComponentMapper<TextureComponent> texMap = ComponentMapper
			.getFor(TextureComponent.class);
	private PotatoGame game;

	public RenderSystem(PotatoGame game) {
		this.game = game;
	}

	@Override
	public void addedToEngine(Engine engine) {
		Family family = Family.all(PositionComponent.class,
				TextureComponent.class).get();
		entities = engine.getEntitiesFor(family);
	}

	@Override
	public void update(float deltaTime) {
		for (Entity entity : entities) {
			TextureComponent texture = texMap.get(entity);
			PositionComponent position = posMap.get(entity);

			game.batch.draw(texture.region, position.x, position.y);
		}
	}
}
