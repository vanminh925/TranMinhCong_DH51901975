package com.example.tranminhcong_dh51901975;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class SinhVienActivity extends AppCompatActivity {

    EditText eLop;
    Button btnLayDS;
    ListView lView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("SinhVienActivity");

        eLop= findViewById(R.id.eLop);
        btnLayDS= findViewById(R.id.btnLayDS);
        lView= findViewById(R.id.lview);


        btnLayDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layDS();
            }
        });

    }

    private void layDS() {
        RequestQueue requestQueue= Volley.newRequestQueue(SinhVienActivity.this);

        Response.Listener<String> listener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SinhVienActivity.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject= new JSONObject(response);
                    JSONArray jsonArray= new JSONArray(jsonObject.getString("KETQUA"));
                    List<Student> list= new ArrayList<>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject json= jsonArray.getJSONObject(i);
                        String ten= json.getString("TEN");
                        String dtb= json.getString("DTB");
                        list.add(new Student(ten, dtb));
                        ListSinhVienAdapter adapter= new ListSinhVienAdapter(list, SinhVienActivity.this, R.layout.sinhvien);
                        lView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        };

        Response.ErrorListener errorListener= new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SinhVienActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Uri.Builder builder= Uri.parse(Constant.SERVER).buildUpon();
        builder.appendQueryParameter("action", "getsinhvien");
        builder.appendQueryParameter("lop", eLop.getText().toString().trim());

        String url= builder.build().toString();

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, listener, errorListener);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(stringRequest);

    }

}