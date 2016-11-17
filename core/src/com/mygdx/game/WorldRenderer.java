package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WorldRenderer {
	private DodgeBall dodgeBall;
	private Array<Player> players1;
	private Array<Player> players2;
	private World world;
	private Texture playerImg;
	private Texture ballImg;
	private Texture mapImg;
	private SpriteBatch batch;
	
	public WorldRenderer(DodgeBall dodgeball, World world) {
		this.dodgeBall = dodgeball;
		batch = dodgeBall.batch;
		this.world = world;
		playerImg = new Texture("player.png");
		ballImg = new Texture("ball.png");
		mapImg = new Texture("map.jpg");
	}
	
	public void render(float delta) {
		Array<Player> players1 = world.getPlayers1();
		Array<Player> players2 = world.getPlayers2();
		Ball ball = world.getBall();
		Vector2 posBall = world.getBall().getPosition();
		batch.begin();
		batch.draw(mapImg, 0, 0);
		drawPlayers();
		batch.draw(ballImg, posBall.x , posBall.y);
		batch.end();
	}
	
	private void drawPlayers() {
		players1 = world.getPlayers1();
		players2 = world.getPlayers2();
		for(Player player : players1) {
			Vector2 posPlayer = player.getPosition();
			batch.draw(playerImg, posPlayer.x, posPlayer.y);
		}
		for(Player player : players2) {
			Vector2 posPlayer = player.getPosition();
			batch.draw(playerImg, posPlayer.x, posPlayer.y);
		}
	}
	
}
