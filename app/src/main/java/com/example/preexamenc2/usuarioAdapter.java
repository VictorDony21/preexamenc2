package com.example.preexamenc2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class usuarioAdapter extends RecyclerView.Adapter<usuarioAdapter.UsuarioViewHolder> {
    private List<Usuario> listaAlumnos;
    private Context context;

    public usuarioAdapter(Context context, List<Usuario> listaAlumnos) {
        this.context = context;
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_registro, parent, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        Alumno alumno = listaAlumnos.get(position);
        holder.bind(alumno);
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCarrera;
        private TextView textViewNombre;
        private TextView textViewMatricula;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            // AQui se pone la carrer en el item

            textViewCarrera = itemView.findViewById(R.id.textViewCarrera);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewMatricula = itemView.findViewById(R.id.textViewMatricula);
        }

    }
}
