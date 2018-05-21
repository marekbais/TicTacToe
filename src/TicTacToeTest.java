import static org.junit.Assert.*;

import org.junit.*;

public class TicTacToeTest {

	TicTacToe tic = new TicTacToe();
	
	@Test
	public void testDrawBoard() {
		assertNotNull(tic.getBoard());
	}
	
	@Test
	public void testWhoWon() {
		assertEquals('_', tic.whoWon());
	}
	
	@Test
	public void testOutcome1() {
		tic.setBoard(new char[][] {{ 'O', '_', '_' }, 
								   { 'O', 'O', 'X' }, 
								   { 'X', 'O', '_' }});
		assertFalse(tic.isDraw());
		assertEquals('_', tic.whoWon());
	}
	
	@Test
	public void testOutcome2() {
		tic.setBoard(new char[][] {{ 'O', 'X', 'O' }, 
								   { 'O', 'X', 'X' }, 
								   { 'X', 'O', 'O' }});
		assertTrue(tic.isDraw());
		assertEquals('_', tic.whoWon());
	}
	
	@Test
	public void testOutcome3() {
		tic.setBoard(new char[][] {{ 'O', 'O', 'O' }, 
								   { 'X', 'X', '_' }, 
								   { '_', '_', '_' }});
		assertEquals('O', tic.whoWon());
		assertFalse(tic.isDraw());
		
		
	}
}
