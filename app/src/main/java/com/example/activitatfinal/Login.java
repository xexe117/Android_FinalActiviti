package com.example.activitatfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String result = Auxiliar.interacionPost(user.getText().toString().toUpperCase(), pass.getText().toString(), true);

        if (!result.trim().isEmpty()){
            try{
                JSONObject json = new JSONObject(result);
                Log.d("JSON", result);

                if(json.getBoolean("correcta")){
                    Intent intent = new Intent();
                    intent.putExtra("user", json.getString("dades"));
                    intent.putExtra("pass", pass.getText().toString().toUpperCase());
                    intent.putExtra("recorda", recordaCheckBox.isChecked());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    logInError();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            logInError();
        }
    }

}