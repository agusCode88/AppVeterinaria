package bootcamp.cl.ejemplo.appveterinarioperruno.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animal {

    @PrimaryKey(autoGenerate = true)
    private long idAnimal;
    private String rutaImagen;
    private String nombreAnimal;
    private String pesoAnimal;
    private String edadAnimal;
    private boolean sexoAnimal;

    public Animal(long idAnimal, String rutaImagen, String nombreAnimal, String pesoAnimal, String edadAnimal, boolean sexoAnimal) {
        this.idAnimal = idAnimal;
        this.rutaImagen = rutaImagen;
        this.nombreAnimal = nombreAnimal;
        this.pesoAnimal = pesoAnimal;
        this.edadAnimal = edadAnimal;
        this.sexoAnimal = sexoAnimal;
    }

    public Animal() {
    }

    public long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public void setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }

    public String getPesoAnimal() {
        return pesoAnimal;
    }

    public void setPesoAnimal(String pesoAnimal) {
        this.pesoAnimal = pesoAnimal;
    }

    public String getEdadAnimal() {
        return edadAnimal;
    }

    public void setEdadAnimal(String edadAnimal) {
        this.edadAnimal = edadAnimal;
    }

    public boolean isSexoAnimal() {
        return sexoAnimal;
    }

    public void setSexoAnimal(boolean sexoAnimal) {
        this.sexoAnimal = sexoAnimal;
    }
}

