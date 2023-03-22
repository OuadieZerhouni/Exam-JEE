package com.project.presentation;

import com.project.beans.Task;
import com.project.business.DefaultServices;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name = "ChangeOrdreServlet", value = "/move/*")
public class ChangeOrdreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // e.g. /up/123 or /down/456

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 3) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid path");
            return;
        }

        String direction = pathParts[1];
        int taskId = Integer.parseInt(pathParts[2]);

        Task task = DefaultServices.getInstance().getTaskById(taskId);
        if (task == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found");
            return;
        }

        if (direction.equals("up")) {
            task.setOrdre(task.getOrdre() - 1);
        } else if (direction.equals("down")) {
            task.setOrdre(task.getOrdre() + 1);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid direction");
            return;
        }

        DefaultServices.getInstance().updateTask(task);
        response.sendRedirect("../../listTask");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
