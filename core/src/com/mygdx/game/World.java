package com.mygdx.game;

public class World {
	private Player player;
	private DodgeBall dodgeBall;
	private Map map;
	private Ball ball;
	
	World(DodgeBall dodgeBall) {
		map = new Map();
		player = new Player(60, 60, this);
		ball = new Ball(80, 40, this);
		this.dodgeBall = dodgeBall;
	}

	Player getPlayer() {
		return player;
	}
	
	Map getMap() {
		return map;
	}
	
	Ball getBall() {
		return ball;
	}
	
	public void update(float delta) {
		player.update();
		ball.update();
	}

}
