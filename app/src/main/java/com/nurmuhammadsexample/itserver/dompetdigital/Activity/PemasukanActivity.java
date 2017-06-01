package com.nurmuhammadsexample.itserver.dompetdigital.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.nurmuhammadsexample.itserver.dompetdigital.App.AppController;
import com.nurmuhammadsexample.itserver.dompetdigital.R;
import com.nurmuhammadsexample.itserver.dompetdigital.Server.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PemasukanActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtnama, txtjumlah;
    private Button btnSimpan;


    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);

        txtnama = (EditText) findViewById(R.id.txtnama);
        txtjumlah = (EditText) findViewById(R.id.txtjumlah);

        pd = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSimpan){
            simpanPemasukan();
        }
    }

    public void simpanPemasukan(){
        final String namapemasukan = txtnama.getText().toString().trim();
        final String jumlah = txtjumlah.getText().toString().trim();
        if (!namapemasukan.isEmpty() && !jumlah.isEmpty()) {
            simpanData(namapemasukan, jumlah);
        } else if (namapemasukan.isEmpty()) {
            txtnama.setError("Harap Isikan Keterangan");
            txtnama.requestFocus();
        } else if (jumlah.isEmpty()) {
            txtjumlah.setError("Jumlah Pemasukan tidak boleh kosong");
            txtjumlah.requestFocus();
        }
    }

    private void simpanData(final String namapemasukan, final String jumlah) {
        String url_simpan = Server.URL+"simpanpemasukan.php";

        String tag_json = "tag_json";

        pd.setCancelable(false);
        pd.setMessage("Menyimpan...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_simpan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());
                hideDialog();

                try {
                    JSONObject jObject = new JSONObject(response);
                    String pesan = jObject.getString("pesan");
                    String hasil = jObject.getString("result");
                    if (hasil.equalsIgnoreCase("true")) {
                        Toast.makeText(PemasukanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), PemasukanActivity.class));
                        finish();
                    } else {
                        Toast.makeText(PemasukanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PemasukanActivity.this, "Error JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", error.getMessage());
                Toast.makeText(PemasukanActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("nama_pemasukan", namapemasukan);
                param.put("harga", jumlah);
                return param;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json);
    }

    private void showDialog() {
        if (!pd.isShowing())
            pd.show();
    }

    private void hideDialog() {
        if (pd.isShowing())
            pd.dismiss();
    }
}
