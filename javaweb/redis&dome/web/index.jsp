<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>province</title>
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function(){
          $.get("findAllServlet", function(data){
              $(data).each(function(index, provice){
                  var option = "<option value='"+ this.id +"'>"+ this.name +"</option>";
                  $("#province").append(option);
              })
          }, "json");
      });
    </script>
  </head>
  <body>
    <select id="province">
      <option value="-1">--请选择--</option>
    </select>
  </body>
</html>
