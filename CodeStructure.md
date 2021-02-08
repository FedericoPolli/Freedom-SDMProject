# Freedom structure


##### Enum `Colour`:

`Enum` that represents the colours of a `Stone`.

It is used to avoid using and working with a `String` all the time.

- `WHITE`, `BLACK`, `NONE`

##### Class `Position`:

Class that represents a position in a cartesian space.

This class is used to encapsulate the fields `x` and `y` so to avoid the [Primitive Obsession](https://sourcemaking.com/refactoring/smells/primitive-obsession), [Long Parameter List](https://sourcemaking.com/refactoring/smells/long-parameter-list) and [Data Clumps](https://sourcemaking.com/refactoring/smells/data-clumps) smells.

- *Fields:*
  - ints coordinates: `int x`, `int y`
- *Methods:*
  - constructor
  - `at()` → creates a new Position at the specified coordinates
  - getters
  - `isInAdjacentPositions()` → checks if a `Position` is adjacent another `Position`

##### Class `Stone`:

Class that represents a stone on the board's tile in the game. It has a Colour and at the end of the game is made "live" if it is part of 4 adjacent stones (horizontally, vertically or diagonally). All the Stones are stored in the Board of the Game.

- *Fields:*
  - `Colour colour` → the `Colour` of the `Stone`
  - `Bool liveStatus` → status of the `Stone`: stores if the `Stone` is "live" (true) or not (false)
- *Methods:*
  - constructor
  - `createEmpty()` → Creates an empty `Stone`, thus a `Stone` with `Colour` equal to `NONE`.
  - `isOfColour()` → confronts the Colour of the stone with a given `Colour`
  - `makeColoured()` → if a Stone has not a Colour it colours it with the given Colour
  - `isOfSameColourAs()` → checks if a `Stone` is of the same `Colour` of another `Stone`
  - `isLive()` → getter for the field `liveStatus` 
  - `changeLiveStatusTo()` → setter for the field `liveStatus`

##### Class `Board`:

Class that represents the board of the game.

It has a set of `Stone`s and at the end of the `Game` it checks which are "live" and which not.

- *Fields:*
  - `HashMap<Position, Stone> board` → dictionary that stores all the `Stone`s and their `Position` in the `Board`
- *Methods:*
  - constructor
  - `getStoneAt()` → returns the `Stone` in the `Board` at a given `Position`. To avoid creating new instances of `Stone` all the time, we create all stones when initializing the board and we use this function to obtain them
  - `updateStoneAt()` → encodes a move of a player, which updates the board during the game registering the move made. Takes a `colour` and a `position` and updates the `stone`
  - `setAllStonesDead()` → sets all the `Stone`s of the `Board` as not "live". Used when we decide if the last move of the game is convenient for the player
  - `countLiveStones()` → counts how many live stones there are for a player
  - `areAdjacentPositionOccupied()` → checks if the stones around one are all occupied or not
  - `check4InDirection()` → finds the stones which are part of exactly 4 horizontal, vertical or diagonal stones and makes them "live"
  - `checkBoardAndMakeStonesLive()` → calls `check4InDirection()` in all possible directions
  - `printBoard()` → prints the `Board` in a graphical way

##### Class `Game`:

Class that represents the game itself. So it creates and populates the board, decides who plays, if a move is valid, the winner.

- *Fields:*
  - `Board board` → the board on which the game is played
  - `int boardSize` → the dimension of the board
  - `int numberOfStonesPlaced` → auxiliary field to know the previous played position
- *Methods:*
  - constructor → creates and populates board
  - `play()` → represents the move of a player: adds a `Stone` of the given `Colour` in the given `Position`. Actually, it only changes the `Colour` of the `Stone` because it has already been created in the `Board`
  - `isMoveValid()` → checks if the move of the player is valid
  - `winner()` → decides and proclaims the winner of the game
  - `isLastMoveConvenient()` → decides if in the last move of the entire game placing the `Stone` at a given `Position` is convenient or not for the player

##### Class `Main`:

Class that controls the execution of the game. It makes the game start and go on.


##### Extra

RandomPalyer and GreedyPlayer (see SML project TicTacToe or DotsAndBoxes) or user interface with JavaFX.


## ToDo

- `printBoard()` method → find characters to be printed as White and Black
- Refactoring of `Main`
- Testing `Main`
- Check methods to be put `private`
- Refactor name `moveInDirection()` in `Position`