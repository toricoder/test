package com.game;

import java.util.HashMap;
import java.util.Random;

public class Game {
	public enum Shape {ROCK, SCISSOR, PAPER};
	private static final int RANDOM_PLAYER_INT = 1;
	private static final int DEFAULT_PLAYER_INT = 2;
	private static final int DRAW_INT = 0;
	private static final String RANDOM_PLAYER_STR = "RANDOM_PLAYER";
	private static final String DEFAULT_PLAYER_STR = "DEFAULT_PLAYER";
	private static final String DRAW_STR = "DRAW";
	
	/**
	 * Generates a random shape for Random Player
	 * @return String. ROCK, SCISSOR, PAPER
	 */
	public String getRandomShape(){
		Random randon = new Random();
		return Shape.values()[randon.nextInt(Shape.values().length)].toString();
	}
	/**
	 * Game's logic regarding that Default Player will always choose ROCK.</br>
	 * SCISSOR vs ROCK = default player wins.</br>
	 * PAPER vs ROCK = random player wins.</br>
	 * ROCK vs ROCK = draw.</br>
	 * @param shapeRandomPlayer. The chosen shape of random player 
	 * @return int. Player who won
	 */
	public int getWinner(Shape shapeRandomPlayer){
		int winner = -1;
		try{
			switch(shapeRandomPlayer){
			case SCISSOR:
				winner = DEFAULT_PLAYER_INT;
				break;
			case PAPER:
				winner = RANDOM_PLAYER_INT;
				break;
			case ROCK:
				winner = DRAW_INT;
				break;
		 }
		}
		catch(Exception e){
			throw new RuntimeException("Shape not valid");
		}
		return winner;
	}
	/**
	 * Plays 100 iterations of Rock, Paper, Scissors.</br>
	 * One player should always play randomly, the other should always choose rock.</br>
	 * Player who has random shape is 1, Default player (ROCK shape) is 2, a Draw is 0.</br> 
	 * Shows at the end how many games each player has won and how many were a draw.
	 * @return HashMap. Result of games won by each player and draws
	 */
	public HashMap<String, Integer> play(){
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		int gamesRandom = 0;
		int gamesDefault = 0;
		int draws = 0;
		
		for(int i=1; i<= 100; i++){
			String shape = getRandomShape();
			int winner = getWinner(Shape.valueOf(shape));
			if(winner == RANDOM_PLAYER_INT){
				gamesRandom++;
			}
			else if(winner == DEFAULT_PLAYER_INT){
				gamesDefault++;
			}
			else{
				draws++;
			}
			trackGame(shape, i);
		}
		result.put(RANDOM_PLAYER_STR, gamesRandom);
		result.put(DEFAULT_PLAYER_STR, gamesDefault);
		result.put(DRAW_STR, draws);
		printResult(result);
		return result;
	}
	/**
	 * Prints output
	 * @param gamesRandom
	 * @param gamesDefault
	 * @param draws
	 */
	private void printResult(HashMap<String, Integer> result){
		StringBuilder output = new StringBuilder();
		output.append("**************************************************************** \n");
		output.append(String.format("Player Random won %s games \n",result.get(RANDOM_PLAYER_STR)));
		output.append(String.format("Player Default won %s games \n",result.get(DEFAULT_PLAYER_STR)));
		output.append(String.format("Draws %s ",result.get(DRAW_STR)));
		output.append("\n**************************************************************** \n");
		System.out.println(output);
	}
	/**
	 * Prints each game
	 * @param shapeGenerated
	 * @param game
	 */
	private void trackGame(String shapeGenerated, int game){
		StringBuilder output = new StringBuilder();
		output.append(String.format("%s VS ROCK in game %s ",shapeGenerated,game));
		System.out.println(output);
	}
}
