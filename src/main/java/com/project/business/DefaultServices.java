package com.project.business;

import com.project.beans.Task;
import com.project.dao.TaskDao;
import com.project.dao.TaskDaoMemory;

import java.util.List;

public class DefaultServices implements Services {
    private static DefaultServices instance;
    private TaskDao taskDao;

    private DefaultServices() {
        this.taskDao = new TaskDaoMemory();
    }

    public static synchronized DefaultServices getInstance() {
        if (instance == null) {
            instance = new DefaultServices();
        }
        return instance;
    }

    @Override
    public Task addTask(String text) {
        Task task = new Task(text);
        return taskDao.add(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDao.get();
    }

    @Override
    public Task getTaskById(int id) {
        return taskDao.getById(id);
    }

    @Override
    public Task updateTask(Task task) {
        return taskDao.update(task);
    }

    @Override
    public Task deleteTask(int id) {
        return taskDao.remove(id);
    }
}