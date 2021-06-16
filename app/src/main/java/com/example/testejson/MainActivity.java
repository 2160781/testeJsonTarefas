package com.example.testejson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> numberlist = new ArrayList<>();

    private ListView lv;
    TextView caixadetexto;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        caixadetexto = findViewById(R.id.textView);
        get_json();
    }

    public void get_json(){

        String json;
        try{
            InputStream is = getAssets().open("JSONFinal.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);

                if (obj.getString("nome").equals("Receita de bolo")){

                    for(int a = 1; a < 5; a++){
                        if(a == 1){
                            numberlist.add(obj.getString("texto" + a));

                        }

                    }

                }

            }

            caixadetexto.setText(numberlist.toString());
            Toast.makeText(getApplicationContext(),numberlist.toString(),Toast.LENGTH_LONG).show();

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}