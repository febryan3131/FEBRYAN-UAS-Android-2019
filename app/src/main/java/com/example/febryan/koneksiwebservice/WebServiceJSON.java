package com.example.febryan.koneksiwebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceJSON extends AppCompatActivity {

    private TextView textHasilJSON;
    private TextView tid;
    private TextView tnama;
    private TextView tasaldaerah;
    private TextView tkamar;
    private EditText nomor;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_json);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.testJSON);
        Button tombolJson = findViewById(R.id.btnJSON);
        tid = findViewById(R.id.tid);
        tnama = findViewById(R.id.tnama);
        tasaldaerah = findViewById(R.id.tasaldaerah);
        tkamar = findViewById(R.id.tkamar);
        nomor = findViewById(R.id.nomor);

        /////////////////mengambil data JSON //////////////////////////////
        tombolJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isian;
                isian =Integer.parseInt(nomor.getText().toString());
                JSON(isian);
            }
        });

    }

    /////////////METHOD////////////////////////
    private void JSON(final int isian){
        //////Masukan kata-kata//////////////
        String url ="http://192.168.5.40/mahasantri.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
           /////////ketika di response melakukan apa///////////////
            @Override
            public void onResponse(JSONArray response) {
                try{

                ///////// pengambilan data mahasantri//////////////

                    JSONObject mahasantri = response.getJSONObject(isian-1);
                        //////////kita panggil/get 1 satu////////////
                        String id = mahasantri.getString("id");
                        String nama = mahasantri.getString("nama");
                        String asalDaerah = mahasantri.getString("asal_daerah");
                        String kamar = mahasantri.getString("kamar");

                        //////kita simpan/set//////////
                    tid.setText(id);
                    tnama.setText(nama);
                    tasaldaerah.setText(asalDaerah);
                    tkamar.setText(kamar);




            }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error1", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }
}
