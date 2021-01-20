# Freedom project



##### Class `Position` / `Coordinates`:

- Fields:
  - ints coordinates: `int x`, `int y`
- Methods:
  - `isOnHorizontalBorders()`
  - `isOnVerticalBorders()`
  - `getAdjacentPositions()`

##### Enum `Colour`:

- `WHITE`, `BLACK`, `NONE`

##### Class `Stone`:

- Fields:
  - `Position p`
  - `Colour colour`
  - `Bool isLive`
- Methods:
  - `makeWhite()`, `makeBlack()`
  - `isWhite()`, `isBlack()`
  - `isStoneLive()`

##### Class `Board`:

- Fields:
  - `List<Tiles> board`
- Methods:
  - `update()`
  - `check4horizontal()`, `check4vertical()`, `check4diagonal()`
  - `countLiveStones()`
  - `areAdjacentTilesOccupied()`	

##### Class `Game`:

- Fields:
  - `Board board`
  - `int numberOfStonesPlaced`
  - `int boardSize`
- Methods:
  - create and populate board
  - `isMoveValid()`



Extra: RandomPalyer and GreedyPlayer (see SML project TicTacToe or DotsAndBoxes)

WRITE WHY THIS CLASSES → see code smells in TicTacToe
