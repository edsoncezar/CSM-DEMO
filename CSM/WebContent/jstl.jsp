<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
  <head>
    <title>Count to 10 Example (using JSTL)</title>
  </head>

  <body>
    <c:forEach var="i" begin="1" end="10" step="1">
   		 <c:out value="${i}" />

      <br />
    </c:forEach>
  </body>
</html>