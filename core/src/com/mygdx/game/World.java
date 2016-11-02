package com.mygdx.game;

public class World {
	private Player player;
	private DodgeBall dodgeBall;
	private Map map;
	
	World(DodgeBall dodgeBall) {
		map = new Map();
		player = new Player(60, 60, this);
		this.dodgeBall = dodgeBall;
	}

	Player getPlayer() {
		return player;
	}
	
	Map getMap() {
		return map;
	}
	
	public void update(float delta) {
		player.update();
	}

}
