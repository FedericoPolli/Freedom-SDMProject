# Freedom structure



##### Class `Position` / `Coordinates`:

This class is used to avoid the [Primitive Obsession](https://sourcemaking.com/refactoring/smells/primitive-obsession), [Long Parameter List](https://sourcemaking.com/refactoring/smells/long-parameter-list) and [Data Clumps](https://sourcemaking.com/refactoring/smells/data-clumps) smells.

- Fields:
  - ints coordinates: `int x`, `int y`
- Methods:
  - `isOnHorizontalBorders()`
  - `isOnVerticalBorders()`
  - `getAdjacentPositions()`

##### Enum `Colour`:

This `enum` is used to avoid using and working with a `string` all the time.

- `WHITE`, `BLACK`, `NONE`

##### Class `Tile` /`Stone`:

Encodes the tile of the board or the stone on the tile, <font color="red">still to be decided</font>.

- Fields:
  - `Position p`
  - `Colour colour`
  - `Bool isLive`
- Methods:
  - `makeWhite()`, `makeBlack()`
  - `isWhite()`, `isBlack()`
  - `isStoneLive()`
  - `Tile tileAt(int x, int y)` → To avoid creating new instances of `Tile` all the time, we create all tiles when initializing the board and we use this function to obtain it

##### Class `Board`:

Encodes the game board, so it's the set of the tiles / stones.

- Fields:
  - `List<Tile> board` // `Hashmap<Position, Stone> board`
- Methods:
  - `update()` / `move()` → encodes a move of a player, which updates the board during the game registering the move made. Takes a `colour` and a `position` and updates the `stone`
  - `check4horizontal()`, `check4vertical()`, `check4diagonal()` → they find the stones which are part of 4 horizontal, vertical or diagonal stones
  - `countLiveStones()` → counts how many live stones per player
  - `areAdjacentTilesOccupied()` → checks if the stones around one are all occupied or not

##### Class `Game`:

Encodes the progress of the game, so it creates and populates the board, decides who plays, if a move is valid, the winner.

- Fields:
  - `Board board`
  - `int numberOfStonesPlaced`
  - `int boardSize`
- Methods:
  - create and populate board
  - `isMoveValid()`
  - `winner()` → decides and proclaims the winner



##### Extra

RandomPalyer and GreedyPlayer (see SML project TicTacToe or DotsAndBoxes) or user interface with JavaFX.