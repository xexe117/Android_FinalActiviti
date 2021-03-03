package com.example.activitatfinal;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Auxiliar {

    public static String inreracionPost(String arg1, String arg2, boolean login){

    }

    public static String ineracioGet(Preferencies pref, boolean prova){

    }

    public static String verificacioUsuari(Preferencies pref){
        StringBuilder text = new StringBuilder();
        URL url;
        try {
            url = new URL("http://54.211.78.147/uepcoman.tb/public/usari/" + pref.getCodiusuari());

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setChunkedStreamingMode(25000);
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setRequestProperty("Authorization", pref.getToken());
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String linatxt;
                while ((linatxt = in.readLine()) != null){
                    text.append(linatxt);
                }
                in.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void processarMissatge(RecyclerView recycler, Context context, Preferencies pref, String result, Boolean prova){
        try{
            JSONObject json = new JSONObject(result);

            JSONArray jArray = json.getJSONArray("dades");
            if(prova){
                InterficieBBDD.buidaMissatges();
            }
            for (int i = 0; i < jArray.length(); i++){
                JSONObject jObejct = jArray.getJSONObject(i);
                InterficieBBDD.emmagatzemaRebut(jObejct);
            }
            if (prova){
                mostraMissatges(recycler, context, 0, pref.getCodiusuari());
            } else {
                mostraMissatges(recycler, context, pref.getDarrerMissatge(), pref.getCodiusuari());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void mostraMissatges (RecyclerView recycler, Context context, int darrerMissatge, String idUser){
        ArrayList<Missatge> missatges = InterficieBBDD.llistaMissatge(darrerMissatge);
        Log.w("TEXTE", missatges.toString());

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        missatgeAadapter adapterMissatge = new missatgeAdapter(context, missatges, idUser);
        recycler.setAdapter(adapterMissatge);
        recycler.smoothScrollToPosition(((ArrayList) missatges).size());
    }
}
