package com.nurmuhammadsexample.itserver.dompetdigital.Server;

/**
 * Created by s_idrive on 05-Jun-17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nurmuhammadsexample.itserver.dompetdigital.Activity.DetailActivity;
import com.nurmuhammadsexample.itserver.dompetdigital.R;


import java.util.ArrayList;
import java.util.HashMap;

public class AdapterDetailA extends RecyclerView.Adapter<AdapterDetailA.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public AdapterDetailA(DetailActivity detailActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = detailActivity;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_detail_a, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtidpemasukan.setText(list_data.get(position).get("id_pemasukan"));
        holder.txtpemasukan.setText(list_data.get(position).get("pemasukan"));
        holder.txtinput.setText(list_data.get(position).get("input"));
        holder.txttanggal.setText(list_data.get(position).get("tgl_pemasukan"));
        holder.txtketerangan.setText(list_data.get(position).get("keterangan"));

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtidpemasukan, txtpemasukan, txttanggal, txtinput, txtketerangan;


        public ViewHolder(View itemView) {
            super(itemView);

            txtidpemasukan = (TextView)itemView.findViewById(R.id.txtidpemasukan);
            txtpemasukan = (TextView)itemView.findViewById(R.id.txtpemasukan);
            txttanggal = (TextView)itemView.findViewById(R.id.txttanggal);
            txtinput = (TextView)itemView.findViewById(R.id.txtinput);
            txtketerangan = (TextView)itemView.findViewById(R.id.txtketerangan);
        }
    }
}
