package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class Resultados extends AppCompatActivity implements View.OnClickListener
{
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private VolleyS mVolleyS;
    private  String url = "https://www.ramiro174.com/api/obtener/numero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        requestQueue = mVolleyS.getRequestQueue();
        datos();
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void Anterior(View view)
    {
        Intent anterior = new Intent(this,MainActivity.class);
        startActivity(anterior);
    }

    public void datos()
    {
        JsonObjectRequest objetoJSon = new JsonObjectRequest(Request.Method.GET, url, null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arreglo = response.getJSONArray("resultados");

                    Gson gson = new Gson();
                    final Type JugadorType = new TypeToken<List<Personas>>(){}.getType();
                    List<Personas> ListaJugadores = gson.fromJson(arreglo.toString(), JugadorType);

                    AdaptadorPersona Jugadores = new AdaptadorPersona(ListaJugadores);
                    recyclerView.setAdapter(Jugadores);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(objetoJSon);
    }


    @Override
    public void onClick(View v) {

    }
}
