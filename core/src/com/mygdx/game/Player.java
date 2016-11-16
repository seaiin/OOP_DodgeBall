package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Player {
	public Vector2 position;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_UPLEFT = 5;
	public static final int DIRECTION_DOWNLEFT = 6;
	public static final int DIRECTION_UPRIGHT = 7;
	public static final int DIRECTION_DOWNRIGHT = 8;
	public static final int DIRECTION_STILL = 0;
	public static final int SPEED = 5;
	public static boolean isHoldBall = false;
	public int currentDirection;
	private int nextDirection;	
	private World world;
	private Ball ball;
	private static final int [][] DIR_OFFSETS = new int[][] {
		{0, 0},
		{0, -1},
		{1, 0},
		{0, 1},
		{-1, 0},
		{-1,-1},
		{-1, 1},
		{1, -1},
		{1, 1}
	};
	
	public Player(int x, int y, World world) {
		position = new Vector2(x, y);
		currentDirection = DIRECTION_STILL;
		nextDirection = DIRECTION_STILL;
		this.world = world;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void move(int dir) {
		position.x += SPEED * DIR_OFFSETS[dir][0];
		position.y += SPEED * DIR_OFFSETS[dir][1];
	}
	
	public void setNextDirection(int dir) {
		nextDirection = dir;
	}
	
	public void update() {
		Map map = world.getMap();
		if(canMoveInDirection(nextDirection)) {
			currentDirection = nextDirection;
		} else {
			currentDirection = DIRECTION_STILL;
		}
		move(currentDirection);
	}
	
	private boolean canMoveInDirection(int dir) {
		Map map = world.getMap();
		return true;
	}	
	
	private void pickBall() {
		Ball ball = world.getBall();	
	}
}
