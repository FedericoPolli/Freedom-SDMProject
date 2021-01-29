# Freedom structure




##### Enum `Colour`:

This `enum` is used to avoid using and working with a `string` all the time.

- `WHITE`, `BLACK`, `NONE`

##### Class `Position` / `Coordinates`:

This class is used to avoid the [Primitive Obsession](https://sourcemaking.com/refactoring/smells/primitive-obsession), [Long Parameter List](https://sourcemaking.com/refactoring/smells/long-parameter-list) and [Data Clumps](https://sourcemaking.com/refactoring/smells/data-clumps) smells.

- *Fields:*
  - ints coordinates: `int x`, `int y`
- *Methods:*
  - `isInSorroundingPositions()`
  - `createAt()` 

##### Class `Stone`:

Encodes the stone on the tile.

- *Fields:*
  - `Colour colour`
  - `Bool isLive`
- *Methods:*
  - `isOfColour()` → confronts the Colour of the stone with a given `Colour`
  - `isNotColored()` → checks if the Stone has Colour `NONE`
  - `makeColoured()` → if a Stone has not a Colour it colours it with the given Colour
  - `isLive()` → getter for the field `isLive` 
  - `makeLive()` → setter for the field `isLive`

##### Class `Board`:

Encodes the game board, so it's the set of the tiles / stones.

- *Fields:*
  - `HashMap<Position, Stone>`
- *Methods:*
  - `getStoneAt()` → To avoid creating new instances of `Stone` all the time, we create all stones when initializing the board and we use this function to obtain it
  - `updateStoneAt()` → encodes a move of a player, which updates the board during the game registering the move made. Takes a `colour` and a `position` and updates the `stone`
  - `countLiveStones()` → counts how many live stones per player
  - `areAdjacentPositionOccupied()` → checks if the stones around one are all occupied or not
  - `check4()` → finds the stones which are part of 4 horizontal, vertical or diagonal stones and makes them live
  - `checkAllDirections()` → calls `check4()` in all possible directions

##### Class `Game`:

Encodes the progress of the game, so it creates and populates the board, decides who plays, if a move is valid, the winner.

- *Fields:*
  - `Board board`
  - `int boardSize`
  - `int numberOfStonesPlaced`
- *Methods:*
  - creates and populates board
  - `isMoveValid()`
  - `play()`
  - `winner()` → decides and proclaims the winner

##### Class `Main`:

Makes the game start.


##### Extra

RandomPalyer and GreedyPlayer (see SML project TicTacToe or DotsAndBoxes) or user interface with JavaFX.