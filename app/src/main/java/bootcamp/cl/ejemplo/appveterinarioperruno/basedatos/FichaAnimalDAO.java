package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaAnimal;

@Dao
public interface FichaAnimalDAO {

    @Insert
    long insertarFichaAtencion(FichaAnimal ficha);

    @Update
    void actualizarFichaAtencion(FichaAnimal ficha);

    @Delete
    void eliminarFichaAtencion(FichaAnimal ficha);

    @Query("SELECT * FROM FichaAnimal WHERE animalId = :animalId")
    public List<FichaAnimal> obtenerFichasAtencion(int animalId);

}
