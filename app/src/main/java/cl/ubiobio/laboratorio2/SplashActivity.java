package cl.ubiobio.laboratorio2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private SplashActivity _this = this;
    private SharedPreferences sharedPre;
    private SharedPreferences.Editor editorSP;

    private int NO_PREFERENCES = 0;
    private int MODO_JOVEN = 1;
    private int MODO_VIEJO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPre = getSharedPreferences(getString(R.string.sharedPreID), MODE_PRIVATE);
        editorSP = sharedPre.edit();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iniciar = null;
                if(sharedPre.getInt("MODO", NO_PREFERENCES) == NO_PREFERENCES){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(_this);
                    alertBuilder.setMessage("Elija el modo de interfaz")
                            .setTitle("App Test Lab")
                            .setPositiveButton("Modo Joven", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editorSP.putInt("MODO",MODO_JOVEN);
                                    editorSP.commit();
                                    Intent iniciar = new Intent(_this, MainJovenActivity.class);
                                    startActivity(iniciar);
                                    finish();
                                }
                            })
                            .setNegativeButton("Modo Viejo", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editorSP.putInt("MODO",MODO_VIEJO);
                                    editorSP.commit();
                                    Intent iniciar = new Intent(_this, MainViejoActivity.class);
                                    startActivity(iniciar);
                                    finish();
                                }
                            });
                    AlertDialog dialog = alertBuilder.create();
                    dialog.show();
                }else{
                    if(sharedPre.getInt("MODO", NO_PREFERENCES) == MODO_JOVEN){
                        iniciar = new Intent(_this, MainJovenActivity.class);
                    }else{
                        iniciar = new Intent(_this, MainViejoActivity.class);
                    }
                    startActivity(iniciar);
                    finish();
                }
            }
        }, 3000);//Milisegundos
    }
}
