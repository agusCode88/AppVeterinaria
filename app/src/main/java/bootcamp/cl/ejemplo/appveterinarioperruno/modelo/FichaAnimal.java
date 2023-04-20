package bootcamp.cl.ejemplo.appveterinarioperruno.modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Animal.class,
        parentColumns = "idAnimal",
        childColumns = "animalId",
        onDelete = ForeignKey.CASCADE))
public class FichaAnimal {

    @PrimaryKey(autoGenerate = true)
    private long idFicha;
    private String descripcionConsulta;
    private float nivelGravedad;
    private long animalId;

    public FichaAnimal(long idFicha, String descripcionConsulta, float nivelGravedad, long animalId) {
        this.idFicha = idFicha;
        this.descripcionConsulta = descripcionConsulta;
        this.nivelGravedad = nivelGravedad;
        this.animalId = animalId;
    }

    public FichaAnimal() {
    }

    public long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(long idFicha) {
        this.idFicha = idFicha;
    }

    public String getDescripcionConsulta() {
        return descripcionConsulta;
    }

    public void setDescripcionConsulta(String descripcionConsulta) {
        this.descripcionConsulta = descripcionConsulta;
    }

    public float getNivelGravedad() {
        return nivelGravedad;
    }

    public void setNivelGravedad(float nivelGravedad) {
        this.nivelGravedad = nivelGravedad;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }
}
