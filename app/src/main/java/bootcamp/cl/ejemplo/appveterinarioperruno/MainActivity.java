package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// A una pantalla o vista del programa se le conoce como Activity
//Por cada activity siempre hay un archivo layout

/*Pueba de Git,cambio en el codigo*/
public class MainActivity extends AppCompatActivity {


    /*
    Variables para lógica
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        Traigo la vista a la actividad

         */
            /*
       Declaración del objetos de la clase View
      Sirven para comunicarse con la interfaz de android
       */

        Button irActividad = findViewById(R.id.bontonNavegar);

        //edad_animal = campoEdadPerro.getText().toString();

        Log.d("MainActivity","La activity se ha creado");

        /*
        Funcionalidad de la app que lleva a la ficha del perro cuando presiona el Botón
         */
        irActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActividadFichaAnimal();
            }
        });

    }

    private void abrirActividadFichaAnimal(){
        
            Intent intentoFicha = new Intent(this, FichaPerroActivity.class);
            startActivity(intentoFicha);
    }


    public void abrirListaAnimales(View view) {

        Intent intent = new Intent(this, ListadoFichasActivity.class);
        startActivity(intent);

    }

}