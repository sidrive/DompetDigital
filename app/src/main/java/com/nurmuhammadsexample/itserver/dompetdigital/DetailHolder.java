package com.nurmuhammadsexample.itserver.dompetdigital;

/**
 * Created by s_idrive on 05-Jun-17.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHolder extends RecyclerView.ViewHolder {

    public TextView txtidpemasukan, txtpemasukan, txttanggal, txtinput, txtketerangan;


    public DetailHolder(View itemView) {
        super(itemView);

        txtidpemasukan = (TextView)itemView.findViewById(R.id.txtidpemasukan);
        txtpemasukan = (TextView)itemView.findViewById(R.id.txtpemasukan);
        txttanggal = (TextView)itemView.findViewById(R.id.txttanggal);
        txtinput = (TextView)itemView.findViewById(R.id.txtinput);
        txtketerangan = (TextView)itemView.findViewById(R.id.txtketerangan);
    }
}
