package com.xlibrarykr.eduorigin.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xlibrarykr.eduorigin.R;
import com.xlibrarykr.eduorigin.RequestHandler;
import com.xlibrarykr.eduorigin.controllers.ApiController;
import com.xlibrarykr.eduorigin.models.ResponseModelRegistration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

     EditText nameRegister,emailRegister,passwordRegister;
     Button registerButton;
    // public static final String url="http://192.168.0.104/EduOriginAPI/Registration/register.php";
    private String register_url = "https://timxn.com/ecom/EduOriginAPI/Registration/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        nameRegister=findViewById(R.id.nameRegisterFieldId);
        emailRegister=findViewById(R.id.emailRegisterFieldId);
        passwordRegister=findViewById(R.id.passwordRegisterFieldId);
        registerButton=findViewById(R.id.registerButtonId);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // register_user(nameRegister.getText().toString().trim(),emailRegister.getText().toString().trim(),passwordRegister.getText().toString().trim());
                register(nameRegister.getText().toString().trim(),emailRegister.getText().toString().trim(),passwordRegister.getText().toString().trim());

            }
        });

    }

    public void register_user(final String name,final String email,final String password)
    {
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(SignUpActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SignUpActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map=new HashMap<String,String>();
//
//                map.put("name",name);
//                map.put("email",email);
//                map.put("password",password);
//
//                return map;
//            }
//        };
//
//        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);

        Call<ResponseModelRegistration> call= ApiController.getInstance().getapi().sendRegistrationData(name,email,password);

        call.enqueue(new Callback<ResponseModelRegistration>() {
            @Override
            public void onResponse(Call<ResponseModelRegistration> call, Response<ResponseModelRegistration> response) {

                ResponseModelRegistration obj= response.body();

                String output=obj.getMessage();

                if(output.equals("inserted"))
                {
                    Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                }
                if(output.equals("failed"))
                    Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseModelRegistration> call, Throwable t) {

                Toast.makeText(SignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void register(final String name,final String email,final String password) {

        //displayLoader();
        Thread sendThread = new Thread() {

            public void run() {

                RequestHandler rh = new RequestHandler();
                HashMap<String, String> param = new HashMap<String, String>();
                //----------------------------------------------------------------


                //Populate the request parameters
                param.put("name", name);
                param.put("email", email);
                param.put("password", password);
                String result=rh.sendPostRequest(register_url, param);
                try {
                    // Create a JSONObject from the JSON string
                    JSONObject jsonObject = new JSONObject(result);

                    // Extract the value associated with the key "message"
                    String output = jsonObject.getString("message");

                    // Print the value
                    System.out.println("Message: " + output); // Output: Message: exist
                    if(output.equals("inserted"))
                    {

                        runOnUiThread(() -> {

                            // Stuff that updates the UI
                            Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        });
                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        finish();
                    }
                    if(output.equals("failed"))
                        runOnUiThread(() -> {

                            // Stuff that updates the UI
                            Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        });




                } catch (JSONException e) {
                    // Handle JSON parsing errors
                    e.printStackTrace();
                }
                // pDialog.dismiss();

            }
        };
        sendThread.start();

    }
}