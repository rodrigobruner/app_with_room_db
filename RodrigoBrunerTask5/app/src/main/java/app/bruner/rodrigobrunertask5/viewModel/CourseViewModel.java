package app.bruner.rodrigobrunertask5.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.bruner.rodrigobrunertask5.data.CourseModel;
import app.bruner.rodrigobrunertask5.repo.CourseRepository;

public class CourseViewModel extends AndroidViewModel {

    // Course repository instance
    private CourseRepository repository;

    // All the courses are present
    private LiveData<List<CourseModel>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);

        repository = new CourseRepository(application);
        // Initialize allCourses if needed
    }

    // Method to insert the data to our repository
    public void insert(CourseModel model) {
        repository.insert(model);
    }

    // Method to get the last inserted course
    public CourseModel getLast() {
        return repository.getLast();
    }
}