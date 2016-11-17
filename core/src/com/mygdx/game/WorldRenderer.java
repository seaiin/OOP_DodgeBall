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
	private Texture arrow1Img;
	private Texture arrow2Img;
	private SpriteBatch batch;
	
	public WorldRenderer(DodgeBall dodgeball, World world) {
		this.dodgeBall = dodgeball;
		batch = dodgeBall.batch;
		this.world = world;
		playerImg = new Texture("player.png");
		ballImg = new Texture("ball.png");
		mapImg = new Texture("map.jpg");
		arrow1Img = new Texture("arrow1.png");
		arrow2Img = new Texture("arrow2.png");
	}
	
	public void render(float delta) {
		Array<Player> players1 = world.getPlayers1();
		Array<Player> players2 = world.getPlayers2();
		Ball ball = world.getBall();
		Vector2 posBall = world.getBall().getPosition();
		batch.begin();
		batch.draw(mapImg, 0, 0);
		drawPlayers();
		drawArrow();
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
	
	private void drawArrow() {
		players1 = world.getPlayers1();
		players2 = world.getPlayers2();
		for(Player player : players1) {
			if(player.isSelected == true) {
				Vector2 posPlayer = player.getPosition();
				batch.draw(arrow1Img, posPlayer.x + 10, posPlayer.y + 60, 30, 30);
			}
		}
		for(Player player : players2) {
			if(player.isSelected == true) {
				Vector2 posPlayer = player.getPosition();
				batch.draw(arrow2Img, posPlayer.x + 10, posPlayer.y + 60, 30, 30);
			}
		}
	}
	
}
