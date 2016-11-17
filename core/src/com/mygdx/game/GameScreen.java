package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends ScreenAdapter{
	private Texture playerImg;
	private Texture ballImg;
	private Array<Player> players1;
	private Array<Player> players2;
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
		Array<Player> players1 = world.getPlayers1();
		Array<Player> players2 = world.getPlayers2();
		updatePlayers1Direction();
		updatePlayers2Direction();
	}
	
	private void updateBallDirection() {
		Ball ball = world.getBall();
		Array<Player> players1 = world.getPlayers1();
		Array<Player> players2 = world.getPlayers2();
		updateBallWithPlayers1();
		updateBallWithPlayers2();
	}
	
	private void updatePlayers1Direction() {
		players1 = world.getPlayers1();
		for(Player player : players1) {
			if(player.isSelected) {
				if(Gdx.input.isKeyPressed(Keys.LEFT)) {
					player.setNextDirection(Player.DIRECTION_LEFT);
				}
				if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
					player.setNextDirection(Player.DIRECTION_RIGHT);
				}
				if(Gdx.input.isKeyPressed(Keys.UP)) {
					player.setNextDirection(Player.DIRECTION_UP);
				}
				if(Gdx.input.isKeyPressed(Keys.DOWN)) {
					player.setNextDirection(Player.DIRECTION_DOWN);
				}
				if(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) {
					player.setNextDirection(Player.DIRECTION_UPLEFT);
				}
				if(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.LEFT)) {
					player.setNextDirection(Player.DIRECTION_DOWNLEFT);
				}
				if(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
					player.setNextDirection(Player.DIRECTION_UPRIGHT);
				}
				if(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
					player.setNextDirection(Player.DIRECTION_DOWNRIGHT);
				}
				if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN)) {
					player.setNextDirection(Player.DIRECTION_STILL);
				}
			}
		}
	}
	private void updatePlayers2Direction() {
		players2 = world.getPlayers2();
		for(Player player : players2) {
			if(player.isSelected) {
				if(Gdx.input.isKeyPressed(Keys.A)) {
					player.setNextDirection(Player.DIRECTION_LEFT);
				}
				if(Gdx.input.isKeyPressed(Keys.D)) {
					player.setNextDirection(Player.DIRECTION_RIGHT);
				}
				if(Gdx.input.isKeyPressed(Keys.W)) {
					player.setNextDirection(Player.DIRECTION_UP);
				}
				if(Gdx.input.isKeyPressed(Keys.S)) {
					player.setNextDirection(Player.DIRECTION_DOWN);
				}
				if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.A)) {
					player.setNextDirection(Player.DIRECTION_UPLEFT);
				}
				if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.A)) {
					player.setNextDirection(Player.DIRECTION_DOWNLEFT);
				}
				if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.D)) {
					player.setNextDirection(Player.DIRECTION_UPRIGHT);
				}
				if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.D)) {
					player.setNextDirection(Player.DIRECTION_DOWNRIGHT);
				}
				if(!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S)) {
					player.setNextDirection(Player.DIRECTION_STILL);
				}
			}
		}
	}
	
	private void updateBallWithPlayers1() {
		players1 = world.getPlayers1();
		ball = world.getBall();
		for(Player player : players1) {
			if(ball.isReadyToHold(player) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				ball.setMotion(true, false);
				player.isHoldBall = true;
			} else if(ball.isReadyToThrow(player) && Gdx.input.isKeyPressed(Keys.SPACE)) {
				player.isHoldBall = false;
				ball.setMotion(false, true);
				ball.setSpeed(30);
				if(player.currentDirection != ball.DIRECTION_STILL) {
					ball.setNextDirection(player.currentDirection);
				} else {
					ball.setMotion(false, false);
				}
			} else if(ball.isThrowed && ball.isOutCourt()) {
				ball.setNextDirection(ball.DIRECTION_STILL);
				ball.setMotion(false, false);
			}
		}	
	}
	
	private void updateBallWithPlayers2() {
		players2 = world.getPlayers2();
		for(Player player : players2) {
			if(ball.isReadyToHold(player) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				ball.setMotion(true, false);
				player.isHoldBall = true;
			} else if (ball.isReadyToThrow(player) && Gdx.input.isKeyPressed(Keys.SPACE)) {
				player.isHoldBall = false;
				ball.setMotion(false, true);
				ball.setSpeed(30);
				if(player.currentDirection != ball.DIRECTION_STILL) {
					ball.setNextDirection(player.currentDirection);
				} else {
					ball.setMotion(false, false);
				}
			} else if(ball.isThrowed && ball.isOutCourt()) {
				ball.setNextDirection(ball.DIRECTION_STILL);
				ball.setMotion(false, false);
			}
		}	
	}
}
