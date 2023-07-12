package com.example.preexamenc2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alumnos.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USUARIOS = "usuario";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_CORREO = "correo";
    private static final String COLUMN_CONTRASEÑA = "contraseña";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIO_TABLE = "CREATE TABLE " + TABLE_USUARIOS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USUARIO + " TEXT,"
                + COLUMN_CORREO + " TEXT,"
                + COLUMN_CONTRASEÑA + " TEXT"
                + ")";
        db.execSQL(CREATE_USUARIO_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public void agregarUsuario(String usuario, String correo, String contraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO, usuario);
        values.put(COLUMN_CORREO, correo);
        values.put(COLUMN_CONTRASEÑA, contraseña);
        long resultado = db.insert(TABLE_USUARIOS, null, values);
        if (resultado == -1) {

        }
        db.close();
    }

    public Usuario obtenerUsuarioPorId(int usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USUARIOS,
                new String[] { COLUMN_ID, COLUMN_USUARIO, COLUMN_CORREO, COLUMN_CONTRASEÑA },
                COLUMN_ID + "=?",
                new String[] { String.valueOf(usuarioId) },
                null,
                null,
                null,
                null
        );

        Usuario usuario = null;
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexUsuario = cursor.getColumnIndex(COLUMN_USUARIO);
            int columnIndexCorreo = cursor.getColumnIndex(COLUMN_CORREO);
            int columnIndexContraseña = cursor.getColumnIndex(COLUMN_CONTRASEÑA);

            if (columnIndexId != -1 && columnIndexUsuario != -1 && columnIndexCorreo != -1 && columnIndexContraseña != -1) {
                usuario = new Usuario(
                        cursor.getInt(columnIndexId),
                        cursor.getString(columnIndexUsuario),
                        cursor.getString(columnIndexCorreo),
                        cursor.getString(columnIndexContraseña)
                );
            }

            cursor.close();
        }

        return usuario;
    }


    public List<Usuario> buscarUsuarios(String query) {
        List<Usuario> listaUsuario = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS +
                " WHERE " + COLUMN_USUARIO + " LIKE '%" + query + "%' OR " +
                COLUMN_CORREO + " LIKE '%" + query + "%' OR " + COLUMN_CONTRASEÑA + " LIKE '%" + query + "%'", null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexUsuario = cursor.getColumnIndex(COLUMN_USUARIO);
            int columnIndexCorreo = cursor.getColumnIndex(COLUMN_CORREO);
            int columnIndexContraseña = cursor.getColumnIndex(COLUMN_CONTRASEÑA);

            do {
                Usuario usuario = new Usuario();
                if (columnIndexId != -1) {
                    usuario.setId(cursor.getInt(columnIndexId));
                }
                if (columnIndexUsuario != -1) {
                    usuario.setUsuario(cursor.getString(columnIndexUsuario));
                }
                if (columnIndexCorreo != -1) {
                    usuario.setCorreo(cursor.getString(columnIndexCorreo));
                }
                if (columnIndexContraseña != -1) {
                    usuario.setContraseña(cursor.getString(columnIndexContraseña));
                }

                listaUsuario.add(usuario);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return listaUsuario;
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuario = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexUsuario = cursor.getColumnIndex(COLUMN_USUARIO);
            int columnIndexCorreo = cursor.getColumnIndex(COLUMN_CORREO);
            int columnIndexContraseña = cursor.getColumnIndex(COLUMN_CONTRASEÑA);

            do {
                Usuario usuario = new Usuario();
                if (columnIndexId != -1) {
                    usuario.setId(cursor.getInt(columnIndexId));
                }
                if (columnIndexUsuario != -1) {
                    usuario.setUsuario(cursor.getString(columnIndexUsuario));
                }
                if (columnIndexCorreo != -1) {
                    usuario.setCorreo(cursor.getString(columnIndexCorreo));
                }
                if (columnIndexContraseña != -1) {
                    usuario.setContraseña(cursor.getString(columnIndexContraseña));
                }

                listaUsuario.add(usuario);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return listaUsuario;
    }
}
