package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Animal;

@Dao
public interface AnimalDAO {

    @Insert
    long insertarAnimal(Animal animal);

    @Update
    void actualizarAnimal(Animal animal);

    @Delete
    void eliminarAnimal(Animal animal);

    @Query("SELECT * FROM Animal")
    List<Animal> obtenerAnimales();


}
