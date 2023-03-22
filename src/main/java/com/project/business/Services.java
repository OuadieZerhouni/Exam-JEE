package com.project.business;

import com.project.beans.Task;

import java.util.List;

public interface Services {
    Task addTask(String text);
    List<Task> getAllTasks();
    Task getTaskById(int id);
    Task updateTask(Task task);
    Task deleteTask(int id);
}