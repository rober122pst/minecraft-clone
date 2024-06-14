package com.robergames.main;

import java.awt.Color;
import java.awt.Graphics;

import com.robergames.world.Camera;
import com.robergames.world.FloorTile;
import com.robergames.world.Tile;
import com.robergames.world.WallTile;
import com.robergames.world.World;

public class Inventario {
	
	public int selected = 0;
	
	public int inventorySize = 45;
	
	public boolean isPressed = false;
	public boolean isPlaceItem = false;
	public int mx,my;
	
	public String[] itens = {"grama","terra","neve","areia","pedra","ar",""};
	
	public int initialPosition = ((Game.WIDTH*Game.SCALE) / 2) - ((itens.length*inventorySize) / 2);
	
	public void tick() {
		if(isPressed) {
			isPressed = false;
			if(mx >= initialPosition && mx < initialPosition + (inventorySize*itens.length)) {
				if(my >= Game.HEIGHT*Game.SCALE - inventorySize - 1 && my < Game.HEIGHT*Game.SCALE - inventorySize - 1 + inventorySize) {
					selected = (int) (mx-initialPosition)/inventorySize;
					System.out.println("Foi selecionado: "+itens[selected]);
				}
			}
		}
		
		if(isPlaceItem) {
			isPlaceItem = false;
			mx = (int)mx/3 + Camera.x;
			my = (int)my/3 + Camera.y;
			
			int tilex = mx/16;
			int tiley = my/16;
			if(World.tiles[tilex+tiley*World.WIDTH].solid == false) {
				if(itens[selected] == "grama") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_GRAMA);
					if(tiley-1 >= 0 && World.tiles[tilex+(tiley-1)*World.WIDTH] instanceof WallTile) {
						World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_TERRA);
					}
				}else if(itens[selected] == "terra") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_TERRA);
				}else if(itens[selected] == "neve") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_NEVE[0]);
				}else if(itens[selected] == "areia") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_AREIA);
				}else if(itens[selected] == "pedra") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_PEDRA);
				}else if(itens[selected] == "ar") {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_AR);
				}
				
				if(World.isFree(Game.player.getX(), Game.player.getY()) == false) {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_AR);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < itens.length; i++) {
			g.setColor(new Color(128,43,0));
			g.fillRect(initialPosition + (i*inventorySize) + 1, Game.HEIGHT*Game.SCALE - inventorySize - 1, inventorySize, inventorySize);
			g.setColor(Color.gray);
			g.drawRect(initialPosition + (i*inventorySize) + 1, Game.HEIGHT*Game.SCALE - inventorySize - 1, inventorySize, inventorySize);
			if(itens[i] == "grama") 
			{
				g.drawImage(Tile.TILE_GRAMA, initialPosition + (i*inventorySize) + 8, Game.HEIGHT*Game.SCALE - inventorySize + 6, 32, 32, null);
			}else if(itens[i] == "terra") {
				g.drawImage(Tile.TILE_TERRA, initialPosition + (i*inventorySize) + 8, Game.HEIGHT*Game.SCALE - inventorySize + 6, 32, 32, null);
			}else if(itens[i] == "neve") {
				g.drawImage(Tile.TILE_NEVE[0], initialPosition + (i*inventorySize) + 8, Game.HEIGHT*Game.SCALE - inventorySize + 6, 32, 32, null);
			}else if(itens[i] == "areia") {
				g.drawImage(Tile.TILE_AREIA, initialPosition + (i*inventorySize) + 8, Game.HEIGHT*Game.SCALE - inventorySize + 6, 32, 32, null);
			}else if(itens[i] == "pedra") {
				g.drawImage(Tile.TILE_PEDRA, initialPosition + (i*inventorySize) + 8, Game.HEIGHT*Game.SCALE - inventorySize + 6, 32, 32, null);
			}else if(itens[i] == "ar") {
				g.drawImage(Tile.TILE_AR, initialPosition + (i*inventorySize) + 8, Game.HEIGHT*Game.SCALE - inventorySize + 6, 32, 32, null);
			}
			
			if(selected == i) {
				g.setColor(Color.red);
				g.drawRect(initialPosition + (i*inventorySize), Game.HEIGHT*Game.SCALE - inventorySize - 1, inventorySize, inventorySize);
			}
		}
	}
	
}
