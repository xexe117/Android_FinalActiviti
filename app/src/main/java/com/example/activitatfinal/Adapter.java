package com.example.activitatfinal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {
    private int TIPUS_IGUAL = 1;
    private int TIPUS_DIFF = 2;

    private Context context;
    private ArrayList<Missatge> llista_missatges;
    private String idUserActual;
    private int idUserActualEnter;

    public Adapter(Context context, ArrayList<Missatge> llista_missatges, String idUserActual){
        this.context = context;
        this.llista_missatges = llista_missatges;
        this.idUserActual = idUserActual;
        this.idUserActualEnter = Integer.parseInt(idUserActual);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

   /* @Override
    public int getItemViewType(int posicio){

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TIPUS_IGUAL) {

        } else {

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){

    }
    public static class MissatgeDreataViewHolder extends RecyclerView.ViewHolder{
        public TextView etNom, etMissatge, etData;

        public MissatgeDreataViewHolder(View itemView){
            super(itemView);
        }

        private void assignaInformacio(Missatge missatge){

        }
    }

    public static class MissatgeEsquerreViewHolder extends RecyclerView.ViewHolder{

        public TextView etNom,etMissatge,etData;

        public MissatgeEsquerreViewHolder(View itemView){
            super(itemView);
        }
    }

    private void assignaInformacio(Missatge missatge){

    }

    @Override
    public int getItemVierwType(int posicio){
        if(idUserActualEnter == Integer.parseInt(llista_missatges.get(posicio).getIdUsuari()))
        {
            return TIPUS_IGUAL;
        } else {
            return TIPUS_DIFF;
        }
    }*/

}
