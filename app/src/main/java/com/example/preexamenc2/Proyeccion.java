package com.example.preexamenc2;

import android.database.Cursor;

import java.util.ArrayList;

public interface Proyeccion {

    public Usuario getUsuario(String correo);
    public ArrayList<Usuario> allUsuarios();
    public Usuario readUsuario(Cursor cursor);

}
