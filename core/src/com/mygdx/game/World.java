package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	private DodgeBall dodgeBall;
	private Map map;
	private Ball ball;
	private Array<Player> players1;
	private Array<Player> players2;
	private static final int[][] PLAYERS1_POSITION = new int [][] {
		{250,260},
		{220,360},
		{160,160}
	};
	private static final int[][] PLAYERS2_POSITION = new int [][] {
		{900,260},
		{940,360},
		{1000,160}
	}; 
	
	World(DodgeBall dodgeBall) {
		map = new Map();
		players1 = new Array<Player>();
		players2 = new Array<Player>();
		addPlayers();
		ball = new Ball(80, 40, this);
		this.dodgeBall = dodgeBall;
	}

	Array<Player> getPlayers1() {
		return players1;
	}
	
	Array<Player> getPlayers2() {
		return players2;
	}
	
	Map getMap() {
		return map;
	}
	
	Ball getBall() {
		return ball;
	}
	
	public void update(float delta) {
		for(Player player : players1) {
			player.update();
		}
		for(Player player : players2) {
			player.update();
		}
		removePlayer();
		ball.update();
	}

	
	private void addPlayers() {
		for(int i = 0 ; i < 3 ; i++) {
			Player player =  new Player(0, 0, this);
			player.position.x = PLAYERS1_POSITION[i][0];
			player.position.y = PLAYERS1_POSITION[i][1];
			players1.add(player);
		}
		for(int i = 0 ; i < 3 ; i++) {
			Player player =  new Player(0, 0, this);
			player.position.x = PLAYERS2_POSITION[i][0];
			player.position.y = PLAYERS2_POSITION[i][1];
			players2.add(player);
		}
	}
	
	private void removePlayer() {
		for(int i = 0 ; i < 3 ; i++) {
			if(players1.get(i).isDead()){
				players1.get(i).position.y += 10;
			}
			if(players2.get(i).isDead()){
				players2.get(i).position.y += 10;
			}
		}
	}
}
