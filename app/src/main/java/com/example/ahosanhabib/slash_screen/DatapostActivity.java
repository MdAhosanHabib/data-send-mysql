package com.example.ahosanhabib.slash_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DatapostActivity extends AppCompatActivity {


    EditText name, phone, address;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datapost);
        name = findViewById(R.id.nameID);
        phone = findViewById(R.id.phoneID);
        address = findViewById(R.id.addressID);
        save = findViewById(R.id.buttonID);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name.getText().toString().isEmpty() | phone.getText().toString().isEmpty() | address.getText().toString().isEmpty()) {
                    //IF ANY OF THEBOVE FIELD IS EMPTY THEN THIS TOAST WILL SHOW
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {


                    // http://localhost/database/createmployee.php?name=sabbir&phone=017&address=dhaka

                    String url = "http://192.168.1.102/ss/createmployee.php";
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        //WHEN THE DATA ENTRY IS SUCCSFUL
                        public void onResponse(String response) {

                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        }
                    },
                            //IF ITS A FAILURE
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                                }
                            }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("name", name.getText().toString());
                            params.put("phone", phone.getText().toString());
                            params.put("address", address.getText().toString());
                            return params;
                        }
                    };

                    GlobalData.getInstance().addToRequestQueue(request);

                    // Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
