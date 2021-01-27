package dssc.project.freedom;

public final class Position {

    private final int x, y;

    public static Position createAt(int x, int y) {
        return new Position(x, y);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        final int prime=31;
        int result=17;
        result = result*prime+x;
        result = result*prime+y;
        return result;
    }

    public boolean isInSorroundingPositions(Position p){
        return x <= p.x +1 && y <= p.y +1 && x >= p.x -1 && y >= p.y -1 && !this.equals(p);
    }

    public boolean isAt(int x, int y){
        return this.x == x && this.y == y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
