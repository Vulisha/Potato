package com.awfa.potato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PotatoAssets {
	public static TextureRegion playerTexture;
	
	public static void load() {
		Texture texture = new Texture(Gdx.files.internal("player.png"));
		playerTexture = new TextureRegion(texture);
	}
}
