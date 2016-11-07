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
	public static boolean isHolded = false;
	public static boolean isThrowed = false;
	public static int speed;
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
	
	public void setMotion() {
		if(isHolded == true) {
			setPositionX(player.getPosition().x + 20);
			setPositionY(player.getPosition().y - 20);
			
		} else if(isThrowed == true) {
			
		}
	}
	
	public void move(int dir) {
		speed = 5;
		position.x += speed * DIR_OFFSETS[dir][0];
		position.y += speed * DIR_OFFSETS[dir][1];
	}
	
	public void throwed(int dir) {
		speed = 10;
	}
	
	public void update() {
		Map map = world.getMap();
		setMotion();
		if(canMoveInDirection(nextDirection)) {
			currentDirection = nextDirection;
		} else {
			currentDirection = DIRECTION_STILL;
		}
		move(currentDirection);
	}
	
	public void setNextDirection(int dir) {
		nextDirection = dir;
	}
	
	private boolean canMoveInDirection(int dir) {
		Map map = world.getMap();
		return true;
	}
	
	public boolean isReadyToHold(Player player) {
		return getPosition().x >= player.getPosition().x - 10 
				&& getPosition().x <= player.getPosition().x + 10
				&& isHolded != true && isThrowed != true && player.isHoldBall == false;
	}
	
	public boolean isReadyToThrow(Player player) {
		return isHolded == true && player.isHoldBall == true;
	}
	
	public void setPositionX(float x) {
		position.x = x;
	}
	
	public void setPositionY(float y) {
		position.y = y;
	}
}