package com.example.preexamenc2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText etCorreo;
    EditText etContraseña;

    String correo;
    String contraseña;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);

        correo = etCorreo.getText().toString();
        contraseña = etContraseña.getText().toString();
    }


    public void ingresar(View view) {

        String correo = etCorreo.getText().toString().trim();
        String correoMin = correo.toLowerCase();
        String contraseña = etContraseña.getText().toString().trim();
        databaseManager = new DatabaseManager(this);

        if ( correoMin.isEmpty() || contraseña.isEmpty() ) {
            Toast.makeText(this, "Por favor, ingresa todos los datos requeridos", Toast.LENGTH_SHORT).show();
        } else {
            if (databaseManager.verificarUsuario(correoMin, contraseña)){
                Toast.makeText(this, "Bienvenido :D", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, mostrarRegistros.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
            }
        }
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

    public boolean validarEmail(String email) {
        // Patrón de expresión regular para verificar el formato de email
        String patronEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        // Compilar el patrón en una expresión regular
        Pattern pattern = Pattern.compile(patronEmail);

        // Verificar si el email coincide con el patrón
        return pattern.matcher(email).matches();
    }
}
