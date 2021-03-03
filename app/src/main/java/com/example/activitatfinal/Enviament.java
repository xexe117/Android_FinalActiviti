package com.example.activitatfinal;

import android.os.AsyncTask;

import org.json.JSONException;

public class Enviament extends AsyncTask<String, Integer, String> {

    private Preferencies pref;

    public Enviament(Preferencies pref){
        this.pref = pref;
    }

    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return Auxiliar.interacionPost(params[0], pref.getCodiusuari(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected  void onProgressUpdate(Integer... values){

    }
}
