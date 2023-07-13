package sg.edu.rp.c346.songl08;

import java.io.Serializable;

public class Song implements Serializable {
    private int year;
    private int id;
    private String title;
    private String singers;

    private int stars;
    public Song(int id,String title, String singers, int year, int stars){
        this.id=id;
        this.title=title;
        this.year=year;
        this.stars=stars;
        this.singers=singers;
    }



    public int getYear(){
        return year;
    }public int getStar(){
        return stars;
    }
    public String getTitle(){
        return title;
    }
    public String getSingers(){
        return singers;
    }
    public int getStars(){return stars;}
    public  String toString(){
        String output = String.format("%-18s %-15s %-7d %d",getTitle(),getSingers(),getYear(),getStars());
        return output;
    }
    public int getId(){
        return id;
    }

    public void setTitle(String x){
        title=x;
    }
    public void setYear(int x ){
        year=x;
    }
    public void setSingers(String singer){
        singers=singer;
    }
    public void setStars(int x){
        stars=x;
    }




}
