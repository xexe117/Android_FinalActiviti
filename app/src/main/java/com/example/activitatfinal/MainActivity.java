package com.example.activitatfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private ReceptorXarxa receptor;
    private final int LOGIN = 1;
    private Intent intent;
    private Preferencies pref;
    private RecyclerView recyclerView;
    private EditText editTextMissatge;
    private final Handler handler = new Handler();
    private Runnable getResponceAfterInterval;
    private AjudaBBDD db;
    private Boolean provaMissatge = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receptor = new ReceptorXarxa();
        this.registerReceiver(receptor,filter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        pref = new Preferencies(this);

        AjudaBBDD db = new AjudaBBDD(this);
        try{
            JSONObject prova_token = new JSONObject(Auxiliar.verificacioUsuari(pref));

            if(!prova_token.getBoolean("correcta")){
                Intent intent = new Intent(this, LoginIn.class);
                startActivity(intent, LOGIN);;
            }
            pref.setDarrerMissatge(prova_token.getInt("darrermisstge"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        editTextMissatge = findViewById(R.id.msg);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  view){
                enviar(view);
            }
        });

        getResponceAfterInterval = new Runnable() {
            @Override
            public void run() {
                try{
                    new Recepcio(MainActivity.this, recyclerView, pref, provaMissatge).execute();
                } catch (Exception e){

                }
                handler.postDelayed(this, 1000*60);
            }
        };
    }

    public void enviar(View view){
        if (!editTextMissatge.getText().toString().equals("")){
            new Enviament(pref).execute(editTextMissatge.getText().toString());
            editTextMissatge.setText("");

            new Recepcio(this, recyclerView, pref, provaMissatge).execute();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN){
            if (resultCode == Activity.RESULT_OK){
                try {
                    JSONObject user = new JSONObject(data.getStringExtra("user"));

                    pref.setCodiUsuari(user.getString("codiusuari"));
                    pref.setUser(user.getString("email"));
                    pref.setToken(user.getString("token"));
                    pref.setPassword(data.getStringExtra("pass"));

                    pref.setRecorda((data.getBooleanExtra("Recorda", false)));
                    JSONObject prova_usuari = new JSONObject(Auxiliar.verificacioUsuari(pref));

                    pref.setDarrerMissatge(prova_usuari.getInt("darrermissatge"));
                } catch (JSONException e) {
                    Log.d("JSON_ERROR", e.getMessage());
                }
            }
            if (resultCode == Activity.RESULT_CANCELED){
                intent = new Intent(this, LogIn.class);
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        handler.removeCallBacks(getResponceAfterInterval);
        handler.post(getResponceAfterInterval);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (receptor != null) this.unregisterReceiver(receptor);
    }
}