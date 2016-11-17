package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ball {
	
	private Vector2 position;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_UPLEFT = 5;
	public static final int DIRECTION_DOWNLEFT = 6;
	public static final int DIRECTION_UPRIGHT = 7;
	public static final int DIRECTION_DOWNRIGHT = 8;
	public static final int DIRECTION_STILL = 0;
	public static boolean isHolded = false;
	public static boolean isThrowed = false;
	public static int ballSpeed;
	private int currentDirection;
	private int nextDirection;
	private World world;
	private Player player;
	private Array<Player> players1;
	private Array<Player> players2;
	private static final int[][] DIR_OFFSETS = new int [][] {
		{0,0},
		{0,1},
		{1,0},
		{0,-1},
		{-1,0},
		{-1,1},
		{-1,-1},
		{1,1},
		{1,-1}
	};
	
	public Ball(int x, int y, World world) {
		position = new Vector2(x, y);
		currentDirection = DIRECTION_STILL;
		nextDirection = DIRECTION_STILL;
		this.world = world;
		this.players1 = world.getPlayers1();
		this.players2 = world.getPlayers2();
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setMotion() {
		setHoldMotion();
		if(isThrowed == true) {
			
		}
	}
	
	public void move(int dir) {
		position.x += ballSpeed * DIR_OFFSETS[dir][0];
		position.y += ballSpeed * DIR_OFFSETS[dir][1];
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
		return 	getPosition().x >= player.getPosition().x - 10
				&& getPosition().x <= player.getPosition().x + 10
				&& isHolded != true && isThrowed != true && player.isHoldBall == false;
	}
	
	public boolean isReadyToThrow(Player player) {
		return isHolded == true && player.isHoldBall == true;
	}
	
	public boolean isHit(Player player) {
		return true;
	}
	
	public void setPositionX(float x) {
		position.x = x;
	}
	
	public void setPositionY(float y) {
		position.y = y;
	}
	
	public void setSpeed(int speed) {
		ballSpeed = speed;
	}
	
	public void setMotion(boolean holded, boolean throwed) {
		isHolded = holded;
		isThrowed = throwed;
	}
	
	public boolean isOutCourt() {
		return (getPosition().y >= 500 | getPosition().y <= 100) | (getPosition().x <= 100 | getPosition().x >= 1100);
	}
	
	private void setHoldMotion() {
		if(isHolded == true) {
			for(Player player : players1) {
				if(player.isHoldBall == true) {
					setPositionX(player.getPosition().x + 20);
					setPositionY(player.getPosition().y - 20);
				}
			}
			for(Player player : players2) {
				if(player.isHoldBall == true) {
					setPositionX(player.getPosition().x + 20);
					setPositionY(player.getPosition().y - 20);
				}
			}
		}
	}
}