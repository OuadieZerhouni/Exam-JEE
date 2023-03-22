package com.project.dao;

import com.project.beans.Task;
import java.util.List;
import java.util.Vector;

public class TaskDaoMemory implements TaskDao {
    private List<Task> tasks;
    private int LastId;

    public TaskDaoMemory(){
        tasks=new Vector<Task>();
    }

    @Override
  public Task add(Task task) {
      LastId++;
      task.setId(LastId);
      task.setOrdre(tasks.size());
      tasks.add(task);
      return task;
  }

    @Override
    public List<Task> get() {
        return tasks;
    }

    @Override
    public Task getById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
  public Task remove(int id) {
      Task removedTask = null;
      int removedIndex = -1;
      for (int i = 0; i < tasks.size(); i++) {
          if (tasks.get(i).getId() == id) {
              removedTask = tasks.remove(i);
              removedIndex = i;
              break;
          }
      }
  
      if (removedTask != null) {
          for (int i = removedIndex; i < tasks.size(); i++) {
              Task t = tasks.get(i);
              t.setOrdre(i);
          }
      }
      return removedTask;
  }

@Override
public Task update(Task task) {
    int oldIndex = -1;
    for (int i = 0; i < tasks.size(); i++) {
        if (tasks.get(i).getId() == task.getId()) {
            oldIndex = i;
            break;
        }
    }

    if (oldIndex != -1) {
        Task oldTask = tasks.get(oldIndex);
        int oldOrdre = oldTask.getOrdre();
        tasks.remove(oldIndex);

        int newIndex = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getOrdre() < task.getOrdre()) {
                newIndex++;
            } else {
                break;
            }
        }
        if(newIndex==oldIndex){
            newIndex++;
        }

        task.setOrdre(newIndex);
        tasks.add(newIndex, task);
        System.out.println("here"+newIndex+"-"+oldIndex);
        if (newIndex < oldIndex) {
            for (int i = newIndex; i < oldIndex; i++) {
                Task t = tasks.get(i);
                if (t != task) {
                    t.setOrdre(t.getOrdre() + 1);
                }
            }
        } else if (newIndex > oldIndex) {

            for (int i = oldIndex; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t != task) {
                    t.setOrdre(t.getOrdre() + 1);
                }
            }
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            t.setOrdre(i);
        }

        return task;
    }

    return null;
}
}