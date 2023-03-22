package com.project.presentation;

import java.io.IOException;

import com.project.business.DefaultServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddTaskServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/addTodo.jsp").forward(request, response);
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String text = request.getParameter("text");
   
       if (text == null || text.trim().isEmpty()) {
           response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Text parameter is missing or empty.");
           return;
       }
   
       try {
           DefaultServices.getInstance().addTask(text);
           response.sendRedirect("listTasks");
       } catch (Exception e) {
           response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add task: " + e.getMessage());
       }
   }
}   