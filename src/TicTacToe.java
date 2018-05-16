import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class TicTacToe {
	private final static Logger logr = Logger.getLogger(TicTacToe.class.getName());
	Scanner input = new Scanner(System.in);

	private char[][] board = { { 'X', '_', '_' }, { 'O', 'O', 'X' }, { 'X', 'O', 'O' } };
	private int turn = 2;
	private char player1 = 'O';
	private char player2 = 'X';

	public void resetAll() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = '_';
			}
		}
		turn = 2;
	}

	public void drawBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + "|");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	public void drawIndexBoard() {
		System.out.println("-------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board.length; j++) {
				System.out.print(((i * 3) + j + 1) + "|");
			}
			System.out.println();
		}
		System.out.println("-------");
	}

	// char of the winner
	public char whoWon() {
		char winner = '_';

		for (int i = 0; i < board.length; i++) {
			// System.out.println("for " + i);
			// row win
			if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
				winner = board[i][0];
			}
			// column win
			else if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
				winner = board[0][i];
			}
			// diagonal win
			else if ((board[0][i] == board[1][1]) && (board[1][1] == board[2][(board.length - 1) - i])) {
				winner = board[1][1];
			}
			// System.out.println("row cond:" + ((board[i][0] == board[i][1]) &&
			// (board[i][1] == board[i][2])));
			// System.out.println("column cond:" + ((board[0][i] == board[1][i])
			// && (board[1][i] == board[2][i])));
			// System.out.println("diagon cond:" + ((board[0][i] == board[1][1])
			// && (board[1][1] == board[2][(board.length - 1) - i])));
			// System.out.println("winner:" + winner);
			if (winner != '_')
				i = board.length + 1;
		}
		return winner;
	}

	public boolean isDraw() {
		if (whoWon() == '_') {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == '_')
						return false;
				}
			}
		}
		return true;
	}

	// changes playerNo in int turn
	public void changeTurn() {
		if (turn == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
	}

	// puts player's char on the board and changes turn
	public void makeMove(int indexNo) {

		if (turn == 1) {
			indexNo--; // compensation between user and computer indices
			board[indexNo / board.length][indexNo % board.length] = player1;
			System.out.println("[" + indexNo / board.length + "][" + indexNo % board.length + "]" + player1);

		} else {
			board[indexNo / board.length][indexNo % board.length] = player2;
			System.out.println("[" + indexNo / board.length + "][" + indexNo % board.length + "]" + player2);
		}
		changeTurn();
	}

	public boolean isMoveValid(int indexNo) {
		if (board[indexNo / board.length][indexNo % board.length] == '_')
			return true;
		else
			return false;
	}

	public void playersTurn() {
		int indexFromPlayer = 0;
		System.out.println("Press the number of index where you want to place your X/O: ");
		
		while(!input.hasNextInt()) {
			System.out.println("Number!!!");
		}
//		 try {
//		 indexFromPlayer = input.nextInt();
//		 }
//		 catch(Exception InputMismatchException) {
//		 System.out.println("Invalid index, please select a number 1-9!");
//		 playersTurn();
//		 }
//		//indexFromPlayer = input.nextInt();
//		// guarantees valid index
		while ((indexFromPlayer > 10 || indexFromPlayer < 1) || !isMoveValid(indexFromPlayer - 1)) {
			System.out.println("Invalid index, please select one of the empty fields!");
			indexFromPlayer = input.nextInt();
		}
		makeMove(indexFromPlayer);
	}

	private boolean matchFound;

	public void AIsTurn(char playerChar) {
		matchFound = false;
		for (int i = 0; i < board.length; i++) {

			// row match
			if (board[i][0] == board[i][1] && board[i][0] == playerChar) {
				if (isMoveValid(i * 3 + 2)) {
					makeMove(i * 3 + 2);
					matchFound = true;
					System.out.println(i + "row");
				}

			} else if (board[i][1] == board[i][2] && board[i][1] == playerChar) {
				if (isMoveValid(i * 3)) {
					makeMove(i * 3);
					matchFound = true;
					System.out.println(i + "row");
				}

			} else if (board[i][0] == board[i][2] && board[i][0] == playerChar) {
				if (isMoveValid(i * 3 + 1)) {
					makeMove(i * 3 + 1);
					matchFound = true;
					System.out.println(i + "row");

				}
			}
			// column match
			else if (board[0][i] == board[1][i] && board[0][i] == playerChar) {
				if (isMoveValid(2 * 3 + i)) {
					makeMove(2 * 3 + i);
					matchFound = true;
					System.out.println(i + "column");
				}

			} else if (board[1][i] == board[2][i] && board[1][i] == playerChar) {
				System.out.println("column running");
				if (isMoveValid(i)) {
					makeMove(i);
					matchFound = true;
					System.out.println(i + "column");
				}

			} else if (board[0][i] == board[2][i] && board[0][i] == playerChar) {
				if (isMoveValid(3 + i)) {
					makeMove(3 + i);
					matchFound = true;
					System.out.println(i + "column");
				}
			}
			// diagonal match
			else if (board[0][i] == board[1][1] && board[1][1] == playerChar) {
				if (isMoveValid(2 * 3 + (board.length - i - 1))) {
					makeMove(2 * 3 + (board.length - i - 1));
					matchFound = true;
					System.out.println(i + "diagonal");
				}
			} else if (board[2][i] == board[1][1] && board[1][1] == playerChar) {
				if (isMoveValid(board.length - i - 1)) {
					makeMove(board.length - i - 1);
					matchFound = true;
					System.out.println(i + "diagonal");
				}
			} else if (board[0][i] == board[2][board.length - i - 1] && board[0][i] == playerChar) {
				if (isMoveValid(4)) {
					makeMove(4);
					matchFound = true;
					System.out.println(i + "diagonal");
				}
			}

			// matchFound terminate for loop
			// if (matchFound) i = board.length + 1;
		}
		// no match found, choose random index
		// if (!matchFound) {
		// int indexAI = (int) (Math.random() * (board.length*board.length));
		// while(!isMoveValid(indexAI)) {
		// indexAI = (int) (Math.random() * (board.length*board.length));
		// System.out.println(indexAI);
		// }
		// makeMove(indexAI);
		// System.out.println("random");
		// }
	}

	public void AIsRandomMove() {
		int indexAI = (int) (Math.random() * (board.length * board.length));
		while (!isMoveValid(indexAI)) {
			indexAI = (int) (Math.random() * (board.length * board.length));
			System.out.println(indexAI);
		}
		makeMove(indexAI);
		System.out.println("random");
	}

	// initializing the game
	public void startGame() {
		System.out.println("Do you want to start a new game? Y/N");
		String answer = input.nextLine();
		if (answer.equals("Y")) {
			mainGameSequence();
		} else if (answer.equals("N")) {
			System.out.println("See you later, alligator");
		} else {
			System.out.println("Wrong key, try again!");
			startGame();
		}

	}

	public void gameOverSequence() {

		if (whoWon() == player1) {
			System.out.println("Congratulations, you won!");
		} else if (whoWon() == player2) {
			System.out.println("You lost!");
		} else if (isDraw()) {
			System.out.println("Draw!");
		}
		System.out.println("Do you want to play again? Y/N");
		switch (input.next()) {
		case "Y":
			mainGameSequence();
			break;
		case "N":
			System.out.println("See you later, alligator!");
			break;
		default:
			System.out.println("Invalid key!");
			gameOverSequence();
			break;
		}
		// if(input.next().equals("Y")) {
		// mainGameSequence();
		// } else if (input.next().equals("N")) {
		// System.out.println("Thanks for playing!");
		// }
	}

	public void mainGameSequence() {
		resetAll();
		drawIndexBoard();
		
		while (!isDraw() && (whoWon() == '_')) {
			drawBoard();
			// player's turn
			if (turn == 1) {
				playersTurn();
			} else {
				// AI's turn
				AIsTurn(player2);
				System.out.println("MatchFound X: " + matchFound);
				if (!matchFound) {
					AIsTurn(player1);
					System.out.println("MatchFound O: " + matchFound);
					if (!matchFound) {
						AIsRandomMove();
					}
				}
			}
		}
		drawBoard();
		gameOverSequence();
	}

	public static void main(String[] args) throws SecurityException, IOException {
		TicTacToe game = new TicTacToe();
		LogManager.getLogManager().reset();
		FileHandler fh = new FileHandler("TTTlog.log");
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.INFO);
//		logr.addHandler(ch);
		ch.setFormatter(new SimpleFormatter());
		
		fh.setLevel(Level.FINE);
		logr.addHandler(fh);

		logr.setLevel(Level.INFO);
		logr.log(Level.INFO, "Initializing game");
		logr.log(Level.FINE, "Player goes first");
		game.startGame();
		// System.out.println(game.isMoveValid(1));
		// game.AIsTurn();
	}
}
