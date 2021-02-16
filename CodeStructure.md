# Freedom structure



##### Enum `Direction`:

`Enum` that represents the direction in which to move.

It is used to avoid working with two integers like `(1, 0)` to move right.

- `RIGHT(1, 0)`, `LEFT(-1, 0)`, `UP(0, 1)`, `DOWN(0, -1)`, `UP_MAIN_DIAGONAL(1, 1)`, `DOWN_MAIN_DIAGONAL(-1, -1)`, `UP_ANTI_DIAGONAL(-1, 1)`, `DOWN_ANTI_DIAGONAL(1, -1)`.
- *Fields:*
  - `int` coordinates of the direction: `int x`, `int y`
- *Methods:*
  - constructor

##### Enum `Colour`:

`Enum` that represents the colours of a `Stone`. This `Enum` represents the colours of a `Stone`: it can be white, black or empty, thus without colour.

It is used to avoid working with a `String` all the time.

- `WHITE`, `BLACK`, `NONE`.

##### Class `Position`:

Class that represents a position in a grid. This class will represent the position of a `Stone` in the `Board` of the `Game`.

This class is used to encapsulate the fields `x` and `y` so to avoid the [Primitive Obsession](https://sourcemaking.com/refactoring/smells/primitive-obsession), [Long Parameter List](https://sourcemaking.com/refactoring/smells/long-parameter-list) and [Data Clumps](https://sourcemaking.com/refactoring/smells/data-clumps) smells.

- *Fields:*
  - `int` coordinates: `int x`, `int y`
- *Methods:*
  - constructor
  - `at()` → creates a new Position at the specified coordinates
  - getters
  - `moveInDirection(int x, int y)` → returns a `Position` obtained by moving <code>x</code> steps in horizontal and <code>y</code> steps in vertical.
  - `isInAdjacentPositions(Position p)` → checks if a `Position` is adjacent another `Position`

##### Class `Stone`:

Class that represents a stone on the board's tile in the game. It has a Colour and at the end of the game is made "live" if it is part of four adjacent stones (horizontally, vertically or diagonally). All the Stones are stored in the Board of the Game.

- *Fields:*
  - `Colour colour` → the `Colour` of the `Stone`
  - `boolean liveStatus` → status of the `Stone`: stores if the `Stone` is "live" (`true`) or not (`false`)
- *Methods:*
  - constructor
  - `getColour()` → getter for the `Colour` of a `Stone`.
  - `isOfColour()` → confronts the Colour of the stone with a given `Colour`
  - `isOfSameColourAs()` → checks if a `Stone` is of the same `Colour` of another `Stone`
  - `makeOfColour()` → colours a `Stone` with the given `Colour`
  - `isLive()` → getter for the field `liveStatus` 
  - `changeLiveStatusTo()` → setter for the field `liveStatus`

##### Class `Board`:

Class that represents the board of the game.

It has a set of `Stone`s and at the end of the `Game` it checks which are "live" and which not.

- *Fields:*
  - `HashMap<Position, Stone> board` → dictionary that stores all the `Stone`s and their `Position` in the `Board`
  - `int boardSize` → the dimension of the board
- *Methods:*
  - constructor → creates and populates the board with `Stone`s of `Colour` `NONE`
  - `getStoneAt()` → returns the `Stone` in the `Board` at a given `Position`. To avoid creating new instances of `Stone` all the time, we create all stones when initializing the board and we use this function to obtain them
  - `getBoardSize()` → getter for the size of a `Board`
  - `updateStoneAt()` → encodes a move of a player, which updates the board during the game registering the move made. Takes a `colour` and a `position` and updates the `stone`
  - `setAllStonesDead()` → sets all the `Stone`s of the `Board` as not "live". Used when we decide if the last move of the game is convenient for the player
  - `countLiveStones()` → counts how many live stones there are for a player
  - `getFreePositions()` → !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  - `getFreeAdjacentPositions()` → !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  - `areAdjacentPositionOccupied()` → checks if the stones around one are all occupied or not
  - `stoneIsAlreadyPlacedAt(Position current)` → checks if a `Stone` has already been placed in the `Position` taken as input. This is done by checking the `Colour` of the `Stone`: it has not been placed only if the `Colour` is <code>NONE</code>.
  - `positionIsInsideTheBoard()` → checks if the input `Position` is inside the `Board` or not.
  - `countStonesInRow()` → checks the whole `Board` in the horizontal, vertical and diagonal `Direction`s to find the `Stone`s which are part of a row of exactly four `Stone`s of the same `Colour`, then makes them "live".
  - `printBoard()` → prints the `Board` in a graphical way

##### Class `Game`:

Class that represents the game itself. So it creates and populates the board, decides who plays, if a move is valid, the winner.

It doesn't take care of interacting with the terminal nor of the players. We used `CommandLineGame` for that.

- *Fields:*
  - `Board board` → the board on which the game is played
  - `Position previous` → auxiliary field to know the previous played position
- *Methods:*
  - constructor → creates the `Board`
  - `move()` → represents the move of a player: adds a `Stone` of the given `Colour` in the given `Position`. Actually, it only changes the `Colour` of the `Stone` because it has already been created in the `Board`
  - `isMoveValid()` → checks if the move of the player is valid
  - `isLastMoveConvenient()` → decides if in the last move of the entire game placing the `Stone` at a given `Position` is convenient or not for the player
  - `winner()` → decides and proclaims the winner of the game

##### Class `CommandLineGame`:

Class that extends the `Game` taking care of interacting with the terminal and with the players.

- *Fields:*
  - Player player1, player2;
- *Methods:*
  - constructor
  - `play()` → 
  - `getCurrentPlayer(int i)` → 
  - `getValidPosition(Player currentPlayer)`
  - `isLastMove()`
  - `isMoveValid()`
  - `winner()`

##### Class `Utility`:

- *Methods:*
  - `getInteger()`
  - `getRandomInteger()`
  - `getOS()`
  - `getWhite()` and `getBlack()`

##### Class `Main`:

Class that controls the execution of the game. It makes the game start and go on.


### Extra

##### Abstract Class `Player`:

##### Class `HumanPalyer`:

##### Class `RandomPalyer`:

##### Class `GreedyPlayer`:



## To Do:

- Refactoring of `Main`
- Refactoring of `CLGameTests` and `GreedyPlayerTests`
- Testing `Main`
- Check methods to be put `private`