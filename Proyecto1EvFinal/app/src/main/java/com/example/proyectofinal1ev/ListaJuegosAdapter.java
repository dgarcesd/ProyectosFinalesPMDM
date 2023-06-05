package com.example.proyectofinal1ev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.proyectofinal1ev.model.Juego;



import java.util.ArrayList;

//3 Creamos el adaptadaor e implementamos los pasos
public class ListaJuegosAdapter extends RecyclerView.Adapter<ListaJuegosAdapter.ViewHolder> {
    //5
    private ArrayList<Juego> dataset;
    private Context context;
    //9
    public ListaJuegosAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }



    @NonNull
    @Override
    public ListaJuegosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //8
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juego,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaJuegosAdapter.ViewHolder holder, int position) {
        // 7
        Juego j = dataset.get(position);
        holder.nombreJuego.setText(j.getNombre());
        // 19 cargamos la imagen con Glide
//        Glide.with(context)
//                .load("https://63cd65a1d4d47898e39822fe.mockapi.io/" + j.getUrl() + ".png")
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.imagenJuego);
        holder.nombreDesarrollador.setText(j.getDesarrollador());
        holder.anyoJuego.setText(j.getAnyo());
        holder.valoracionJuego.setRating(j.getValoracion());

    }

    @Override
    public int getItemCount() {
        //6
        return dataset.size();
    }
    //15 Creamos el metodo en el adaptador
    // Permitimos que el Arraylist que llega lo junte con el arraylist del adaptador
    public void adicionarJuego(ArrayList<Juego> listaJuegos) {
        dataset.addAll(listaJuegos);
        // 16 con este metodo actualizaremos el recyclerView en la pantalla
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //4a
        private ImageView imagenJuego;
        private TextView nombreJuego;
        private TextView nombreDesarrollador;
        private TextView anyoJuego;
        private RatingBar valoracionJuego;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //4b
            imagenJuego = itemView.findViewById(R.id.imagenJuego);
            nombreJuego = itemView.findViewById(R.id.nombreJuego);
            nombreDesarrollador = itemView.findViewById(R.id.nombreDesarrollador);
            anyoJuego = itemView.findViewById(R.id.anyoJuego);
            valoracionJuego = itemView.findViewById(R.id.valoracionJuego);


        }
    }
}













