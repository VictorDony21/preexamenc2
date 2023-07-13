package com.example.preexamenc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class registroActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;

    private DatabaseManager db;
    private int alumnoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);

        db = new DatabaseManager(this);

        // Verifica si se enviaron datos extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            alumnoId = extras.getInt("ALUMNO_ID");

            String usuarioUsuario = extras.getString("USUARIO_USUARIO");
            String usuarioCorreo = extras.getString("USUARIO_CORREO");
            String usuarioContraseña = extras.getString("USUARIO_CONTRASEÑA");


            EditText etUsuario = findViewById(R.id.etUsuario);
            EditText etCorreo = findViewById(R.id.etCorreo);
            EditText etContraseña = findViewById(R.id.etContraseña);

            etUsuario.setText(usuarioUsuario);
            etCorreo.setText(usuarioCorreo);
            etContraseña.setText(usuarioContraseña);

        }

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

        String correoMin = correo.toLowerCase();


        // Verifica que todos los campos estén completos
        if (usuario.isEmpty() || correoMin.isEmpty() || contraseña.isEmpty() || confirmContraseña.isEmpty() ) {
            Toast.makeText(this, "Por favor, ingresa todos los datos requeridos", Toast.LENGTH_SHORT).show();
            return;
        } else if (validarEmail(correoMin)) {
            // El email tiene el formato correcto, puedes proceder con el envío
            if (contraseña.equals(confirmContraseña)) {

                db.agregarUsuario(usuario, correoMin, contraseña);
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                // Regresar
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }
        }else {
            // El email no tiene el formato correcto, muestra un mensaje de error
            Toast.makeText(getApplicationContext(), "Email inválido", Toast.LENGTH_SHORT).show();
            return;
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
