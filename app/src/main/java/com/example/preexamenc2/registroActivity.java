package com.example.preexamenc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class registroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);


    }

    public void registrarUsuario(View view) {
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etContraseña = findViewById(R.id.etContraseña);
        EditText etConfirmarContra = findViewById(R.id.etConfirmarContra);


        String usuario = etUsuario.getText().toString();
        String correo = etCorreo.getText().toString();
        String contraseña = etContraseña.getText().toString();
        String confirmContraseña = etConfirmarContra.getText().toString();


        // Verifica que todos los campos estén completos
        if (usuario.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmContraseña.isEmpty() ) {
            Toast.makeText(this, "Por favor, ingresa todos los datos requeridos", Toast.LENGTH_SHORT).show();
        } else if (validarEmail(correo)) {
            // El email tiene el formato correcto, puedes proceder con el envío
            if (contraseña.equals(confirmContraseña)) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                // Regresar
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }else {
            // El email no tiene el formato correcto, muestra un mensaje de error
            Toast.makeText(getApplicationContext(), "Email inválido", Toast.LENGTH_SHORT).show();
        }



    }

    public boolean validarEmail(String email) {
        // Patrón de expresión regular para verificar el formato de email
        String patronEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        // Compilar el patrón en una expresión regular
        Pattern pattern = Pattern.compile(patronEmail);

        // Verificar si el email coincide con el patrón
        return pattern.matcher(email).matches();
    }





    public void regresar(View view) {
        // Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        finish();
    }


}
