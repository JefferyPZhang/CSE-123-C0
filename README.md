# Creative Project 0: Abstract Strategy Games

**Assignment Spec:**

Strategy games are games in which players make a sequence of moves according to a set of rules hoping to achieve a particular outcome (e.g. a higher score, a specific game state) to win the game. Strategy games usually give players free choice about which moves to make (within the rules) and have little to no randomness or luck (e.g. rolling of dice, drawing of cards) involved. Abstract strategy games are a subset of strategy games usually characterized by a) perfect information (i.e. all players know the full game state at all times, and b) little to no theme or narrative around gameplay. Popular examples of abstract strategy games include: Chess, Checkers, Go, Tic-Tac-Toe, and many others.

In this assignment, you will implement a data structure to represent the game state of an abstract strategy game of your choice.

**Required Interface**
You will implement a class to represent your chosen game. Your class should implement that AbstractStrategyGame interface, which requires the following methods:

public String instructions();

Returns a string describing how to play the game. Must include how to read the game state returned by toString(), how to make a move (as used by makeMove()), how the game ends, and who wins. Should also include any additional information important to allowing someone to play the game.

public String toString();

Constructs and returns a string representation of the current game state. This representation should contain all information that should be known to players at any point in the game, including board state (if any) and scores (if any).

public boolean isGameOver();

Returns true if the game has ended, and false otherwise.

public int getWinner();

Returns the (1-based) index of the player who has won the game, or -1 if the game is not over. If the game ended in a tie, returns 0.

public int getNextPlayer();

Returns the (1-based) index of the player who will take the next turn. If the game is over, returns -1.

public void makeMove(Scanner input);

Takes input from the parameter to specify the move the player with the next turn wishes to make, then executes that move. If any part of the move is illegal, throws an IllegalArgumentException.
