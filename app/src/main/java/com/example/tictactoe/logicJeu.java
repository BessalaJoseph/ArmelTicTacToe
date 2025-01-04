package com.example.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class logicJeu {
    private int [][] tabJeu;
    private int[] victType = {-1,-1,-1};
    private int joueur = 1;
    private String[] nomJoueur = {"joueur 1", "joueur 2"};
    private Button btnNouv;
    private Button btnCom;
    private TextView playerTurn;
    logicJeu(){
        tabJeu = new int[3][3];
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
                tabJeu[r][c]= 0;
            }

        }
    }
    public boolean validVict(){
        boolean estGagnant = false;
        for(int r=0 ;r<3; r++){
            if(tabJeu[r][0]==tabJeu[r][1] && tabJeu[r][0]==tabJeu[r][2] && tabJeu[r][0]!=0){
                victType = new int[] {r,0,1};
                estGagnant = true;
            }
        }
        for(int c=0 ;c<3; c++){
            if(tabJeu[c][0]==tabJeu[c][1] && tabJeu[c][0]==tabJeu[c][2] && tabJeu[c][0]!=0){
                victType = new int[] {0,c,2};
                estGagnant = true;
            }
        }

        if(tabJeu[0][0]==tabJeu[1][1] && tabJeu[0][0]==tabJeu[2][2] && tabJeu[0][0]!=0){
            victType = new int[] {0,2,3};
            estGagnant = true;
        }
        if(tabJeu[2][0]==tabJeu[1][1] && tabJeu[2][0]==tabJeu[0][2] && tabJeu[2][0]!=0){
            victType = new int[] {2,2,4};
            estGagnant = true;
        }
        int fin = 0;
        for(int r=0 ;r<3; r++){
            for(int c=0 ;c<3; c++){
                if(tabJeu[r][c] != 0){
                    fin+=1;
                }
            }
        }
        if(estGagnant){
            btnNouv.setVisibility(View.VISIBLE);
            btnCom.setVisibility(View.VISIBLE);
            playerTurn.setText(nomJoueur[joueur-1] + " a gagné");
            return true;
        } else if (fin == 9) {
            btnNouv.setVisibility(View.VISIBLE);
            btnCom.setVisibility(View.VISIBLE);
            playerTurn.setText(nomJoueur[joueur-1] + "Jeu Egal");

            return true;
        }
        else{
            return false;
        }

    }
    public boolean miseJourJeu(int lig, int col){
        if(tabJeu[lig-1][col-1] == 0){
            tabJeu[lig-1][col-1] = joueur;
            if(joueur == 1){
                playerTurn.setText((nomJoueur[1] + "'s Turn"));
            }
            else {
                playerTurn.setText((nomJoueur[0] + "'s Turn"));
            }

            return true;
        }
        else{
            return false;
        }
    }
    public void resetGame(){
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
                tabJeu[r][c]= 0;
            }

        }
        joueur = 1;

        btnNouv.setVisibility(View.GONE);
        btnCom.setVisibility(View.GONE);

        playerTurn.setText((nomJoueur[1] + " 's Turn"));
    }

    public void setBtnCom(Button btnCom) {
        this.btnCom = btnCom;
    }
    public void setBtnNouv(Button btnNouv){
        this.btnNouv= btnNouv;
    }
    public void setPlayerTurn(TextView playerTurn){
        this.playerTurn = playerTurn;
    }
    public void setNomJoueur(String[] nomJoueur){
        this.nomJoueur = nomJoueur;
    }

    public int[][] getTabJeu(){
        return tabJeu;
    }
    public void setJoueur(int joueur){
        this.joueur = joueur;
    }
    public int getJoueur(){
        return joueur;
    }
    public int[] getVictType(){
        return victType;
    }
}
