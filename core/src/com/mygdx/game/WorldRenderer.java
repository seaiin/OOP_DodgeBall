package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private DodgeBall dodgeBall;
	private Player player;
	private World world;
	private Texture playerImg;
	private Texture ballImg;
	private SpriteBatch batch;
	private MapRenderer mapRenderer;
	
	public static final int BLOCK_SIZE = 40;
	
	public WorldRenderer(DodgeBall dodgeball, World world) {
		this.dodgeBall = dodgeball;
		batch = dodgeBall.batch;
		this.world = world;
		mapRenderer = new MapRenderer(dodgeBall.batch, world.getMap());
		playerImg = new Texture("player.png");
		ballImg = new Texture("ball.png");
	}
	
	public void render(float delta) {
		mapRenderer.render();
		Player player = world.getPlayer();
		Ball ball = world.getBall();
		Vector2 posPlayer = world.getPlayer().getPosition();
		Vector2 posBall = world.getBall().getPosition();
		batch.begin();
		batch.draw(playerImg, posPlayer.x - BLOCK_SIZE/2, DodgeBall.HEIGHT - posPlayer.y - BLOCK_SIZE/2);
		batch.draw(ballImg, posBall.x - BLOCK_SIZE/2, DodgeBall.HEIGHT - posBall.y - BLOCK_SIZE/2);
		batch.end();
	}
	
}
