package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Animal;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaAnimal;

@Database(entities = {Animal.class, FichaAnimal.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {

    public abstract AnimalDAO animalDao();
    public abstract FichaAnimalDAO fichaAtencionDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "bd_veterinaria")
                            .addMigrations(new Migration1To2())
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
