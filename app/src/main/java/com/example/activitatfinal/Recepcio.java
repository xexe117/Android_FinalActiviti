package com.example.activitatfinal;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;


public class Recepcio extends AsyncTask<Integer, Integer, String> {

    private RecyclerView lv;
    private Context context;
    private Preferencies pref;
    private boolean prova;

    public Recepcio(Context context, RecyclerView lv, Preferencies pref, boolean prova){
        this.lv = lv;
        this.context = context;
        this.pref = pref;
        this.prova = prova;
    }

    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {
        return Auxiliar.interacioGet(pref, prova);
    }

    @Override
    protected  void onProgressUpdate(Integer... values){

    }

    @Override
    protected void onPostExecute(String result) {
        Auxiliar.processarMissatges(lv, context, pref,result,prova);
    }
}
