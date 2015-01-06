package com.awfa.potato;

import com.awfa.potato.components.PositionComponent;
import com.awfa.potato.components.TextureComponent;
import com.badlogic.ashley.core.Entity;

public class EntityFactory {
	
	public static Entity createPlayer() {
		Entity player = new Entity();
		
		TextureComponent texComp = new TextureComponent();
		texComp.region = PotatoAssets.playerTexture;
		
		PositionComponent posComp = new PositionComponent();
		
		player.add(texComp);
		player.add(posComp);
		
		return player;
	}

}
