package com.nurmuhammadsexample.itserver.dompetdigital.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import com.nurmuhammadsexample.itserver.dompetdigital.R;
import com.nurmuhammadsexample.itserver.dompetdigital.Server.Server;

public class MainActivity extends AppCompatActivity {

    private TextView txtSaldo;
    private Button btnPemasukan, btnPengeluaran;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = Server.URL+"getSaldo.php";

        txtSaldo = (TextView)findViewById(R.id.txtSaldo);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("saldo");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("saldo", json.getString("saldo"));
                        list_data.add(map);
                    }
                    txtSaldo.setText("Rp. "+list_data.get(0).get("saldo"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);

        btnPemasukan = (Button) findViewById(R.id.btnPemasukan);
        btnPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PemasukanActivity.class);
                startActivity(intent);
            }
        });

        btnPengeluaran = (Button) findViewById(R.id.btnDetail);
        btnPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PemasukanActivity.class);
               startActivity(intent);
            }
        });
    }
}
