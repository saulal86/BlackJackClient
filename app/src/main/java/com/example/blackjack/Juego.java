package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class Juego extends AppCompatActivity {

    private Cliente cliente = new Cliente();

    private Button buttonPedir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        new Thread() {
            @Override
            public void run() {
                try {
                    if (cliente.iniciar(getIntent().getStringExtra("usuario"))) {
                        String juego = String.valueOf(cliente.repartir());
                        pintarJuego(juego);
                    }
                    else {
                        Toast.makeText(Juego.this, "No se pudo crear la partida", Toast.LENGTH_LONG).show();
                        Juego.this.finish();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

        buttonPedir = findViewById(R.id.buttonPedir);
        buttonPedir.setOnClickListener((v) -> this.pedir());
        buttonPedir = findViewById(R.id.buttonPlantarse);
        buttonPedir.setOnClickListener((v) -> this.plantarse());
        buttonPedir = findViewById(R.id.buttonRepartir);
        buttonPedir.setOnClickListener((v) -> this.repartir());

    }


    private void pedir() {
        new Thread() {
            @Override
            public void run() {
                String juego = null;
                try {
                    juego = cliente.pedir();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pintarJuego(juego);
            }
        }.start();
    }
    private void plantarse() {
        new Thread() {
            @Override
            public void run() {
                String juego = null;
                try {
                    juego = cliente.plantarse();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pintarJuego(juego);
            }
        }.start();
    }
    private void repartir() {
        new Thread() {
            @Override
            public void run() {
                String juego = null;
                try {
                    juego = cliente.repartir();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pintarJuego(juego);
            }
        }.start();
    }

    private void pintarJuego(String juego) {
        Log.i("Juego", "Estado del juego: " + juego);
    }

}