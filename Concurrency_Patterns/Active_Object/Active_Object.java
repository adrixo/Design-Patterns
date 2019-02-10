//The active object design pattern decouples method execution from method invocation for objects that each reside in their own thread of control. The goal is to introduce concurrency, by using asynchronous method invocation and a scheduler for handling requests.

class MyClass {

    private double val = 0.0;

    void doSomething() {
        val = 1.0;
    }

    void doSomethingElse() {
        val = 2.0;
    }
}

class MyActiveObject {

    private double val = 0.0;

    private BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<Runnable>();

    public MyActiveObject() {
        new Thread (new Runnable() {

                @Override
                public void run() {
                    while(true) {
                        try {
                            dispatchQueue.take().run();
                        } catch (InterruptedException e) {
                            // okay, just terminate the dispatcher
                        }
                    }
                }
            }
        ).start();
    }

    void doSomething() throws InterruptedException {
        dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    val = 1.0;
                }
            }
        );
    }

    void doSomethingElse() throws InterruptedException {
        dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    val = 2.0;
                }
            }
        );
    }
}
