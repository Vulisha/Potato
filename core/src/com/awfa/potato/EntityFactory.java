package com.awfa.potato;

import com.awfa.potato.components.PlayerComponent;
import com.awfa.potato.components.PositionComponent;
import com.awfa.potato.components.TextureComponent;
import com.badlogic.ashley.core.Entity;

public class EntityFactory {
	
	public static Entity createPlayer() {
		Entity player = new Entity();
		
		TextureComponent textureComponent = new TextureComponent();
		textureComponent.region = PotatoAssets.playerTexture;
		
		PositionComponent positionComponent = new PositionComponent();
		PlayerComponent playerComponent = new PlayerComponent();
		
		player.add(textureComponent);
		player.add(positionComponent);
		player.add(playerComponent);
		
		return player;
	}

}
