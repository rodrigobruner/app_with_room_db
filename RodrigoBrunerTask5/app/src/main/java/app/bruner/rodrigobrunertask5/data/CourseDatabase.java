package app.bruner.rodrigobrunertask5.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// This annotation defines a Room database with CourseModel in version 1
@Database(entities = {CourseModel.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {
    // A singleton instance of the database
    private static volatile CourseDatabase instance;
    // Number of threads for the service
    private static final int NUMBER_OF_THREADS = 4;
    // Create an Executor Service to run the db activities in another thread (background), not in the main thread
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    // Get DAO
    public abstract CourseDao courseDao();

    // Get the singleton instance
    public static CourseDatabase getInstance(final Context context) {
        if (instance == null) { // If no instance, create one
            synchronized (CourseDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    CourseDatabase.class, "course_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback) // Callback to populate DB when it is created
                            .build();
                }
            }
        }
        return instance;
    }

    // Callback to populate DB when it is created
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Execute db operations in background
            databaseWriteExecutor.execute(() -> {
                CourseDao dao = instance.courseDao();
                // You can add initial data here if needed
            });
        }
    };
}