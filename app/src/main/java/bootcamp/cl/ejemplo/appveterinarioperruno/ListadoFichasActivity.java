package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores.AnimalAdapter;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AnimalDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaAnimalDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityFichaPerroBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityListadoFichasBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Animal;

public class ListadoFichasActivity extends AppCompatActivity {

    private Context context;
    ActivityListadoFichasBinding binding;

    AnimalAdapter adaptarAnimales;

    List<Animal> listaAnimales = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        binding = ActivityListadoFichasBinding.inflate(getLayoutInflater());
        AppDataBase db = AppDataBase.getDatabase(context);
        setContentView(binding.getRoot());
        binding.listadoFichas.setLayoutManager(new LinearLayoutManager(this));

        /*
        Las consultas a las bases de datos se tiene que hacer en un hilo
        secundario , si no la app se cae porque toma el hilo principal
        La lógica que uds hagan debe ir dentro del run()
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                AnimalDAO animalDAO = db.animalDao();
                FichaAnimalDAO fichaDao = db.fichaAtencionDao();

                // Se obtine un listado de los registros de la base de datos gracias al DAO
                listaAnimales = animalDAO.obtenerAnimales();

                runOnUiThread(new Runnable() {
                    public void run() {

                        // Se crea el objeto adaptador
                        adaptarAnimales = new AnimalAdapter(listaAnimales,context);
                        adaptarAnimales.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Animal animal) {

                                Log.d("ListadoActivity","Click a un animal");

                            }
                        });
                        //Al reciclerView le paso el adaptador lleno de objetos
                        binding.listadoFichas.setAdapter(adaptarAnimales);
                    }
                });

            }

        });

    }
}