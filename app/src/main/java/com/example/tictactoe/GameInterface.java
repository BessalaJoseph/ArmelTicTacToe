package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameInterface extends AppCompatActivity {
    private tableauJeu TableauJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_interface);
        Button btnNouv = findViewById(R.id.nouveau);
        Button btnCom = findViewById(R.id.acceuil);
        TextView playerTurn = findViewById(R.id.player_display);

        btnNouv.setVisibility(View.GONE);
        btnCom.setVisibility(View.GONE);
        String[] nomJoueur = getIntent().getStringArrayExtra("NOM_JOUEURS");
        if(nomJoueur != null){
            playerTurn.setText(("tour de " + nomJoueur[0]));
        }

        TableauJeu = findViewById(R.id.tableauJeu);
        TableauJeu.setUpGame(btnNouv, btnCom, playerTurn, nomJoueur);
    }
    public void BtnNouveau (View view){
        TableauJeu.resetGame();
        TableauJeu.invalidate();
    }
    public void BtnAcceuil (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}