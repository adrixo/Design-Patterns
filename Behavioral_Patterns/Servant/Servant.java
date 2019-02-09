//In software engineering, the servant pattern defines an object used to offer some functionality to a group of classes without defining that functionality in each of them. A Servant is a class whose instance (or even just class) provides methods that take care of a desired service, while objects for which (or with whom) the servant does something, are taken as parameters.

// Servant class, offering its functionality to classes implementing
// Movable Interface
public class MoveServant {
	// Method, which will move Movable implementing class to position where
	public void moveTo(Movable serviced, Position where) {
		// Do some other stuff to ensure it moves smoothly and nicely, this is
		// the place to offer the functionality
		serviced.setPosition(where);
	}

	// Method, which will move Movable implementing class by dx and dy
	public void moveBy(Movable serviced, int dx, int dy) {
		// this is the place to offer the functionality
		dx += serviced.getPosition().xPosition;
		dy += serviced.getPosition().yPosition;
		serviced.setPosition(new Position(dx, dy));
	}
}

// Interface specifying what serviced classes needs to implement, to be
// serviced by servant.
public interface Movable {
	public void setPosition(Position p);

	public Position getPosition();
}

// One of geometric classes
public class Triangle implements Movable {
	// Position of the geometric object on some canvas
	private Position p;

        // Method, which sets position of geometric object
	public void setPosition(Position p) {
		this.p = p;
	}

	// Method, which returns position of geometric object
	public Position getPosition() {
		return this.p;
	}
}

// One of geometric classes
public class Ellipse implements Movable {
	// Position of the geometric object on some canvas
	private Position p;

	// Method, which sets position of geometric object
	public void setPosition(Position p) {
		this.p = p;
	}

	// Method, which returns position of geometric object
	public Position getPosition() {
		return this.p;
	}
}

// One of geometric classes
public class Rectangle implements Movable {
	// Position of the geometric object on some canvas
	private Position p;

	// Method, which sets position of geometric object
	public void setPosition(Position p) {
		this.p = p;
	}

	// Method, which returns position of geometric object
	public Position getPosition() {
		return this.p;
	}
}

// Just a very simple container class for position.
public class Position {
	public int xPosition;
	public int yPosition;

	public Position(int dx, int dy) {
		xPosition = dx;
		yPosition = dy;
	}
}
