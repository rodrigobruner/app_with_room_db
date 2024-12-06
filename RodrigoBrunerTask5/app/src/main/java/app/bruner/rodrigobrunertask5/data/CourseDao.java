package app.bruner.rodrigobrunertask5.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

// Define DAO interface
@Dao
public interface CourseDao {

    // Add course to database
    @Insert
    void insert(CourseModel course);

    // Select the last inserted course
    @Query("SELECT * FROM courses ORDER BY id DESC LIMIT 1")
    CourseModel getLast();
}