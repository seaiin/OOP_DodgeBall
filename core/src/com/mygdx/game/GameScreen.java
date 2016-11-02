package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter{
	private Texture PlayerImg;
	private Player player;
	private DodgeBall dodgeBall;
	private World world;
	private WorldRenderer worldRenderer;
	
	public GameScreen(DodgeBall dodgeBall) {
		this.dodgeBall = dodgeBall;
		PlayerImg = new Texture("player.png");
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

}
