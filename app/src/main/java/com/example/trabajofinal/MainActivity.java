package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2;
    private TextView textView;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private VolleyS mVolleyS;
    private  String url = "https://www.ramiro174.com/api/numero";
    private  String urlp = "https://www.ramiro174.com/api/enviar/numero";
    private int numero=0;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btn1 = (Button) findViewById(R.id.btn1);
            btn2 = (Button) findViewById(R.id.btn2);
            textView =(TextView)findViewById(R.id.tnumero);

            mVolleyS = VolleyS.getInstance(this.getApplicationContext());
            requestQueue = mVolleyS.getRequestQueue();
           //datos();

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.btn1:
                            JsonObjectRequest enviarnumero = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {
                                        Integer nu = response.getInt("numero");


                                        if(numero>21)
                                        {
                                            btn1.setEnabled(false);

                                        }
                                        else
                                        {
                                            numero += nu;
                                            textView=findViewById(R.id.tnumero);
                                            String tmpStr10 = String.valueOf(numero);
                                            textView.setText(tmpStr10);
                                        }


                                    }catch(JSONException e){
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            requestQueue.add(enviarnumero);
                            break;

                        case R.id.btn2:
                            JSONObject datos = new JSONObject();
                            try {
                                datos.put("nombre","sisko");
                                datos.put("numero", numero);
                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlp, datos, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject JsonObject) {
                                    Toast.makeText(MainActivity.this, JsonObject.toString(), Toast.LENGTH_SHORT).show();
                                    Log.i("onResponse: ", JsonObject.toString());
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            requestQueue.add(request);
                            break;
                    }

                }
            };
            btn1.setOnClickListener(listener);
            btn2.setOnClickListener(listener);

            recyclerView = findViewById(R.id.recyclerView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

        }

    public void Siguiente(View view)
    {
        Intent siguiente = new Intent(this,Resultados.class);
        startActivity(siguiente);
    }
}
