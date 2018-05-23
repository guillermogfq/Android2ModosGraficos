package cl.ubiobio.laboratorio2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity implements DialogCustom.ListenerButton {

    private SplashActivity _this = this;
    private SharedPreferences sharedPre;
    private SharedPreferences.Editor editorSP;

    private int NO_PREFERENCES = 0;
    private int MODO_JOVEN = 1;
    private int MODO_VIEJO = 2;

    private static final int ID_DIALOG_MODO_GRAFICO = 0;

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
                    DialogCustom dlog = new DialogCustom();
                    dlog.setIdDialog(ID_DIALOG_MODO_GRAFICO);
                    dlog.setTxt_msg("Elija un modo gráfico para esta aplicación");
                    dlog.setTxt_title("SmartAPP");
                    dlog.setTxt_btn_neg("Modo Viejo");
                    dlog.setTxt_btn_pos("Modo Joven");
                    dlog.show(getFragmentManager(),"My Dialog Custom");
                    /*
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
                    dialog.show();*/
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

    @Override
    public void onClickNegButton(int idDialog) {
        switch (idDialog){
            case ID_DIALOG_MODO_GRAFICO:
                editorSP.putInt("MODO",MODO_VIEJO);
                editorSP.commit();
                Intent iniciar = new Intent(_this, MainViejoActivity.class);
                startActivity(iniciar);
                finish();
                break;
            case 8:

                break;
        }
    }

    @Override
    public void onClickPosButton(int idDialog) {
        switch (idDialog) {
            case ID_DIALOG_MODO_GRAFICO:
                editorSP.putInt("MODO",MODO_JOVEN);
                editorSP.commit();
                Intent iniciar = new Intent(_this, MainJovenActivity.class);
                startActivity(iniciar);
                finish();
                break;
        }
    }
}
