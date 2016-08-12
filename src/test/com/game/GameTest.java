package com.game;

import java.util.HashMap;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.game.Game;
import com.game.Game.Shape;

public class GameTest {
	/**
	 * UNIT TEST for Game Class
	 */
	private Game game;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
    public void setUp() {
         game = new Game();
    }
	/**
	 * TESTING METHOD getRandomShape()
	 */
	@Test
	public void checkNotNullMethodGetRandomShape() throws Exception {
		String shape = game.getRandomShape();
		Assert.assertNotNull(shape);
	}
	@Test
	public void checkValuesGetRandomShape() throws Exception {
		String shape = game.getRandomShape();
		Assert.assertThat(shape, AnyOf.anyOf(Is.is(Game.Shape.PAPER.toString()), Is.is(Game.Shape.ROCK.toString()), Is.is(Game.Shape.SCISSOR.toString())));
	}
	/**
	 * TESTING METHOD getWinner()
	 */
	@Test
	public void checkNotNullMethodGetWinner() throws Exception {
		Assert.assertNotNull(game.getWinner(Shape.SCISSOR));
		Assert.assertNotNull(game.getWinner(Shape.PAPER));
		Assert.assertNotNull(game.getWinner(Shape.ROCK));
	}
	@Test
	public void checkNullMethodGetWinner() throws Exception {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Shape not valid");
		game.getWinner(null);
	}
	@Test
	public void checkRightGameRulesMethodGetWinner() throws Exception {
		int RANDOM_PLAYER_WINS = 1;
		int DEFAULT_PLAYER_WINS = 2;
		int DRAW = 0;
		Assert.assertThat(game.getWinner(Shape.SCISSOR), Is.is(DEFAULT_PLAYER_WINS));
		Assert.assertThat(game.getWinner(Shape.PAPER), Is.is(RANDOM_PLAYER_WINS));
		Assert.assertThat(game.getWinner(Shape.ROCK), Is.is(DRAW));
	}
	/**
	 * TESTING METHOD play()
	 */
	@Test
	public void checkNotNullValuesMethodPlay() throws Exception {
		HashMap<String, Integer> result = game.play();
		Assert.assertNotNull(result.get("RANDOM_PLAYER"));
		Assert.assertNotNull(result.get("DEFAULT_PLAYER"));
		Assert.assertNotNull(result.get("DRAW"));
	}
	@Test
	public void checkValuesMethodPlay() throws Exception {
		HashMap<String, Integer> result = game.play();
		int player1 = result.get("RANDOM_PLAYER");
		int player2 = result.get("DEFAULT_PLAYER");
		int draws = result.get("DRAW");
		Assert.assertThat(player1+player2+draws, Is.is(100));
	}
	
	

}
