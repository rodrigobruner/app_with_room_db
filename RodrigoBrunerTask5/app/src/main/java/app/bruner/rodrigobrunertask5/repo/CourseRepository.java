package app.bruner.rodrigobrunertask5.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.bruner.rodrigobrunertask5.data.CourseDao;
import app.bruner.rodrigobrunertask5.data.CourseDatabase;
import app.bruner.rodrigobrunertask5.data.CourseModel;

public class CourseRepository {
    // Dao instance
    private CourseDao courseDao;
    // Number of threads in the pool
    private static final int NUMBER_OF_THREADS = 2;
    // Instance to execute the queries in background
    private final ExecutorService dbExecutor;

    // Constructor
    public CourseRepository(Application application) {
        CourseDatabase database = CourseDatabase.getInstance(application);
        // Set dao
        courseDao = database.courseDao();

        // Set executor service
        dbExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    // Function to insert course
    public void insert(CourseModel model) {
        // Run in background the insert command
        dbExecutor.execute(() -> courseDao.insert(model));
    }

    // Function to get the last inserted course
    public CourseModel getLast() {
        return courseDao.getLast();
    }
}