package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.editText);
    }
    public void iniciarJuego(View v){
        Intent i = new Intent(getApplicationContext(), Juego.class);
        i.putExtra("usuario", usuario.getText());
        startActivity(i);
    }
}