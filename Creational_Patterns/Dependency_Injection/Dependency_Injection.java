//In software engineering, dependency injection is a technique whereby one object (or static method) supplies the dependencies of another object. A dependency is an object that can be used (a service). An injection is the passing of a dependency to a dependent object (a client) that would use it.

//initialized by the Client constructor. The client controls which implementation of service is used and controls its construction. In this situation, the client is said to have a hard-coded dependency on ExampleService

// An example without dependency injection
public class Client {
  // Internal reference to the service used by this client
  private ExampleService service;

  // Constructor
  Client() {
    // Specify a specific implementation in the constructor instead of using dependency injection
    service = new ExampleService();
  }

  // Method within this client that uses the services
  public String greet() {
    return "Hello " + service.getName();
  }
}
