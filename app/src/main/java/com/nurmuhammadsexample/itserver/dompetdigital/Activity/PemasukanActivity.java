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
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nurmuhammadsexample.itserver.dompetdigital.App.AppController;
import com.nurmuhammadsexample.itserver.dompetdigital.Helper.Helper;
import com.nurmuhammadsexample.itserver.dompetdigital.R;
import com.nurmuhammadsexample.itserver.dompetdigital.Server.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PemasukanActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtnama, txtjumlah;
    private Button btnSimpan;
    private Spinner spNamen;
    private Button btnKembali;

    private ProgressDialog progressDialog;
    private static final String URL_REGISTER_DEVICE = "http://nurmuha.hostzi.com/dompet/simpanpemasukan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);

        btnKembali = (Button) findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(this);

        spNamen = (Spinner) findViewById(R.id.sp_name);

        txtnama = (EditText) findViewById(R.id.txtnama);
        txtjumlah = (EditText) findViewById(R.id.txtjumlah);

    }



    @Override
    public void onClick(View v) {
        if(v == btnSimpan){
            sendPemasukan();
        }
        if(v == btnKembali){
            Intent intent = new Intent(PemasukanActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    //storing token to mysql server
    private void sendPemasukan() {
        txtnama.setError(null);
        txtjumlah.setError(null);
        /*check keberadaan teks*/
        if (Helper.isEmpty(txtnama)) {
            txtnama.setError("Harap Isikan Keterangan");
            txtnama.requestFocus();
        } else if (Helper.isEmpty(txtjumlah)) {
            txtjumlah.setError("Jumlah Pemasukan tidak boleh kosong");
            txtjumlah.requestFocus();
        } else {

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Proses simpan pemasukan...");
            progressDialog.show();

            final String keterangan = txtnama.getText().toString();
            final String jumlah = txtjumlah.getText().toString();
            final String mode   = spNamen.getSelectedItem().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
                                Toast.makeText(PemasukanActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(PemasukanActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("keterangan", keterangan);
                    params.put("jumlah", jumlah);
                    params.put("mode", mode);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            Intent intent = new Intent(PemasukanActivity.this, PemasukanActivity.class);
            startActivity(intent);

        }
       /* */
    }


}
