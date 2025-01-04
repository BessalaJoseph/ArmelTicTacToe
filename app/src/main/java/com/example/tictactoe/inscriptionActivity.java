package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inscriptionActivity extends AppCompatActivity {
    private EditText joueur1;
    private EditText joueur2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        joueur1=findViewById(R.id.editTextText7);
        joueur2=findViewById(R.id.editTextText8);
    }
    public void submitButtonClick(View view){
        String NomJoueur1 = joueur1.getText().toString();
        String NomJoueur2 = joueur2.getText().toString();

        Intent intent = new Intent(this, GameInterface.class);
        intent.putExtra("NOM_JOUEURS",new String[] {NomJoueur1,NomJoueur2});
        startActivity(intent);
    }
}