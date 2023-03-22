<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.project.beans.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
<% List<Task> tasks= (List<Task>) request.getAttribute("tasks");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exam Tasks</title>
    <link rel="stylesheet" href="CSS/main.css">
</head>
<body>
<main class="main">
    <h2>Add Tasks</h2>
    <form action="addTask" method="POSt">
        <label for="text">text</label>
        <input id="text" name="text" type="text"/>
        <button type="submit" >Add</button>
        <button type="reset" >reset</button>
    </form>


    <h1>Tasks list</h1>
    <ul>
      <% List<Task> sortedTasks = new ArrayList<>(tasks);
         Collections.sort(sortedTasks, Comparator.comparingInt(Task::getOrdre));
         for (Task task : sortedTasks) { %>
             <li>text :<%= task.getText() %> -ordre:<%= task.getOrdre() %>
                 <form method="GET" action="deleteTask">
                     <input type="hidden" name="id" value="<%= task.getId() %>">
                     <button type="submit">Delete</button>
                 </form>
                 <% if (sortedTasks.indexOf(task) != 0) { %>
                     <form method="GET" action="move/up/<%= task.getId() %>">
                         <button type="submit">Up</button>
                     </form>
                 <% } %>
                 <% if (sortedTasks.indexOf(task) != sortedTasks.size()-1) { %>
                     <form method="GET" action="move/down/<%= task.getId() %>">
                         <button type="submit">Down</button>
                     </form>
                 <% } %>
             </li>
         <% } %>
    </ul>
</main>
</body>
</html>