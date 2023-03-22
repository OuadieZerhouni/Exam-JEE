package com.project.beans;

import java.util.Random;

public class Task {
    private int ordre;
    private int id;
    private String text;

    public Task(String text){
        this.text=text;
    }

    public String getText(){
        return this.text;

    }
    public void setText(String text){
        if(!text.isEmpty()){
            this.text=text;
        }
    }
    public int getOrdre(){
        return this.ordre;
    }
    public  void setOrdre(int ordre){
        this.ordre=ordre;
    }
    public int getId(){
        return this.id;
    }
    public void  setId(int id){
        this.id=id;
    }

}