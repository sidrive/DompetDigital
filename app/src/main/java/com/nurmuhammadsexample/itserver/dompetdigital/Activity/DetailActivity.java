package com.nurmuhammadsexample.itserver.dompetdigital.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nurmuhammadsexample.itserver.dompetdigital.App.AppController;
import com.nurmuhammadsexample.itserver.dompetdigital.Helper.Helper;
import com.nurmuhammadsexample.itserver.dompetdigital.R;
import com.nurmuhammadsexample.itserver.dompetdigital.Server.AdapterDetailA;

public class DetailActivity extends AppCompatActivity {



    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;


    private RecyclerView lvdetail;

 /*   private ArrayList<HashMap<String, String>> list_data;

    private String tag_json = "tag_json";
*/
    private String url = "http://nurmuha.hostzi.com/dompet/selectDetail.php";

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        pd = new ProgressDialog(this);

        lvdetail = (RecyclerView)findViewById(R.id.lvdetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lvdetail.setLayoutManager(linearLayoutManager);

        requestQueue = Volley.newRequestQueue(DetailActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("pemasukan");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("id_pemasukan", json.getString("id_pemasukan"));
                        map.put("pemasukan", json.getString("pemasukan"));
                        map.put("input", json.getString("input"));
                        map.put("tgl_pemasukan", json.getString("tgl_pemasukan"));
                        map.put("keterangan", json.getString("keterangan"));
                        list_data.add(map);
                        AdapterDetailA adapter = new AdapterDetailA(DetailActivity.this, list_data);
                        lvdetail.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


       // showData();
    }

 /*   private void showData() {

        pd.setMessage("Loading...");
        pd.setCancelable(false);
        showDialog();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("RESPONSE ", response.toString());
                hideDialog();
                try {
                    JSONArray jray = response.getJSONArray("pemasukan");
                    for (int a = 0; a < jray.length(); a++){
                        JSONObject json = jray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", json.getString("id"));
                        map.put("id_pemasukan", json.getString("id_pemasukan"));
                        map.put("pemasukan", json.getString("pemasukan"));
                        map.put("input", json.getString("input"));
                        map.put("tgl_pemasukan", json.getString("tgl_pemasukan"));
                        map.put("keterangan", json.getString("keterangan"));
                        list_data.add(map);
                        AdapterDetailA adapter = new AdapterDetailA(DetailActivity.this, list_data);
                        lvdetail.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", error.getMessage());
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonRequest, tag_json);
    }
*/
    private void showDialog() {
        if (!pd.isShowing())
            pd.show();
    }

    private void hideDialog() {
        if (pd.isShowing())
            pd.dismiss();
    }
}
