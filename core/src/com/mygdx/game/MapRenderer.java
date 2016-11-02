package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapRenderer {
	private Map map;
	private SpriteBatch batch;
	private Texture mapImg;
	
	public MapRenderer(SpriteBatch batch, Map map) {
		this.map = map;
		this.batch = batch;
		//mapImg = new Texture("map.png");
	}
	
	public void render() {
		batch.begin();
		batch.end();
	}
}
