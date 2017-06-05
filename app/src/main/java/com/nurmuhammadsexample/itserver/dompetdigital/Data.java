package com.nurmuhammadsexample.itserver.dompetdigital;

/**
 * Created by IT Server on 6/5/2017.
 */

public class Data {

        private String id, nama, alamat, barcode, jumlah, total;
        private String idpemasukan, pemasukan, tanggal, input, keterangan;


        public Data() {
        }

        public Data(String id, String nama, String alamat, String barcode, String jumlah, String total,
                    String idpemasukan, String pemasukan, String tanggal, String input, String keterangan) {
            this.id = id;
            this.nama = nama;
            this.alamat = alamat;
            this.barcode = barcode;
            this.jumlah = jumlah;
            this.total = total;

            this.idpemasukan   = idpemasukan;
            this.pemasukan    = pemasukan;
            this.tanggal    = tanggal;
            this.input    = input;
            this.keterangan       = keterangan;

        }

       /* public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

       */ public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String harga) {
            this.barcode = harga;
        }

        public String getJumlah() {
            return jumlah;
        }

        public void setJumlah(String jumlah) {
            this.jumlah = jumlah;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }


        //method untuk detail dompet
        public String getIdpemasukan() {
            return idpemasukan;
        }

        public void setIdpemasukan(String idpemasukan) {
            this.idpemasukan = idpemasukan;
        }

        public String getPemasukan() {
            return pemasukan;
        }

        public void setPemasukan(String pemasukan) {
            this.pemasukan = pemasukan;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }
}

