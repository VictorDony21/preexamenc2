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
    private List<Usuario> listaUsuario;
    private Context context;

    public usuarioAdapter(Context context, List<Usuario> listaUsuario) {
        this.context = context;
        this.listaUsuario = listaUsuario;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_registro, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = listaUsuario.get(position);
        holder.bind(usuario);
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUsuario;
        private TextView textViewCorreo;
        private TextView textViewContraseña;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            // AQui se pone la carrer en el item

            textViewUsuario = itemView.findViewById(R.id.textViewUsuario);
            textViewCorreo = itemView.findViewById(R.id.textViewCorreo);
            textViewContraseña = itemView.findViewById(R.id.textViewContraseña);
        }

        public void bind(Usuario usuario) {
            textViewUsuario.setText(usuario.getUsuario());
            textViewCorreo.setText(usuario.getCorreo());
            textViewContraseña.setText(usuario.getContraseña());

        }
    }
}
