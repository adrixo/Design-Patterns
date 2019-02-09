//the visitor design pattern is a way of separating an algorithm from an object structure on which it operates. A practical result of this separation is the ability to add new operations to existent object structures without modifying the structures. It is one way to follow the open/closed principle.

//In essence, the visitor allows adding new virtual functions to a family of classes, without modifying the classes. Instead, a visitor class is created that implements all of the appropriate specializations of the virtual function. The visitor takes the instance reference as input, and implements the goal through double dispatch.

import java.util.List;

interface CarElement {
    void accept(CarElementVisitor visitor);
}

interface CarElementVisitor {
    void visit(Body body);
    void visit(Car car);
    void visit(Engine engine);
    void visit(Wheel wheel);
}

class Wheel implements CarElement {
  private final String name;

  public Wheel(final String name) {
      this.name = name;
  }

  public String getName() {
      return name;
  }

  @Override
  public void accept(CarElementVisitor visitor) {
      /*
       * accept(CarElementVisitor) in Wheel implements
       * accept(CarElementVisitor) in CarElement, so the call
       * to accept is bound at run time. This can be considered
       * the *first* dispatch. However, the decision to call
       * visit(Wheel) (as opposed to visit(Engine) etc.) can be
       * made during compile time since 'this' is known at compile
       * time to be a Wheel. Moreover, each implementation of
       * CarElementVisitor implements the visit(Wheel), which is
       * another decision that is made at run time. This can be
       * considered the *second* dispatch.
       */
      visitor.visit(this);
  }
}

class Body implements CarElement {
  @Override
  public void accept(CarElementVisitor visitor) {
      visitor.visit(this);
  }
}

class Engine implements CarElement {
  @Override
  public void accept(CarElementVisitor visitor) {
      visitor.visit(this);
  }
}

class Car implements CarElement {
    private final List<CarElement> elements;

    public Car() {
        this.elements = List.of(
            new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left"), new Wheel("back right"),
            new Body(), new Engine()
        );
    }

    @Override
    public void accept(CarElementVisitor visitor) {
        for (CarElement element : elements) {
            element.accept(visitor);
        }
        visitor.visit(this);
    }
}

class CarElementDoVisitor implements CarElementVisitor {
    @Override
    public void visit(Body body) {
        System.out.println("Moving my body");
    }

    @Override
    public void visit(Car car) {
        System.out.println("Starting my car");
    }

    @Override
    public void visit(Wheel wheel) {
        System.out.println("Kicking my " + wheel.getName() + " wheel");
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Starting my engine");
    }
}

class CarElementPrintVisitor implements CarElementVisitor {
    @Override
    public void visit(Body body) {
        System.out.println("Visiting body");
    }

    @Override
    public void visit(Car car) {
        System.out.println("Visiting car");
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }

    @Override
    public void visit(Wheel wheel) {
        System.out.println("Visiting " + wheel.getName() + " wheel");
    }
}

public class VisitorDemo {
    public static void main(final String[] args) {
        var car = new Car();

        car.accept(new CarElementPrintVisitor());
        car.accept(new CarElementDoVisitor());
    }
}
