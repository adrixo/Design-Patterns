//In object-oriented programming, the decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class. The decorator pattern is often useful for adhering to the Single Responsibility Principle, as it allows functionality to be divided between classes with unique areas of concern. The decorator pattern is structurally nearly identical to the chain of responsibility pattern, the difference being that in a chain of responsibility, exactly one of the classes handles the request, while for the decorator, all classes handle the request.

//1. The following Java example illustrates the use of decorators using the window/scrolling scenario.
// The Window interface class
public interface Window {
  void draw(); // Draws the Window
  String getDescription(); // Returns a description of the Window
}

// Implementation of a simple Window without any scrollbars
class SimpleWindow implements Window {
  @Override
  public void draw() {
    // Draw window
  }
  @Override
  public String getDescription() {
    return "simple window";
  }
}

// 2.The following classes contain the decorators for all Window classes, including the decorator classes themselves.
// abstract decorator class - note that it implements Window
abstract class WindowDecorator implements Window {
  protected Window windowToBeDecorated; // the Window being decorated

  public WindowDecorator (Window windowToBeDecorated) {
    this.windowToBeDecorated = windowToBeDecorated;
  }
  @Override
  public void draw() {
    windowToBeDecorated.draw(); //Delegation
  }
  @Override
  public String getDescription() {
    return windowToBeDecorated.getDescription(); //Delegation
  }
}

// The first concrete decorator which adds vertical scrollbar functionality
class VerticalScrollBarDecorator extends WindowDecorator {
  public VerticalScrollBarDecorator (Window windowToBeDecorated) {
    super(windowToBeDecorated);
  }

  @Override
  public void draw() {
    super.draw();
    drawVerticalScrollBar();
  }

  private void drawVerticalScrollBar() {
    // Draw the vertical scrollbar
  }

  @Override
  public String getDescription() {
    return super.getDescription() + ", including vertical scrollbars";
  }
}

// The second concrete decorator which adds horizontal scrollbar functionality
class HorizontalScrollBarDecorator extends WindowDecorator {
  public HorizontalScrollBarDecorator (Window windowToBeDecorated) {
    super(windowToBeDecorated);
  }

  @Override
  public void draw() {
    super.draw();
    drawHorizontalScrollBar();
  }

  private void drawHorizontalScrollBar() {
    // Draw the horizontal scrollbar
  }

  @Override
  public String getDescription() {
    return super.getDescription() + ", including horizontal scrollbars";
  }
}


/**
Here's a test program that creates a Window instance which is fully decorated (i.e., with vertical and horizontal scrollbars), and prints its description:

public class DecoratedWindowTest {
    public static void main(String[] args) {
        // Create a decorated Window with horizontal and vertical scrollbars
        Window decoratedWindow = new HorizontalScrollBarDecorator (
                new VerticalScrollBarDecorator (new SimpleWindow()));

        // Print the Window's description
        System.out.println(decoratedWindow.getDescription());
    }
}
Below is the JUnit test class for the Test Driven Development

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WindowDecoratorTest {
	@Test
	public void testWindowDecoratorTest() {
	    Window decoratedWindow = new HorizontalScrollBarDecorator(new VerticalScrollBarDecorator(new SimpleWindow()));
      	    // assert that the description indeed includes horizontal + vertical scrollbars
            assertEquals("simple window, including vertical scrollbars, including horizontal scrollbars", decoratedWindow.getDescription())
	}
}

**/
