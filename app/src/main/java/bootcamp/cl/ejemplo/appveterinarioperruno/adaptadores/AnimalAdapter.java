package bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.R;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityListadoFichasBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.FichaItemBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Animal;

/*
Se le pasa por paremetro el viewolder que queremos pintar
AnimalAdapter.ViewHolder,esto es herencia  la clase ViewHolder esta creada acá mismo al finla del código
 */
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private List<Animal> mData;
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public  AnimalAdapter(List<Animal> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /*
    Este metodo se llama x cantidad de veces , dependiendo la cantidad de objetos que le lleguen
    si tengo 5 animales , se creará 5 veces automáticamente.
    Se le envía al ViewHolder la vista xml

     */
    @Override
    public AnimalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FichaItemBinding binding = FichaItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        //View view = mInflater.inflate(R.layout.ficha_item, null);
        return new AnimalAdapter.ViewHolder(binding);
    }



   /*
   Cada obeto que se pintará en la lista tiene una posición , este método detecta la posicion
   del objeto dentro de la lista
    */
    @Override
    public void onBindViewHolder(final AnimalAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    // Se crea la interfaz en Java para que esta sea llamada desde cualquier otro lugar
    public interface OnItemClickListener{
        void onItemClick(Animal animal);
    }

    // Setro del listener como cualquier otro atributo
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

   /*
   Esta es la clase ViewHolder que le pasasamos al adaptador
   Básicamente lo que hace un ViewHolder es recibir el layout del item (la fila de la lista)
   y le indica que elementos(views) quiero pintar
    */
    public class ViewHolder extends RecyclerView.ViewHolder {

        FichaItemBinding binding;

       /*
        Constructor del VieqHolder
        recibe como parametro el layot (la vista) lo
        recibe como parametro en forma de binding
         */
        ViewHolder(FichaItemBinding binding) {
            super(binding.getRoot());

               this.binding = binding;
        }

       /**
        * Puente que sirve para pasarle el objeto animal
        * y seterales los datos a las vistas , es como simular un binding
        * @param item
        */
        public void bindData(final Animal item) {
            //Drawable d = context.getDrawable(item.getImage());
            binding.nombreAnimalItem.setText(item.getNombreAnimal());
            binding.pesoAnimalItem.setText(item.getPesoAnimal());
            binding.edadAnimalItem.setText(item.getEdadAnimal());

            binding.getRoot().setOnClickListener(view -> {

                onItemClickListener.onItemClick(item);
            });
            
            binding.executePendingBindings();
        }
    }

}
