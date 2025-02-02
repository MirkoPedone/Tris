//Package------------------------------------------------------------------------------------------

package com.example.tris;

//Imports------------------------------------------------------------------------------------------

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

//Main-Start---------------------------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    //Instantiate-Start----------------------------------------------------------------------------

    //Objects-------------------------------------------
    private TextView txtV1Round, txtV2Round, txtV_win, txtV_xScore, txtV_oScore;
    private TextView txtV_title, txtV_title2, txtV_by, txtV_language, txtV_color, txtV_resetScore;
    private Button btn1A, btn2A, btn3A, btn1B, btn2B, btn3B, btn1C, btn2C, btn3C;
    private Button btn_playAgain, btn_resetScore, btn_resetNo, btn_resetYes, btn_settings;
    private Button btn_languageIT, btn_languageEN;
    private Button btn_color_greens, btn_color_lightBlueSand, btn_color_greyYellow;
    private ImageView img_x_a1, img_x_a2, img_x_a3, img_x_b1, img_x_b2, img_x_b3, img_x_c1, img_x_c2, img_x_c3;
    private ImageView img_lines;
    private LinearLayout linearL_resetScore, linearL_settings;

    //Variables-------------------------------------------
    private boolean isPlayerOneTurn = true;
    private int turns = 0;
    private int[][] x_o_matrix = {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    };
    private boolean isPlayEnd = false;
    private boolean isPlayWin = false;
    private int xScore = 0;
    private int oScore = 0;
    private boolean isSettingsLinearLayoutShow = false;
    private boolean isItalianSelected = false;
    private boolean isLastWinner1 = false;
    private String colorSetName = "";

    //Instantiate-End------------------------------------------------------------------------------

    //OnCreate-Start-------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //----------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextViews-------------------------------------
        txtV1Round  = findViewById(R.id.txtV_1round);
        txtV2Round  = findViewById(R.id.txtV_2round);
        txtV_win    = findViewById(R.id.txtV_win);
        txtV_xScore = findViewById(R.id.txtV_xScore);
        txtV_oScore = findViewById(R.id.txtV_oScore);
        txtV_title      = findViewById(R.id.txtV_title);
        txtV_title2     = findViewById(R.id.txtV_title2);
        txtV_by         = findViewById(R.id.txtV_by);
        txtV_language   = findViewById(R.id.txtV_language);
        txtV_color      = findViewById(R.id.txtV_color);
        txtV_resetScore = findViewById(R.id.txtV_resetScore);

        //Buttons---------------------------------------
        btn1A = findViewById(R.id.btn_1A);
        btn2A = findViewById(R.id.btn_2A);
        btn3A = findViewById(R.id.btn_3A);
        btn1B = findViewById(R.id.btn_1B);
        btn2B = findViewById(R.id.btn_2B);
        btn3B = findViewById(R.id.btn_3B);
        btn1C = findViewById(R.id.btn_1C);
        btn2C = findViewById(R.id.btn_2C);
        btn3C = findViewById(R.id.btn_3C);
        btn_playAgain  = findViewById(R.id.btn_playAgain);
        btn_resetScore = findViewById(R.id.btn_resetScore);
        btn_resetNo    = findViewById(R.id.btn_resetNo);
        btn_resetYes   = findViewById(R.id.btn_resetYes);
        btn_settings   = findViewById(R.id.btn_settings);
        btn_languageIT   = findViewById(R.id.btn_languageIT);
        btn_languageEN    = findViewById(R.id.btn_languageEN);
        btn_color_greens  = findViewById(R.id.btn_color_greens);
        btn_color_lightBlueSand = findViewById(R.id.btn_color_lightBlueSand);
        btn_color_greyYellow    = findViewById(R.id.btn_color_greyYellow);

        //Images----------------------------------------
        img_x_a1 = findViewById(R.id.img_x_a1);
        img_x_a2 = findViewById(R.id.img_x_a2);
        img_x_a3 = findViewById(R.id.img_x_a3);
        img_x_b1 = findViewById(R.id.img_x_b1);
        img_x_b2 = findViewById(R.id.img_x_b2);
        img_x_b3 = findViewById(R.id.img_x_b3);
        img_x_c1 = findViewById(R.id.img_x_c1);
        img_x_c2 = findViewById(R.id.img_x_c2);
        img_x_c3 = findViewById(R.id.img_x_c3);
        img_lines = findViewById(R.id.img_lines);

        //LinearLayout----------------------------------
        linearL_resetScore = findViewById(R.id.linearL_resetScore);
        linearL_settings   = findViewById(R.id.linearL_settings);

        //Button-1A-------------------------------------
        btn1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn1A, img_x_a1, 0, 0);
            }
        });

        //Button-2A-------------------------------------
        btn2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn2A, img_x_a2, 0, 1);
            }
        });

        //Button-3A-------------------------------------
        btn3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn3A, img_x_a3, 0, 2);
            }
        });

        //Button-1B-------------------------------------
        btn1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn1B, img_x_b1, 1, 0);
            }
        });

        //Button-2B-------------------------------------
        btn2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn2B, img_x_b2, 1, 1);
            }
        });

        //Button-3B-------------------------------------
        btn3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn3B, img_x_b3, 1, 2);
            }
        });

        //Button-1C-------------------------------------
        btn1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn1C, img_x_c1, 2, 0);
            }
        });

        //Button-2C-------------------------------------
        btn2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn2C, img_x_c2, 2, 1);
            }
        });

        //Button-3C-------------------------------------
        btn3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn3C, img_x_c3, 2, 2);
            }
        });

        //Button-PlayAgain------------------------------
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        //Button-ResetScore-----------------------------
        btn_resetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xScore + oScore > 0)
                    showResetScoreLinearLayout();
            }
        });

        //Button-ResetNo--------------------------------
        btn_resetNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideResetScoreLinearLayout();
            }
        });

        //Button-ResetYes-------------------------------
        btn_resetYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScore();
            }
        });

        //Button-Settings-------------------------------
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSettingsLinearLayoutShow) {
                    hideSettingsLinearLayout();
                }
                else {
                    showSettingsLinearLayout();
                }
            }
        });

        //Button-Language-IT----------------------------
        btn_languageIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItalianLanguage();
            }
        });

        //Button-Language-EN----------------------------
        btn_languageEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnglishLanguage();
            }
        });

        //Button-Color-Greens---------------------------
        btn_color_greens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String darkGreen  = "#2f4f4f";
                String lightGreen = "#defade";
                String midGreen   = "#96da98";
                setColor(darkGreen, midGreen, lightGreen, "green");
            }
        });

        //Button-Color-LightBlue-&-Sand-----------------
        btn_color_lightBlueSand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sand      = "#A38C60";
                String midSand   = "#7EC8E3";
                String lightBlue = "#ADD8E6";
                setColor(sand, midSand, lightBlue, "sand");
            }
        });

        //Button-Color-grey-&-Yellow--------------------
        btn_color_greyYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String grey    = "#333333";
                String midGrey = "#B28000";
                String yellow  = "#FFD700";
                setColor(grey, midGrey, yellow, "yellow");
            }
        });

        //Get-Variable-Value-----------------------------------------------------------------------
        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);

        //Set-Language----------------------------------
        isItalianSelected = prefs.getBoolean("isItalianSelected", false);
        if (isItalianSelected) {
            setItalianLanguage();
        } else {
            setEnglishLanguage();
        }
        xScore = prefs.getInt("xScore", 0);
        oScore = prefs.getInt("oScore", 0);
        colorSetName = prefs.getString("colorSetName", "");

        //Set-BackgroundColor---------------------------
        if (colorSetName.equals("green"))
            btn_color_greens.performClick();
        else if (colorSetName.equals("sand"))
            btn_color_lightBlueSand.performClick();
        else if (colorSetName.equals("yellow"))
            btn_color_greyYellow.performClick();

        //Set-PlayersScore------------------------------
        txtV_xScore.setText("X: " + xScore);
        txtV_oScore.setText("O: " + oScore);

        //Set-Button-ResetScore-Visibility--------------
        if (xScore + oScore > 0)
            btn_resetScore.setAlpha(1f);
        else
            btn_resetScore.setAlpha(0.5f);
    }
    //OnCreate-End---------------------------------------------------------------------------------

    //Methods-Start--------------------------------------------------------------------------------

    //SetItalianLanguage-Start--------------------------
    private void setItalianLanguage() {
        txtV_title2.setText("X contro O");
        txtV_by.setText("Creato da ImMirko");
        txtV_language.setText("Lingue"); // modificare spazio scritta
        txtV_color.setText("Colori");
        txtV_resetScore.setText("Azzerare Punteggio?");
        btn_resetNo.setText("No");
        btn_resetYes.setText("Sì");
        // da cambiare in metodo di stampa vittoria && gameResult_tie
        btn_playAgain.setText("Rigioca");
        txtV1Round.setText("Giocatore X");
        txtV2Round.setText("Giocatore O");

        if (isPlayEnd) {
            if (isPlayWin) {
                if (isLastWinner1)
                    txtV_win.setText("Ha vinto il Giocatore X!");
                else
                    txtV_win.setText("Ha vinto il Giocatore O!");
            }
            else {
                txtV_win.setText("Pareggio!");
            }
        }

        isItalianSelected = true;
        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isItalianSelected", true);
        editor.apply();
    }
    //SetItalianLanguage-End----------------------------

    //SetEnglishLanguage-Start--------------------------
    private void setEnglishLanguage() {
        txtV_title2.setText("X vs O");
        txtV_by.setText("Made by ImMirko");
        txtV_language.setText("Language"); // change left space
        txtV_color.setText("Colour");
        txtV_resetScore.setText("Reset Score?");
        btn_resetNo.setText("No");
        btn_resetYes.setText("Yes");
        btn_playAgain.setText("Play Again");
        txtV1Round.setText("Player X's turn");
        txtV2Round.setText("Player O's turn");

        if (isPlayEnd) {
            if (isPlayWin) {
                if (isLastWinner1)
                    txtV_win.setText("Player X Wins!");
                else
                    txtV_win.setText("Player O Wins!");
            } else {
                txtV_win.setText("It's a draw!");
            }
        }

        isItalianSelected = false;
        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isItalianSelected", false);
        editor.apply();
    }
    //SetEnglishLanguage-End----------------------------

    //SetColor-Start------------------------------------
    private void setColor(String darkColor, String midColor, String lightColor, String colorSet) {

        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("colorSetName", colorSet);
        editor.apply();

        //Images----------------------------------------
        ColorFilter colorFilterLight = new PorterDuffColorFilter(Color.parseColor(lightColor), PorterDuff.Mode.SRC_ATOP);
        img_lines.setColorFilter(colorFilterLight);
        img_x_a1.setColorFilter(colorFilterLight);
        img_x_a2.setColorFilter(colorFilterLight);
        img_x_a3.setColorFilter(colorFilterLight);
        img_x_b1.setColorFilter(colorFilterLight);
        img_x_b2.setColorFilter(colorFilterLight);
        img_x_b3.setColorFilter(colorFilterLight);
        img_x_c1.setColorFilter(colorFilterLight);
        img_x_c2.setColorFilter(colorFilterLight);
        img_x_c3.setColorFilter(colorFilterLight);

        //TextViews-------------------------------------
        txtV_title.setTextColor(Color.parseColor(lightColor));
        txtV_title2.setTextColor(Color.parseColor(lightColor));
        txtV_xScore.setTextColor(Color.parseColor(lightColor));
        txtV_oScore.setTextColor(Color.parseColor(lightColor));
        txtV_by.setTextColor(Color.parseColor(lightColor));
        txtV1Round.setTextColor(Color.parseColor(lightColor));
        txtV2Round.setTextColor(Color.parseColor(lightColor));
        txtV_resetScore.setTextColor(Color.parseColor(darkColor));
        txtV_language.setTextColor(Color.parseColor(darkColor));
        txtV_color.setTextColor(Color.parseColor(darkColor));
        txtV_win.setTextColor(Color.parseColor(lightColor));

        //LinearLayouts---------------------------------
        float[] radii = new float[] {50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f};
        RoundRectShape roundRectShape = new RoundRectShape(radii, null, null);

        ShapeDrawable shapeDrawableResetScore = new ShapeDrawable(roundRectShape);
        shapeDrawableResetScore.getPaint().setColor(Color.parseColor(lightColor));
        linearL_resetScore.setBackground(shapeDrawableResetScore);

        ShapeDrawable shapeDrawableSettings = new ShapeDrawable(roundRectShape);
        shapeDrawableSettings.getPaint().setColor(Color.parseColor(lightColor));
        linearL_settings.setBackground(shapeDrawableSettings);

        //Buttons---------------------------------------
        btn_playAgain.setTextColor(Color.parseColor(darkColor));
        btn_playAgain.setBackgroundColor(Color.parseColor(lightColor));

        btn_resetNo.setTextColor(Color.parseColor(darkColor));
        btn_resetYes.setTextColor(Color.parseColor(darkColor));
        btn_resetYes.setBackgroundColor(Color.parseColor(midColor));
        btn_resetNo.setBackgroundColor(Color.parseColor(midColor));

        btn_languageIT.setBackgroundColor(Color.parseColor(lightColor));
        btn_languageEN.setBackgroundColor(Color.parseColor(lightColor));

        btn_color_greyYellow.setBackgroundColor(Color.parseColor(lightColor));
        btn_color_greens.setBackgroundColor(Color.parseColor(lightColor));
        btn_color_lightBlueSand.setBackgroundColor(Color.parseColor(lightColor));

        btn_resetScore.setBackgroundColor(Color.parseColor(lightColor));
        Drawable[] drawablesResetScoreBtn = btn_resetScore.getCompoundDrawables();
        Drawable drawableLeftResetScoreBtn = drawablesResetScoreBtn[0];
        if (drawableLeftResetScoreBtn != null) {
            drawableLeftResetScoreBtn.setColorFilter(new PorterDuffColorFilter(Color.parseColor(darkColor), PorterDuff.Mode.SRC_ATOP));
            btn_resetScore.setCompoundDrawablesWithIntrinsicBounds(drawableLeftResetScoreBtn, drawablesResetScoreBtn[1], drawablesResetScoreBtn[2], drawablesResetScoreBtn[3]);
        }
        btn_settings.setBackgroundColor(Color.parseColor(lightColor));
        Drawable[] drawablesSettingsBtn = btn_settings.getCompoundDrawables();
        Drawable drawableLeftSettingsBtn = drawablesSettingsBtn[0];
        if (drawableLeftSettingsBtn != null) {
            drawableLeftSettingsBtn.setColorFilter(new PorterDuffColorFilter(Color.parseColor(darkColor), PorterDuff.Mode.SRC_ATOP));
            btn_settings.setCompoundDrawablesWithIntrinsicBounds(drawableLeftSettingsBtn, drawablesSettingsBtn[1], drawablesSettingsBtn[2], drawablesSettingsBtn[3]);
        }

    }
    //SetColor-End--------------------------------------

    //Turn-Start----------------------------------------
    private void turn(Button turn_button, ImageView turn_image, int raw, int column) {
        if (!isPlayEnd) {
            disableTurnButton(turn_button);
            activateXorO(turn_image);
            x_o_matrix = updateMatrix(x_o_matrix, raw, column);
            turns++;
            checkForWin(x_o_matrix);
            if (turns == 9 && !isPlayWin) {
                gameResult_tie();
            }
            changeTurn();
        }
    }
    //Turn-End------------------------------------------

    //DisableTurnButton-Start---------------------------
    private void disableTurnButton(Button turn_button) {
        turn_button.setVisibility(View.INVISIBLE);
    }
    //DisableTurnButton-End-----------------------------

    //ActivateXorO-Start--------------------------------
    private void activateXorO(ImageView turn_image) {
        turn_image.setVisibility(View.VISIBLE);
        if (isPlayerOneTurn) {
            turn_image.setImageResource(R.drawable.x);
        } else {
            turn_image.setImageResource(R.drawable.o);
        }
    }
    //ActivateXorO-End----------------------------------

    //UpdateMatrix-Start--------------------------------
    private int[][] updateMatrix(int[][] matrix, int raw, int column) {
        if (isPlayerOneTurn)
            matrix[raw][column] = 1;
        else
            matrix[raw][column] = 2;
        return matrix;
    }
    //UpdateMatrix-End----------------------------------

    //CheckForWin-Start---------------------------------
    private void checkForWin(int[][] matrix) {
        if (matrix[0][0]!=0 && matrix[0][0] == matrix[0][1] && matrix[0][1] == matrix[0][2]) {
            gameResult_win(matrix[0][0]);
        }
        else if (matrix[1][0]!=0 && matrix[1][0] == matrix[1][1] && matrix[1][1] == matrix[1][2]) {
            gameResult_win(matrix[1][0]);
        }
        else if (matrix[2][0]!=0 && matrix[2][0] == matrix[2][1] && matrix[2][1] == matrix[2][2]) {
            gameResult_win(matrix[2][0]);
        }
        else if (matrix[0][0]!=0 && matrix[0][0] == matrix[1][0] && matrix[1][0] == matrix[2][0]) {
            gameResult_win(matrix[0][0]);
        }
        else if (matrix[0][1]!=0 && matrix[0][1] == matrix[1][1] && matrix[1][1] == matrix[2][1]) {
            gameResult_win(matrix[0][1]);
        }
        else if (matrix[0][2]!=0 && matrix[0][2] == matrix[1][2] && matrix[1][2] == matrix[2][2]) {
            gameResult_win(matrix[0][2]);
        }
        else if (matrix[0][0]!=0 && matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) {
            gameResult_win(matrix[0][0]);
        }
        else if (matrix[0][2]!=0 && matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) {
            gameResult_win(matrix[0][2]);
        }
    }
    //CheckForWin-End-----------------------------------

    //GameResult-Win-Start------------------------------
    private void gameResult_win(int winnerValue) {
        isPlayEnd = true;
        isPlayWin = true;

        txtV_win.setVisibility(View.VISIBLE);
        txtV1Round.setVisibility(View.INVISIBLE);
        txtV2Round.setVisibility(View.INVISIBLE);

        if (winnerValue == 1) {
            if (isItalianSelected)
                txtV_win.setText("Ha vinto il Giocatore X!");
            else
                txtV_win.setText("Player X Wins!");
            xScorePoint();
            isLastWinner1 = true;
        } else if (winnerValue == 2) {
            if (isItalianSelected)
                txtV_win.setText("Ha vinto il Giocatore O!");
            else
                txtV_win.setText("Player O Wins!");
            oScorePoint();
            isLastWinner1 = false;
        }

        askToPlayAgain();
    }
    //GameResult-Win-End--------------------------------

    //X-ScorePoint-Start--------------------------------
    private void xScorePoint() {
        txtV_xScore.setText("X: " + ++xScore);
        btn_resetScore.setAlpha(1f);
        saveScores();
    }
    //X-ScorePoint-End----------------------------------

    //O-ScorePoint-Start--------------------------------
    private void oScorePoint() {
        txtV_oScore.setText("O: " + ++oScore);
        btn_resetScore.setAlpha(1f);
        saveScores();
    }
    //O-ScorePoint-End----------------------------------

    //SaveScore-Start-----------------------------------
    private void saveScores() {
        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("xScore", xScore);
        editor.putInt("oScore", oScore);
        editor.apply();
    }
    //SaveScore-End-------------------------------------

    //GameResult-Tie-Start------------------------------
    private void gameResult_tie() {
        isPlayEnd = true;
        txtV_win.setVisibility(View.VISIBLE);
        if (isItalianSelected)
            txtV_win.setText("Pareggio!");
        else
            txtV_win.setText("It's a draw!");
        txtV1Round.setVisibility(View.INVISIBLE);
        txtV2Round.setVisibility(View.INVISIBLE);
        askToPlayAgain();
    }
    //GameResult-Tie-End--------------------------------

    //ChangeTurn-Start----------------------------------
    private void changeTurn() {
        if (!isPlayEnd) {
            if (isPlayerOneTurn) {
                txtV1Round.setVisibility(View.INVISIBLE);
                txtV2Round.setVisibility(View.VISIBLE);
            } else {
                txtV1Round.setVisibility(View.VISIBLE);
                txtV2Round.setVisibility(View.INVISIBLE);
            }
            isPlayerOneTurn = !isPlayerOneTurn;
        }
    }
    //ChangeTurn-End------------------------------------

    //AskToPlayAgain-Start------------------------------
    private void askToPlayAgain() {
        btn_playAgain.setVisibility(View.VISIBLE);
    }
    //AskToPlayAgain-End--------------------------------

    //PlayAgain-Start----------------------------------
    private void playAgain() {
        turns = 0;
        isPlayEnd = false;
        isPlayWin = false;
        isPlayerOneTurn = true;

        txtV1Round.setVisibility(View.VISIBLE);
        txtV2Round.setVisibility(View.INVISIBLE);
        btn_playAgain.setVisibility(View.INVISIBLE);
        txtV_win.setVisibility(View.INVISIBLE);

        btn1A.setVisibility(View.VISIBLE);
        btn2A.setVisibility(View.VISIBLE);
        btn3A.setVisibility(View.VISIBLE);
        btn1B.setVisibility(View.VISIBLE);
        btn2B.setVisibility(View.VISIBLE);
        btn3B.setVisibility(View.VISIBLE);
        btn1C.setVisibility(View.VISIBLE);
        btn2C.setVisibility(View.VISIBLE);
        btn3C.setVisibility(View.VISIBLE);

        img_x_a1.setVisibility(View.INVISIBLE);
        img_x_a2.setVisibility(View.INVISIBLE);
        img_x_a3.setVisibility(View.INVISIBLE);
        img_x_b1.setVisibility(View.INVISIBLE);
        img_x_b2.setVisibility(View.INVISIBLE);
        img_x_b3.setVisibility(View.INVISIBLE);
        img_x_c1.setVisibility(View.INVISIBLE);
        img_x_c2.setVisibility(View.INVISIBLE);
        img_x_c3.setVisibility(View.INVISIBLE);

        x_o_matrix = new int[][]{
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        //Button-1A-------------------------------------
        btn1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn1A, img_x_a1, 0, 0);
            }
        });

        //Button-2A-------------------------------------
        btn2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn2A, img_x_a2, 0, 1);
            }
        });

        //Button-3A-------------------------------------
        btn3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn3A, img_x_a3, 0, 2);
            }
        });

        //Button-1B-------------------------------------
        btn1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn1B, img_x_b1, 1, 0);
            }
        });

        //Button-2B-------------------------------------
        btn2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn2B, img_x_b2, 1, 1);
            }
        });

        //Button-3B-------------------------------------
        btn3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn3B, img_x_b3, 1, 2);
            }
        });

        //Button-1C-------------------------------------
        btn1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn1C, img_x_c1, 2, 0);
            }
        });

        //Button-2C-------------------------------------
        btn2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn2C, img_x_c2, 2, 1);
            }
        });

        //Button-3C-------------------------------------
        btn3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(btn3C, img_x_c3, 2, 2);
            }
        });

        //Button-PlayAgain------------------------------
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });
    }
    //PlayAgain-End------------------------------------

    //ShowResetScoreLinearLayout-Start------------------
    private void showResetScoreLinearLayout() {
        linearL_resetScore.setVisibility(View.VISIBLE);

        btn_resetScore.setEnabled(false);
        btn_resetScore.setAlpha(0.5f);

        btn_settings.setEnabled(false);
        btn_settings.setAlpha(0.5f);
    }
    //ShowResetScoreLinearLayout-End--------------------

    //HideResetScoreLinearLayout-Start------------------
    private void hideResetScoreLinearLayout() {
        linearL_resetScore.setVisibility(View.INVISIBLE);

        btn_resetScore.setEnabled(true);
        if (xScore + oScore > 0)
            btn_resetScore.setAlpha(1f);
        else
            btn_resetScore.setAlpha(0.5f);

        btn_settings.setEnabled(true);
        btn_settings.setAlpha(1f);
    }
    //HideResetScoreLinearLayout-End--------------------

    //ResetScore-Start----------------------------------
    private void resetScore() {
        xScore = 0;
        oScore = 0;
        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("xScore", 0);
        editor.putInt("oScore", 0);
        editor.apply();

        txtV_xScore.setText("X: 0");
        txtV_oScore.setText("O: 0");

        linearL_resetScore.setVisibility(View.INVISIBLE);

        btn_resetScore.setEnabled(true);
        btn_resetScore.setAlpha(0.5f);
        btn_settings.setEnabled(true);
        btn_settings.setAlpha(1f);

        btn_playAgain.setVisibility(View.INVISIBLE);
        playAgain();
    }
    //ResetScore-End------------------------------------

    //ShowSettingsLinearLayout-Start---------------
    private void showSettingsLinearLayout() {
        if (colorSetName.equals("green")) {
            btn_color_lightBlueSand.performClick();
            btn_color_greens.performClick();
            SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(colorSetName, "green");
            editor.apply();
        }
        else if (colorSetName.equals("sand")) {
            btn_color_greens.performClick();
            btn_color_lightBlueSand.performClick();
            SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(colorSetName, "sand");
            editor.apply();
        }
        else if (colorSetName.equals("yellow")){
            btn_color_greens.performClick();
            btn_color_greyYellow.performClick();
            SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(colorSetName, "yellow");
            editor.apply();
        }
        linearL_settings.setVisibility(View.VISIBLE);
        btn_resetScore.setEnabled(false);
        btn_resetScore.setAlpha(0.5f);
        isSettingsLinearLayoutShow = true;
    }
    //ShowSettingsLinearLayout-End-----------------

    //HideSettingsLinearLayout-Start---------------
    private void hideSettingsLinearLayout() {
        linearL_settings.setVisibility(View.INVISIBLE);
        btn_resetScore.setEnabled(true);
        if (xScore + oScore > 0)
            btn_resetScore.setAlpha(1f);
        else
            btn_resetScore.setAlpha(0.5f);
        isSettingsLinearLayoutShow = false;
    }
    //HideSettingsLinearLayout-End-----------------

    //Methods-End--------------------------------------------------------------------------------

}

//Main-End-----------------------------------------------------------------------------------------