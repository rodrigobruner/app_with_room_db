package app.bruner.rodrigobrunertask5.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class CourseModel {

    //ID is auto generate by db
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String name;
    private int duration;
    private double fees;

    public CourseModel(String code, String name, int duration, double fees) {
        this.code = code;
        this.name = name;
        this.duration = duration;
        this.fees = fees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
