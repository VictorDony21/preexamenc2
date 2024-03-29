package com.example.preexamenc2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class mostrarRegistros extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private DatabaseManager db;
    private static final int REQUEST_EDIT_USUARIO = 1;

    private RecyclerView recyclerViewUsuarios;
    private usuarioAdapter usuarioAdapter;
    private List<Usuario> listaUsuarios;
    private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mostrar_registro);

        db = new DatabaseManager(this);
        recyclerViewUsuarios = findViewById(R.id.recyclerViewUsuarios);

        listaUsuarios = new ArrayList<>();
        usuarioAdapter = new usuarioAdapter(this, listaUsuarios);
        recyclerViewUsuarios.setAdapter(usuarioAdapter);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        mostrarUsuarios();

        recyclerViewUsuarios.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerViewUsuarios,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Usuario selectedUsuario = listaUsuarios.get(position);

                                Intent intent = new Intent(mostrarRegistros.this, registroActivity.class);
                                intent.putExtra("ALUMNO_ID", selectedUsuario.getId());
                                intent.putExtra("ALUMNO_USUARIO", selectedUsuario.getUsuario());
                                intent.putExtra("ALUMNO_CORREO", selectedUsuario.getCorreo());
                                intent.putExtra("ALUMNO_CONTRASEÑA", selectedUsuario.getContraseña());
                                startActivityForResult(intent, REQUEST_EDIT_USUARIO);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        })
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Usuario> listaFiltrada = db.buscarUsuarios(newText);
        listaUsuarios.clear();

        // Filtrar por usuario y correo
        for (Usuario usuario : listaFiltrada) {
            if (usuario.getUsuario().toLowerCase().contains(newText.toLowerCase()) ||
                    usuario.getCorreo().toLowerCase().contains(newText.toLowerCase()) ||
                    usuario.getContraseña().toLowerCase().contains(newText.toLowerCase()))  {
                listaUsuarios.add(usuario);
            }
        }

        usuarioAdapter.notifyDataSetChanged();
        return true;
    }

    private void mostrarUsuarios() {
        listaUsuarios.clear();
        listaUsuarios.addAll(db.obtenerUsuarios());
        usuarioAdapter.notifyDataSetChanged();
    }


    private static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private GestureDetectorCompat gestureDetector;
        private OnItemClickListener mListener;

        interface OnItemClickListener {
            void onItemClick(View view, int position);
            void onLongItemClick(View view, int position);
        }

        RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            gestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null && mListener != null) {
                        mListener.onLongItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                    }
                }
            });

            recyclerView.addOnItemTouchListener(this);
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && gestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    public void regresar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas regresar al menú principal?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Regresar a la ventana anterior
                onBackPressed();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
