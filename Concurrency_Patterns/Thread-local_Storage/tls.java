//Thread-local storage (TLS) is a computer programming method that uses static or global memory local to a thread.

//https://en.wikipedia.org/wiki/Thread-local_storage

private static final ThreadLocal<Integer> myThreadLocalInteger = new ThreadLocal<Integer>();
