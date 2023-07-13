package sg.edu.rp.c346.songl08;

public class Song {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;
    public Song(String title, String singers, int year, int stars){
        this.title=title;
        this.year=year;
        this.stars=stars;
        this.singers=singers;
    }
    public int get_id(){
        return _id;
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




}
