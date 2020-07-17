
public class nk_TicTacToe {

	// instance variables
	private int size;
	private int line;
	private char[][] gameBoard;

	// Constructor
	public nk_TicTacToe(int board_size, int inline, int man_levels) {
		this.size = board_size;
		this.line = inline;
		gameBoard = new char[board_size][board_size];

		// Filling the gameBoard with ' '
		for (int y = 0; y < board_size; y++) {
			for (int x = 0; x < board_size; x++)
				gameBoard[x][y] = ' ';
		}
	}

	// Private method used to convert gameBoard config to string format
	private String asString(char[][] board) {
		String confStr = "";

		// left to right, top to bottom
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++)
				confStr += this.gameBoard[row][col];
		}

		return confStr;
	}

	// creates a dictionary
	public Dictionary createDictionary() {
		// Choose size in 6000-8000 range. Mine is 7333
		Dictionary dict = new Dictionary(7333);
		return dict;
	}

	// checks if the config is in the configurations dictionary
	public int repeatedConfig(Dictionary configurations) {
		String config = asString(this.gameBoard);
		return configurations.get(config);
	}

	// inserts a config string and score int into the dictionary
	public void insertConfig(Dictionary configurations, int score) {
		String config = asString(this.gameBoard);
		Record newRec = new Record(config, score);

		configurations.insert(newRec);
	}

	// stores given symbol in given gameBoard slot
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}

	// checks if given gameBoard slot is empty (' ')
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ')
			return true;
		else
			return false;
	}

	//detects if the given symbol has won the game by having a row of length k of the given symbol.
		public boolean wins(char symbol) {
			int count = 0;
			
			//checks row by row (r), resets count on each new row (1st loop)
			//check all rows
			for(int r = 0; r < size; r++) {
				count = 0;
				for(int c = 0; c < size; c++) {
					if(gameBoard[r][c] == symbol){
						count += 1;
						if(count >= this.line) {
							return true;
						}
					}
					else {
						count = 0;
					}
				}
			}
			
			//checks col by col (c), resets count on each new col (2nd loop)
			//check all cols
			for(int c = 0; c < size; c++) {
				count = 0;
				for(int r = 0; r < size; r++) {
					if(gameBoard[r][c] == symbol){
						count += 1;
						if(count >= this.line) {
							return true;
						}
					}
					else {
						count = 0;
					}
				}
			}

			
			//loops thru every square (left to right then down) for both diagonal directions
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					count = 0;				
		
					//diagonal down right
					//makes sure it is in possible bounds
					if(i <= size - line && j <= size - line) {
						
						//when at the square, checks the adjacent (down right) squares and sees if the count == line
						for(int e = 0; e < line; e++) {
							if(gameBoard[i+e][j+e] == symbol) {
								count += 1;
								if(count >= this.line) {
									return true;
								}
							} else {
								//resets the count to 0 if the line isn't complete
								count = 0;
							}
						}
					}
							
			//diagonal down left		
					//makes sure it is in possible bounds
					if(i <= size - line && j >= line-1) {
								
						//when at the square, checks the adjacent (down right) squares and sees if the count == line
						for(int e = 0; e < line; e++) {
							if(gameBoard[i+e][j-e] == symbol) {
								count += 1;
								if(count >= this.line) {
									return true;
								}
							} else {
								//resets the count to 0 if the line isn't complete
								count = 0;
							}
						}		
					}

					
				}
			}
			
			//returns false if no player has won
			return false;
		}

	// returns true if the gameBoard is full and no one has won
	public boolean isDraw() {

		// checks entire game board
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				if (gameBoard[row][col] == ' ') {
					return false;
				}
			}
		}
		// if no spaces are seen
		return true;
	}

	// returns values based on if a certain symbol wins
	public int evalBoard() {
		if (wins('O') == true)
			return 3;
		if (wins('X') == true)
			return 0;
		if (isDraw() == true)
			return 2;
		else
			return 1;
	}

}
