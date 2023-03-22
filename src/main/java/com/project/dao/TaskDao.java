package com.project.dao;

import com.project.beans.Task;

import java.util.List;

public interface TaskDao {
    public Task add(Task task);
    public List<Task> get();
    public Task getById(int id);
    public Task remove(int id);
    public Task update(Task task);
}
