package com.robergames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.robergames.main.Game;
import com.robergames.world.Camera;
import com.robergames.world.World;

public class Player extends Entity{
	
	public boolean right, left;
	
	public double life = 20;
	
	private int dir = 1;
	
	public boolean jump = false;
	
	public boolean isJumping = false;
	public int jumpHeight = 56;
	public int jumpFrames = 0;
	
	public double vspd = 0;
	
	private int framesAnimations = 0;
	private int maxFrames = 15;
	
	private int maxSprite = 2;
	private int curSprite = 0;
	
	public BufferedImage ATTACK_RIGHT, ATTACK_LEFT;
	
	public boolean attack = false;
	public boolean isAttacting = false;
	public int attackFrames = 0;
	public int maxFramesAttack = 10;
	
	private int atkSpeed = 0, maxAtkSpeed = 30;
	
	private double gravity = 0.3;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		ATTACK_RIGHT = Game.spritesheet.getSprite(0, 64, 16, 16);
		ATTACK_LEFT = Game.spritesheet.getSprite(16, 64, 16, 16);
	}
	
	public void tick(){
		depth = 2;
		vspd+=gravity;
		if(!World.isFree((int)x,(int)(y+1)) && jump)
		{
			vspd = -5;
			jump = false;
		}
		
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
		
		if(right && World.isFree((int)(x+speed), (int) y)) {
			x+=speed;
			dir = 1;
		}
		else if(left && World.isFree((int)(x-speed), (int) y)) {
			x-=speed;
			dir = -1;
		}
		
		//Sistema de Ataque
		if(attack) {
			if(isAttacting == false) {
				attack = false;
				isAttacting = true;
			}
		}
		
		if(isAttacting) {
			attackFrames++;
			if(attackFrames == maxFramesAttack) {
				attackFrames = 0;
				isAttacting = false;
			}
		}
		
		colisionEnemy();
		
		Camera.x = Camera.clamp((int) x - Game.WIDTH/2, 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp((int) y - Game.HEIGHT/2, 0, World.HEIGHT*16 - Game.HEIGHT);
	}
	public void colisionEnemy() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {
					atkSpeed++;
					if(atkSpeed >= maxAtkSpeed) {
						atkSpeed = 0;
						life--;
					}	
				}
				if(isAttacting) {
					atkSpeed++;
					if(atkSpeed >= maxAtkSpeed) {
						atkSpeed = 0;
						((Enemy) e).vida-=2;
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		framesAnimations++;
		if(framesAnimations == maxFrames) {
			curSprite++;
			framesAnimations = 0;
			if(curSprite == maxSprite) {
				curSprite = 0;
			}
		}
		if(dir == 1) {
			sprite = Entity.PLAYER_RIGHT[curSprite];
			if(isAttacting) {
				g.drawImage(ATTACK_RIGHT, (this.getX()+8) - Camera.x, this.getY() - Camera.y, null);
			}
		}else if(dir == -1) {
			sprite = Entity.PLAYER_LEFT[curSprite];
			if(isAttacting) {
				g.drawImage(ATTACK_LEFT, (this.getX()-8) - Camera.x, this.getY() - Camera.y, null);
			}
		}
		super.render(g);
	}

}
