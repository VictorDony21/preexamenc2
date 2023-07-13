package com.example.preexamenc2;

public interface Persistencia {

    public void openDataBase();
    public void closeDataBase();
    public long agregarUsuario(Usuario usuario);

}
