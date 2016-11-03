package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Ball {
	private Vector2 position;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	public static int SPEED;
	private int currentDirection;
	private int nextDirection;
	private World world;
	private Player player;
	
	private static final int[][] DIR_OFFSETS = new int [][] {
		{0,0},
		{0,-1},
		{1,0},
		{0,1},
		{-1,0}
	};
	public Ball(int x, int y, World world) {
		position = new Vector2(x, y);
		currentDirection = DIRECTION_STILL;
		nextDirection = DIRECTION_STILL;
		this.world = world;
		player = world.getPlayer();
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void move(int dir) {
		
	}
	
	public void  update() {
		Map map = world.getMap();
		if(canMoveInDirection(nextDirection)) {
			if(isOnGround() && Gdx.input.isKeyPressed(Keys.TAB)) {
				position.x = player.getPosition().x + 20;
				position.y = player.getPosition().y - 20;
			}
			if(isHolded(player)) {
				System.out.println("Hold");
			}
		} else {
			currentDirection = DIRECTION_STILL;
		}
	}
	
	public void setNextDirection(int dir) {
		nextDirection = dir;
	}
	
	public boolean isOnGround() {
		return currentDirection == DIRECTION_STILL;
	}
	
	public boolean isHolded(Player player) {
		return position.x == player.getPosition().x + 20 && position.y == player.getPosition().y - 20;
	}
	
	private boolean canMoveInDirection(int dir) {
		Map map = world.getMap();
		return true;
	}
}
