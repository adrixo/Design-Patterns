//The front controller software design pattern is listed in several pattern catalogs and related to the design of web applications. It is "a controller that handles all requests for a website",[1] which is a useful structure for web application developers to achieve the flexibility and reuse without code redundancy

private void doProcess(HttpServletRequest request,
                       HttpServletResponse response)
   throws IOException, ServletException {
    ...
   try {
      getRequestProcessor().processRequest(request);
      getScreenFlowManager().forwardToNextScreen(request, response);
    } catch (Throwable ex) {
      String className = ex.getClass().getName();
      nextScreen = getScreenFlowManager().getExceptionScreen(ex);
      // put the exception in the request
      request.setAttribute("javax.servlet.jsp.jspException", ex);
      if (nextScreen == null) {
         // send to general error screen
         ex.printStackTrace();
         throw new ServletException("MainServlet: unknown exception: " +
            className);
      }
   }
}
