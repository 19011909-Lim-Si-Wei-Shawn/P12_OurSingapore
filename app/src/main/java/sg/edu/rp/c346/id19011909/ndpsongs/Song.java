package sg.edu.rp.c346.id19011909.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {

    //Creating Variable,
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    //Constructor,
    public Song(String title, String singers, int year, int stars)
    {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public Song(int id, String title, String singers, int year, int stars)
    {
        this._id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    //Creating Functions,
    public int get_id() {   return _id;     }

    public String getTitle() {  return title;       }

    public void setTitle(String x) {    this.title = x;     }

    public String getSingers() {    return singers;     }

    public void setSingers(String x) {  this.singers = x;       }

    public int getYear() {  return year;        }

    public void setYear(int i)  {   this.year = i;      }

    public int getStars() { return stars;       }

    public void setStars(int i) {   this.stars = i;     }

    @Override
    public String toString()
    {
        String x = "";

        for(int i = 0; i < stars; i++)
        {
            x += "* ";
        }

        return x;
    }


}
