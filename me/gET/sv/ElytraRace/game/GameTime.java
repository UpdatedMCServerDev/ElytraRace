package me.gET.sv.ElytraRace.game;

public class GameTime implements Runnable{
	
	private static Game game;
	private int time = 0;
	
	@Override
	public void run(){
		
		if(time == 300){
			game.getArena().stop();
			return;
		}
		
		time++;
		game.getGameBoard().setTime(time);
		
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public GameTime setGame(Game g){
		game = g;
		return this;
	}
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public int getTime(){
		return time;
	}

}
