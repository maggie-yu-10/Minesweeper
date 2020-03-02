
public class Coordinates implements Comparable<Coordinates>{
	int x;
	int y;
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Coordinates o) {
		if (o.getX() == x && o.getY() == y) return 0;
		else if (o.getX() > x) return 1;
		else return -1;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
