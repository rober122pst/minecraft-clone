package com.robergames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.robergames.main.Game;
import com.robergames.world.*;

public class Enemy extends Entity{
	
	public boolean right = true, left = false;
	public int dir = 1;
	
	public double vspd = 0;
	private double gravity = 0.3;
	
	public int vida = 10;
	
	public BufferedImage sprite1, sprite2;
	
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite1, BufferedImage sprite2) {
		super(x, y, width, height, speed, null);
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
	}
	
	public void tick() {
		vspd+=gravity;
		
		if(!World.isFree((int)x,(int)(y+vspd))) {
			int signVsp = 0;
			if(vspd >= 0)
			{
				signVsp = 1;
			}else  {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp))) {
				y = y+signVsp;
			}
			vspd = 0;
		}

		y = y + vspd;
		
		if(dir == 1) {
			if(World.isFree((int)(x+speed), (int) y)) {
				x+=speed;
			}else {
				int xnext = (int)((x+speed) / 16) + 1;
				int ynext = (int)(y / 16);
				Tile tile = World.tiles[xnext+ynext*World.WIDTH];
				if(tile instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile(xnext*16,ynext*16,Tile.TILE_AR);
				}
				dir = -1;
				left = true;
				right = false;
			}
		}else if(dir == -1) {
			if(World.isFree((int)(x-speed), (int) y)) {
				x-=speed;
			}else {
				int xnext = (int)((x-speed) / 16);
				int ynext = (int)(y / 16);
				Tile tile = World.tiles[xnext+ynext*World.WIDTH];
				if(tile instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile(xnext*16,ynext*16,Tile.TILE_AR);
				}
				dir = 1;
				right = true;
				left = false;
			}
		}
		
		if(vida <= 0) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		if(dir == 1)
			sprite = sprite1;
		else if(dir == -1)
			sprite = sprite2;
		
		super.render(g);
	}


}
