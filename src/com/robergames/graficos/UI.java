package com.robergames.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.robergames.entities.Player;
import com.robergames.main.Game;
import com.robergames.world.World;

public class UI {
	
	public int seconds = 0;
	public int minutes = 0;
	public int frames = 0;
	
	public void tick() { 
		frames++;
		if(frames == 60) {
			frames = 0;
			seconds++;
			if(seconds == 60) {
				minutes++;
				seconds = 0;
				if(minutes % 1 == 0) {
					World.CICLO++;
					if(World.CICLO > World.noite) {
						World.CICLO = 0;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		int curlife = (int) ((Game.player.life/20)*100);
		g.setColor(Color.red);
		g.fillRect(Game.inventario.initialPosition, Game.HEIGHT*Game.SCALE-70, 100, 20);
		g.setColor(Color.green);
		g.fillRect(Game.inventario.initialPosition, Game.HEIGHT*Game.SCALE-70, curlife, 20);
		
		String formatTime = "";
		if(minutes < 10) {
			formatTime += "0"+minutes+":";
		}else {
			formatTime += minutes+":";
		}
		
		if(seconds < 10) {
			formatTime += "0"+seconds;
		}else {
			formatTime += seconds;
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,23));
		g.drawString(formatTime, 14, 30);
	}
	
}
