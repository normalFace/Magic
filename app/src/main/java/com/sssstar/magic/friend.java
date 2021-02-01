package com.sssstar.magic;

public class friend {
    private String name,speack;
    private int icon;

    public friend(){

    }

    public friend(String name,String speack,int icon){
        this.name = name;
        this.speack = speack;
        this.icon = icon;
    }

    public String getName(){
        return name;
    }

    public String getSpeack(){
        return speack;
    }

    public int getIcon(){
        return icon;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSpeack(String speack){
        this.speack=speack;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }
}
