package bootcamp.cl.ejemplo.appveterinarioperruno;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import java.io.File;
import android.Manifest;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AnimalDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaAnimalDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityFichaPerroBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Animal;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaAnimal;

public class FichaPerroActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSION_CAMERA = 2;

    private Bitmap imageBitmap;

    private String nombreAnimal;
    private String mCurrentPhotoPath;
    private String edadAnimalRecibida;
    private String pesoAnimal;
    private boolean sexoAnimal;
    private String descripConsulta;
    private float valoracionGravedad;
    private ActivityFichaPerroBinding binding;

    private Animal animal;

    private FichaAnimal fichaAnimal;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_ficha_perro);
        context = getApplicationContext();
        binding = ActivityFichaPerroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        seteoBotones();

    }

    public void seteoBotones(){

        binding.ImagenAvatarPerro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tomarFoto();
                //Aca llamo a la cámra
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.radioMacho) {

                     sexoAnimal = false;
                    // El usuario seleccionó "macho"
                    // Aquí puedes hacer lo que necesites con este valor

                } else if (i == R.id.radioHembra) {

                     sexoAnimal = true;

                    // El usuario seleccionó "hembra"
                    // Aquí puedes hacer lo que necesites con este valor
                }
            }
        });

        binding.botonTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llenarRegistroAnimal();

                //Log.d("MainActivity", "CLICK");
            }
        });

    }

    public void llenarRegistroAnimal(){

        AppDataBase db = AppDataBase.getDatabase(context);
        AnimalDAO animalDAO = db.animalDao();
        FichaAnimalDAO fichaDao = db.fichaAtencionDao();

        nombreAnimal = binding.campoNombre.getText().toString();
        edadAnimalRecibida = binding.campoEdad.getText().toString();
        pesoAnimal = binding.campoPeso.getText().toString();

        descripConsulta = binding.descripcionDiagnostico.getText().toString();
        valoracionGravedad = binding.nivelGravedad.getRating();

        Animal animal = new Animal();
        animal.setRutaImagen(mCurrentPhotoPath);
        animal.setNombreAnimal(nombreAnimal);
        animal.setEdadAnimal(edadAnimalRecibida);
        animal.setPesoAnimal(pesoAnimal);


       // animalDAO.insertarAnimal(animal);

        FichaAnimal fichaAnimal = new FichaAnimal();
        fichaAnimal.setDescripcionConsulta(descripConsulta);
        fichaAnimal.setNivelGravedad(valoracionGravedad);

        //fichaDao.insertarFichaAtencion(fichaAnimal);


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                // Insertar el animal y su ficha en la base de datos
                long idAnimal = animalDAO.insertarAnimal(animal); // Insertar el animal en la tabla
                fichaAnimal.setAnimalId(idAnimal); // Asignar el ID generado al objeto ficha
                long idFicha = fichaDao.insertarFichaAtencion(fichaAnimal); // Insertar la ficha en la tabla

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Ficha del paciente Lista", Toast.LENGTH_SHORT).show();
                        //Intent intentActividadVerFicha = new Intent(context, VerFichaActivity.class);
                        //intentActividadVerFicha.putExtra("perroAtendido",fichaAnimal);
                        //startActivity(intentActividadVerFicha);
                    }
                });

            }

        });

    }
    //Método para lanzar la cámara
    public void tomarFoto() {
        // Solicitar permiso para usar la cámara si no está otorgado
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    REQUEST_PERMISSION_CAMERA);
            return;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Verificar si hay una aplicación de cámara instalada en el dispositivo
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Crear el archivo donde se almacenará la imagen
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error al crear el archivo
                ex.printStackTrace();
            }
            // Continuar solo si el archivo fue creado correctamente
            if (photoFile != null) {
                // Obtener la URI del archivo creado
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                // Pasar la URI al intent de la cámara
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                // Iniciar la actividad de la cámara
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Crear un nombre único para la imagen basado en la fecha y hora actuales
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // Obtener el directorio donde se almacenarán las imágenes
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // Crear el archivo
        File image = File.createTempFile(
                imageFileName,  /* prefijo */
                ".jpg",         /* sufijo */
                storageDir      /* directorio */
        );
        // Guardar la ruta absoluta del archivo creado
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Verificar que la solicitud es para tomar una foto y que fue exitosa
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Mostrar la imagen en el ImageView

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            binding.ImagenAvatarPerro.setImageBitmap(bitmap);
            // Guardar la imagen en la galería del dispositivo
            //galleryAddPic();
        }
    }


}
