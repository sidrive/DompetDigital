package com.nurmuhammadsexample.itserver.dompetdigital;

/**
 * Created by IT Server on 6/5/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.nurmuhammadsexample.itserver.dompetdigital.App.AppController;
import com.nurmuhammadsexample.itserver.dompetdigital.Data;

import java.util.List;

public class AdapterDetail extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;


    public AdapterDetail(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_detail, null);

        TextView id_pemasukan = (TextView) convertView.findViewById(R.id.id_pemasukan);
        TextView input = (TextView) convertView.findViewById(R.id.input);
        TextView tanggal = (TextView) convertView.findViewById(R.id.tgl_pemasukan);
        TextView pemasukan = (TextView) convertView.findViewById(R.id.pemasukan);
        TextView keterangan = (TextView) convertView.findViewById(R.id.keterangan);

        Data data = items.get(position);

        id_pemasukan.setText(data.getIdpemasukan());
        input.setText(data.getInput());
        tanggal.setText("Tanggal dibuat : "+data.getTanggal());
        pemasukan.setText("Pemasukan : Rp. "+data.getPemasukan());
        keterangan.setText(data.getKeterangan());

        return convertView;
    }
}
