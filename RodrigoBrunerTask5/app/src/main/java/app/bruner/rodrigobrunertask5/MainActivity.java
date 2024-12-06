package app.bruner.rodrigobrunertask5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.bruner.rodrigobrunertask5.data.CourseModel;
import app.bruner.rodrigobrunertask5.viewModel.CourseViewModel;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    EditText editTextCode, editTextName, editTextDuration, editTextFees;
    Button buttonSubmit, buttonDisplay;
    private CourseViewModel courseViewModel;

    //I need create a new executor to run the select
    //Create a executor and mainHandler for run query in background
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Get widgets
        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextDuration = findViewById(R.id.editTextDuration);
        editTextFees = findViewById(R.id.editTextFees);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonDisplay = findViewById(R.id.buttonDisplay);


        // create new ViewModel
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // set on click event for buttonSubmit
        buttonSubmit.setOnClickListener(v -> {
            try {
                // Create a new CourseModel instance
                CourseModel course = new CourseModel(
                        editTextCode.getText().toString(),
                        editTextName.getText().toString(),
                        Integer.parseInt(editTextDuration.getText().toString()),
                        Double.parseDouble(editTextFees.getText().toString().replace(",", "."))
                );

                // Insert the course into the database
                courseViewModel.insert(course);

                // Show a confirmation message
                Toast.makeText(MainActivity.this, getString(R.string.form_save), Toast.LENGTH_LONG).show();

                // Clear the input fields
                clearForm();
            } catch (Exception e) {
                // Show an error message
                Toast.makeText(MainActivity.this, getString(R.string.form_error), Toast.LENGTH_LONG).show();
                Log.i(TAG, e.getMessage());
            }
        });


        // Set OnClickListener for buttonDisplay
        buttonDisplay.setOnClickListener(v -> {
            executor.submit(() -> { // Execute in a background thread
                CourseModel course = courseViewModel.getLast(); // Get last register
                // Back to main thread
                mainHandler.post(() -> {
                    if (course != null) { // If have a course
                        new AlertDialog.Builder(MainActivity.this) // New alert dialog
                                .setTitle(getString(R.string.show_title)) // Set title
                                .setMessage(                              // Set the message
                                        getString(R.string.form_code) + ": " + course.getCode() + "\n" +
                                                getString(R.string.form_name) + ": " + course.getName() + "\n" +
                                                getString(R.string.form_duration) + ": " + course.getDuration() + "\n" +
                                                getString(R.string.form_fees) + ": " + String.format("%.2f",course.getFees())) //Format double
                                .setPositiveButton(getString(R.string.show_button_ok), null) // Add an OK button
                                .show(); // Show dialog
                    } else {
                        // Show a message that course not found
                        Toast.makeText(MainActivity.this, "No course found", Toast.LENGTH_LONG).show();
                    }
                });
            });
        });
    }

    //Function to reset the form
    private void clearForm(){
        editTextCode.setText("");
        editTextName.setText("");
        editTextDuration.setText("");
        editTextFees.setText("");
    }
}