package com.example.tranminhcong_dh51901975;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText eEmail;
    EditText ePass;
    Button btnDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("MainActivity");

        eEmail= findViewById(R.id.eEmail);
        ePass= findViewById(R.id.ePass);
        btnDN= findViewById(R.id.btnDN);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }





    private void Login() {
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);

        Response.Listener<String> listener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String result= jsonObject.getString("KETQUA");
                    if (result.compareTo("true")==0){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity.this, SinhVienActivity.class));
                    }else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener= new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Uri.Builder builder= Uri.parse(Constant.SERVER).buildUpon();
        builder.appendQueryParameter("action", "login");
        builder.appendQueryParameter("email", eEmail.getText().toString().trim());
        builder.appendQueryParameter("pass", ePass.getText().toString().trim());


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