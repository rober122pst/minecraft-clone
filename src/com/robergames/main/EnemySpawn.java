package com.robergames.main;

import com.robergames.entities.Enemy;
import com.robergames.entities.Entity;
import com.robergames.world.World;

public class EnemySpawn {

	public int interval = 60*5;
	public int curTime = 0;
	
	public void tick() {
		if(World.CICLO == World.noite) {
			curTime++;
			if(curTime == interval) {
				curTime = 0;
				int xinitial = Entity.rand.nextInt((Game.WIDTH*Game.SCALE)*16 - 16 - 16)+16;
				Enemy enemy = new Enemy(xinitial,16,16,16,1,Entity.ENEMY_RIGHT,Entity.ENEMY_LEFT);
				Game.entities.add(enemy);
			}
		}
	
	}
	
}
