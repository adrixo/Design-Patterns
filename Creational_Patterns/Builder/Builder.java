import java.util.List;
import java.util.Random;

//The Builder is a design pattern designed to provide a flexible solution to various object creation problems in object-oriented programming. The intent of the Builder design pattern is to separate the construction of a complex object from its representation.

class Car {
  private int wheels;
  private String color;

  public Car() {
  }

  public String getColor() {
      return color;
  }

  public void setColor(final String color) {
      this.color = color;
  }

  public int getWheels() {
      return wheels;
  }

  public void setWheels(final int wheels) {
      this.wheels = wheels;
  }

  @Override
  public String toString() {
      return "Car [wheels = " + wheels + ", color = " + color + "]";
  }
}

/**
 * The builder abstraction.
 */
interface CarBuilder {
  Car build();

  CarBuilder setColor(final String color);

  CarBuilder setWheels(final int wheels);
}

class CarBuilderImpl implements CarBuilder {
  private Car carTmp;

  public CarBuilderImpl() {
    carTmp = new Car();
  }

  @Override
  public Car build() {
    Car car = new Car();
    car.setColor(carTmp.getColor());
    car.setWheels(carTmp.getWheels());
    return car;
  }

  @Override
  public CarBuilder setColor(final String color) {
    carTmp.setColor(color);
    return this;
  }

  @Override
  public CarBuilder setWheels(final int wheels) {
    carTmp.setWheels(wheels);
    return this;
  }
}

public class CarBuildDirector {
  private CarBuilder builder;

  public CarBuildDirector(final CarBuilder builder) {
    this.builder = builder;
  }

  public Car construct() {
    return builder.setWheels(4)
                  .setColor("Red")
                  .build();
  }

  public static void main(final String[] arguments) {
    final CarBuilder builder = new CarBuilderImpl();

    final CarBuildDirector carBuildDirector = new CarBuildDirector(builder);

    System.out.println(carBuildDirector.construct());
  }
}
