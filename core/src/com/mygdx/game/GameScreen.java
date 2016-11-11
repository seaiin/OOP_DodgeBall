package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter{
	private Texture playerImg;
	private Texture ballImg;
	private Player player;
	private DodgeBall dodgeBall;
	private World world;
	private WorldRenderer worldRenderer;
	private Ball ball;
	
	public GameScreen(DodgeBall dodgeBall) {
		this.dodgeBall = dodgeBall;
		playerImg = new Texture("player.png");
		ballImg = new Texture("ball.png");
		world = new World(dodgeBall);
		worldRenderer = new WorldRenderer(dodgeBall, world);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		worldRenderer.render(delta);
	}
	
	public void update(float delta) {
		updatePlayerDirection();
		updateBallDirection();
		world.update(delta);
	}
	
	private void updatePlayerDirection() {
		Player player = world.getPlayer();
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.setNextDirection(Player.DIRECTION_LEFT);
		} 
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.setNextDirection(Player.DIRECTION_RIGHT);
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			player.setNextDirection(Player.DIRECTION_UP);
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.setNextDirection(Player.DIRECTION_DOWN);
		}
		else {
			player.setNextDirection(Player.DIRECTION_STILL);
		}
	}
	
	private void updateBallDirection() {
		Ball ball = world.getBall();
		Player player = world.getPlayer();
		if(ball.isReadyToHold(player) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			ball.setMotion(true, false);
			player.isHoldBall = true;
		} else if (ball.isReadyToThrow(player) && Gdx.input.isKeyPressed(Keys.SPACE)) {
			player.isHoldBall = false;
			ball.setMotion(false, true);
			ball.setSpeed(30);
			ball.setNextDirection(player.currentDirection);
		} else if(ball.isThrowed && (ball.getPosition().x >= 1200 | ball.getPosition().y >= 800)) {
			ball.setNextDirection(ball.DIRECTION_STILL);
			ball.setMotion(false, false);
		}
			
	}

}
