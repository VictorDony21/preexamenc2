package com.example.preexamenc2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ingresar(View view) {
        Intent intent = new Intent(this, mostrarRegistros.class);
        startActivity(intent);
    }

    public void registrarUsuario(View view) {
        Intent intent = new Intent(this, registroActivity.class);
        startActivity(intent);
    }

    public void salir(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas salir de la aplicación?");
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0); // Cierra la aplicación
            }
        }).setNegativeButton("Cancelar", null);
        builder.show();
    }
}
