package com.robergames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.robergames.main.Game;

public class Tile {
	
	public static BufferedImage TILE_GRAMA = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_TERRA = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage[] TILE_NEVE = {Game.spritesheet.getSprite(32,0,16,16),Game.spritesheet.getSprite(32,16,16,16)};
	public static BufferedImage TILE_AREIA = Game.spritesheet.getSprite(48,0,16,16);
	public static BufferedImage TILE_PEDRA = Game.spritesheet.getSprite(64,0,16,16);
	public static BufferedImage TILE_AR = Game.spritesheet.getSprite(64+16,0,16,16);
	public static BufferedImage TILE_NOITE = Game.spritesheet.getSprite(64+16,16,16,16);
	public static BufferedImage TILE_SOLID = Game.spritesheet.getSprite(64+32,0,16,16);
	public static BufferedImage TILE_AGUA = Game.spritesheet.getSprite(64+48,0,16,16);
	
	public boolean solid = false;

	private BufferedImage sprite;
	protected int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void tick() {}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
