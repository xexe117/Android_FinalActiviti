package com.example.activitatfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InterficieBBDD {

    private static AjudaBBDD db;

    public InterficieBBDD(Context context){
        db = new AjudaBBDD(context);
    }

    public static void emmagatzemaRebut(JSONObject jObject){

        try{
            SQLiteDatabase query = db.getWritableDatabase();
            ContentValues contingut = new ContentValues();
            if (!codiUsuariExisteix(jObject.getString("codiusuari"))){
                contingut.put(AjudaBBDD.COL_USER_ID, jObject.getString("codiusuari"));
                contingut.put(AjudaBBDD.COL_USER_NAME, jObject.getString("nom"));
                query.insert(AjudaBBDD.TAB_USER, null, contingut);
            }
            contingut = new ContentValues();
            query = db.getWritableDatabase();
            query.insert(AjudaBBDD.TAB_MSG, null, contingut);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static boolean codiUsuariExisteix(String codiUsuari){
        return true;
    }

    public static ArrayList<Missatge> llistaMissatge(int idUlimoMsg){

    }

    public static Missatge cursorToMissatge(Cursor cursor){

    }

    public static String getNomUsuari (String userId){
        String select = "SELECT " + AjudaBBDD.COL_USER_NAME + " FROM " + AjudaBBDD.TAB_USER + " WHERE " + AjudaBBDD.COL_USER_ID + " = " + userId;
        return select;
    }

    public static void buidaMissatges(){

    }
}
