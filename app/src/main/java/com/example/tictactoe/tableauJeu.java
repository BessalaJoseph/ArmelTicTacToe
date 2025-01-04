package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class tableauJeu extends View {
    private final int couleurtab;
    private final int couleurO;
    private final int couleurX;
    private final logicJeu game;
    private final int couleurlignVict;

    private boolean ligneVict = false;
    private final Paint paint = new Paint();
    private int cellTail = getWidth()/3;

    public tableauJeu (Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        game = new logicJeu();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.tableauJeu, 0,0);
        try {
            couleurtab = a.getInteger(R.styleable.tableauJeu_couleurtab, 0);
            couleurO = a.getInteger(R.styleable.tableauJeu_couleurO, 0);
            couleurX = a.getInteger(R.styleable.tableauJeu_couleurX, 0);
            couleurlignVict = a.getInteger(R.styleable.tableauJeu_couleurlignVict, 0);
        }finally {
            a.recycle();
        }
    }
    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        int dimension=Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellTail=dimension/3;

        setMeasuredDimension(dimension, dimension);
    }
    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        dessineTableauJeu(canvas);
        drawMakers(canvas);
        if(ligneVict){
            paint.setColor(couleurlignVict);
            DessineLigneVict(canvas);
        }
    }
    @SuppressLint("ClickableViewAccessiility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            int lig = (int) Math.ceil(y/cellTail);
            int col = (int) Math.ceil(x/cellTail);
            if(!ligneVict){
                if(game.miseJourJeu(lig, col)){
                    invalidate();
                    if(game.validVict()){
                        ligneVict = true;
                        invalidate();
                    }
                    if(game.getJoueur() % 2 == 0){
                        game.setJoueur(game.getJoueur() - 1);
                    }
                    else{
                        game.setJoueur(game.getJoueur() + 1);
                    }
                }
            }


            invalidate();

            return true;
        }
        return false;
    }
    private void dessineTableauJeu(Canvas canvas){
        paint.setColor(couleurtab);
        paint.setStrokeWidth(16);
        for (int c=1; c<3; c++){
            canvas.drawLine(cellTail*c,0,cellTail*c,canvas.getWidth(), paint);
        }
        for (int r=1; r<3; r++){
            canvas.drawLine(0,cellTail*r,canvas.getWidth(),cellTail*r, paint);
        }
    }
    private void drawMakers(Canvas canvas){
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
                if(game.getTabJeu()[r][c] != 0){
                    if(game.getTabJeu()[r][c] == 1){
                        DessineX(canvas, r , c);
                    }
                    else{
                        DessineO(canvas, r, c);
                    }
                }
            }

        }
    }
    protected void DessineX(Canvas canvas,int lig,int col){
        paint.setColor(couleurX);

        canvas.drawLine((float) ((col+1)*cellTail - cellTail*0.2),
                (float) (lig*cellTail + cellTail*0.2),
                (float) (col*cellTail+cellTail*0.2),
                (float) ((lig+1)*cellTail - cellTail*0.2),
                        paint);
        canvas.drawLine((float) (col*cellTail + cellTail*0.2),
                (float) (lig*cellTail + cellTail*0.2),
                (float) ((col+1)*cellTail - cellTail*0.2),
                (float) ((lig+1)*cellTail - cellTail*0.2),
                paint);

    }
    protected void DessineO(Canvas canvas ,int lig,int col){
        paint.setColor(couleurO);

        canvas.drawOval((float) (col*cellTail + cellTail*0.2),
                (float) (lig*cellTail + cellTail*0.2),
                (float) ((col*cellTail+cellTail) - cellTail*0.2),
                (float) ((lig*cellTail+cellTail) - cellTail*0.2),
                paint);
    }
    private void DessineLigneHoriz(Canvas canvas, int lig, int col){
        canvas.drawLine(col, lig*cellTail + (float)cellTail/2, cellTail*3,
                lig*cellTail + (float)cellTail/2,paint);
    }
    protected void DessineLigneVerti(Canvas canvas, int lig, int col){
        canvas.drawLine(col*cellTail + (float)cellTail/2,lig, col*cellTail + (float)cellTail/2,
                cellTail*3, paint);
    }
    protected void DessineLigneObPos(Canvas canvas){
        canvas.drawLine(0, cellTail*3, cellTail*3, 0, paint);
    }
    protected void DessineLigneObNeg(Canvas canvas){
        canvas.drawLine(0, 0, cellTail*3, cellTail*3, paint);
    }
    private void DessineLigneVict(Canvas canvas){
        int lig= game.getVictType()[0];
        int col = game.getVictType()[1];

        switch (game.getVictType()[2]){
            case 1:
                DessineLigneHoriz(canvas, lig, col);
                break;
            case 2:
                DessineLigneVerti(canvas, lig, col);
                break;
            case 3:
                DessineLigneObNeg(canvas);
                break;
            case 4:
                DessineLigneObPos(canvas);
                break;
        }
    }
    public void setUpGame(Button nouv, Button comm, TextView playerDisplay, String[] names){
        game.setBtnNouv(nouv);
        game.setBtnCom(comm);
        game.setPlayerTurn(playerDisplay);
        game.setNomJoueur(names);
    }
    public void resetGame(){
        game.resetGame();
        ligneVict = false;
    }
}
