package com.xlibrarykr.eduorigin.adminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.xlibrarykr.eduorigin.HttpRequest;
import com.xlibrarykr.eduorigin.R;
import com.xlibrarykr.eduorigin.registration.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminPanelCreateQuizActivity extends AppCompatActivity {
    private String admin_quiz_url = "https://timxn.com/ecom/EduOriginAPI/Registration/quiz.php";
    private final int jsoncode = 1;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private ExecutorService executor;
    private  Handler handler ;
    private Button adminCreateQuizButton;
    private TextInputEditText adminCreateQuizTitleEditText,adminCreateQuizQuestionEditText,adminCreateQuizOption1EditText,adminCreateQuizOption2EditText,adminCreateQuizOption3EditText,adminCreateQuizOption4EditText,adminCreateQuizCorrectAnswerEditText;

    private ImageView vectorIconForOption1,vectorIconForOption2,vectorIconForOption3,vectorIconForOption4,vectorIconForCorrectAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_create_quiz);

        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        adminCreateQuizTitleEditText=findViewById(R.id.adminCreateQuizTitleEditTextId);
        adminCreateQuizQuestionEditText=findViewById(R.id.adminCreateQuizQuestionEditTextId);
        adminCreateQuizOption1EditText=findViewById(R.id.adminCreateQuizOption1EditTextId);
        adminCreateQuizOption2EditText=findViewById(R.id.adminCreateQuizOption2EditTextId);
        adminCreateQuizOption3EditText=findViewById(R.id.adminCreateQuizOption3EditTextId);
        adminCreateQuizOption4EditText=findViewById(R.id.adminCreateQuizOption4EditTextId);
        adminCreateQuizCorrectAnswerEditText=findViewById(R.id.adminCreateQuizCorrectAnswerEditTextId);
        adminCreateQuizButton=findViewById(R.id.adminCreateQuizButtonId);

        vectorIconForOption1=findViewById(R.id.vectorIconForOption1Id);
        vectorIconForOption2=findViewById(R.id.vectorIconForOption2Id);
        vectorIconForOption3=findViewById(R.id.vectorIconForOption3Id);
        vectorIconForOption4=findViewById(R.id.vectorIconForOption4Id);
        vectorIconForCorrectAnswer=findViewById(R.id.vectorIconForCorrectAnswerId);


        bottomNavigationView = findViewById(R.id.bottomNavigationId);

        adminCreateQuizTitleEditText.setFocusableInTouchMode(true);
        //frameLayout = findViewById(R.id.frameLayoutContainerId);
//        adminCreateQuizOption1EditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus && !adminCreateQuizOption1EditText.getText().toString().isEmpty())
//                    vectorIconForOption1.setColorFilter(getResources().getColor(R.color.light_green));
//
//            }
//        });



        adminCreateQuizOption1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!adminCreateQuizOption1EditText.getText().toString().isEmpty())
                    vectorIconForOption1.setColorFilter(getResources().getColor(R.color.purple_500));

                else{
                    vectorIconForOption1.setColorFilter(getResources().getColor(R.color.gray));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {




            }
        });





        adminCreateQuizOption2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!adminCreateQuizOption2EditText.getText().toString().isEmpty())
                    vectorIconForOption2.setColorFilter(getResources().getColor(R.color.purple_500));

                else{
                    vectorIconForOption2.setColorFilter(getResources().getColor(R.color.gray));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {




            }
        });


        adminCreateQuizOption3EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!adminCreateQuizOption3EditText.getText().toString().isEmpty())
                    vectorIconForOption3.setColorFilter(getResources().getColor(R.color.purple_500));

                else{
                    vectorIconForOption3.setColorFilter(getResources().getColor(R.color.gray));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {




            }
        });

        adminCreateQuizOption4EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!adminCreateQuizOption4EditText.getText().toString().isEmpty())
                    vectorIconForOption4.setColorFilter(getResources().getColor(R.color.purple_500));

                else{
                    vectorIconForOption4.setColorFilter(getResources().getColor(R.color.gray));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {




            }
        });


        adminCreateQuizCorrectAnswerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!adminCreateQuizCorrectAnswerEditText.getText().toString().isEmpty())
                    vectorIconForCorrectAnswer.setColorFilter(getResources().getColor(R.color.light_green));

                else{
                    vectorIconForCorrectAnswer.setColorFilter(getResources().getColor(R.color.gray));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });







        //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, new AdminBookUploadFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.menu_quizCreationAdmin);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment store = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bookUploadAdmin:
                       // store = new AdminBookUploadFragment();
                      //  getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, store).commit();
                        //break;
                        startActivity(new Intent(getApplicationContext(), AdminPanelBookUploadActivity.class));
                        break;


                    //case R.id.menu_quizCreationAdmin:
                       // store = new AdminQuizCreationFragment();
                       // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, store).commit();


                    case R.id.menu_Logout_admin:

                        SharedPreferences sp = getSharedPreferences("credential", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.remove("email");
                        editor.remove("password");
                        editor.commit();
                        editor.apply();

                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        finish();
                        break;


                }

                // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId,store).commit();
                return true;

            }

        });

        adminCreateQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=adminCreateQuizTitleEditText.getText().toString().trim();
                String question=adminCreateQuizQuestionEditText.getText().toString().trim();
                String option_1=adminCreateQuizOption1EditText.getText().toString().trim();
                String option_2=adminCreateQuizOption2EditText.getText().toString().trim();
                String option_3=adminCreateQuizOption3EditText.getText().toString().trim();
                String option_4=adminCreateQuizOption4EditText.getText().toString().trim();
                String correct_answer=adminCreateQuizCorrectAnswerEditText.getText().toString().trim();
                if(!title.isEmpty() && !question.isEmpty() && !option_1.isEmpty() &&
                        !option_2.isEmpty() && !option_3.isEmpty() && !option_4.isEmpty()
                        && !correct_answer.isEmpty())

                {
                    createQuiz(title,question,option_1,option_2,option_3,option_4,correct_answer);
                    /*Call<ResponseModelQuiz> call= ApiController.getInstance().getapi().createQuizFromAdmin(title,question,option_1,option_2,option_3,option_4,correct_answer);

                    call.enqueue(new Callback<ResponseModelQuiz>() {
                        @Override
                        public void onResponse(Call<ResponseModelQuiz> call, Response<ResponseModelQuiz> response) {

                            adminCreateQuizCorrectAnswerEditText.setFocusable(false);
                            adminCreateQuizCorrectAnswerEditText.setFocusableInTouchMode(true);
                            adminCreateQuizTitleEditText.setText("");
                            adminCreateQuizQuestionEditText.setText("");
                            adminCreateQuizOption1EditText.setText("");
                            adminCreateQuizOption2EditText.setText("");
                            adminCreateQuizOption3EditText.setText("");
                            adminCreateQuizOption4EditText.setText("");
                            adminCreateQuizCorrectAnswerEditText.setText("");
                            Toast.makeText(AdminPanelCreateQuizActivity.this, "Question created successfully", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<ResponseModelQuiz> call, Throwable t) {

                            adminCreateQuizCorrectAnswerEditText.setFocusable(false);
                            adminCreateQuizCorrectAnswerEditText.setFocusableInTouchMode(true);
                            adminCreateQuizTitleEditText.setText("");
                            adminCreateQuizQuestionEditText.setText("");
                            adminCreateQuizOption1EditText.setText("");
                            adminCreateQuizOption2EditText.setText("");
                            adminCreateQuizOption3EditText.setText("");
                            adminCreateQuizOption4EditText.setText("");
                            adminCreateQuizCorrectAnswerEditText.setText("");
                            Toast.makeText(AdminPanelCreateQuizActivity.this, "Question created successfully", Toast.LENGTH_SHORT).show();
                        }
                    });


*/
                }
                else
                Toast.makeText(AdminPanelCreateQuizActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();

            }
        });
    }
    protected void createQuiz(String title,String question,String option_1,String option_2,String option_3,String option_4,String correct_answer) {
        // Show progress bar if necessary
        // progbar.setVisibility(View.VISIBLE);
        // showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);

        executor.execute(() -> {
            String response = "";
            HashMap<String, String> map = new HashMap<>();

            try {
                HttpRequest req = new HttpRequest(admin_quiz_url);
                // Populate the request parameters
                map.put("title", title);
                map.put("question", question);
                map.put("option_1", option_1);
                map.put("option_2", option_2);
                map.put("option_3", option_3);
                map.put("option_4", option_4);
                map.put("correct_answer", correct_answer);

                response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
            } catch (Exception e) {
                response = e.getMessage();
            }

            String finalResponse = response;
            handler.post(() -> {
                // Process response on the main thread
                Log.d("response", finalResponse);

                try {
                    onTrainInfoTaskCompleted(finalResponse, jsoncode);
                }catch(Exception ex){}
            });
        });
    }

    public void onTrainInfoTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response);
        switch (serviceCode) {
            case jsoncode:
                if (isSuccess(response)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                    adminCreateQuizCorrectAnswerEditText.setFocusable(false);
                    adminCreateQuizCorrectAnswerEditText.setFocusableInTouchMode(true);
                    adminCreateQuizTitleEditText.setText("");
                    adminCreateQuizQuestionEditText.setText("");
                    adminCreateQuizOption1EditText.setText("");
                    adminCreateQuizOption2EditText.setText("");
                    adminCreateQuizOption3EditText.setText("");
                    adminCreateQuizOption4EditText.setText("");
                    adminCreateQuizCorrectAnswerEditText.setText("");
                    Toast.makeText(AdminPanelCreateQuizActivity.this, "Question created successfully", Toast.LENGTH_SHORT).show();

                        }
                    });
                    // Hide progress bar if necessary
                    // progbar.setVisibility(View.GONE);

                    //trainModelArrayList.clear();
                    Resources res = getResources();
                   // trainModelArrayList = getTrainInfo(response);

                    // Update the adapter
                   // DeviceAdapter adapter = new DeviceAdapter(this, R.layout.item, trainModelArrayList, res);
                   // adapter.notifyDataSetChanged();
                    //trainInfo.setAdapter(adapter);
                }else{

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI here
                            // For example, update a TextView or RecyclerView
                            // Update the RecyclerView adapter on the main thread
                            adminCreateQuizCorrectAnswerEditText.setFocusable(false);
                            adminCreateQuizCorrectAnswerEditText.setFocusableInTouchMode(true);
                            adminCreateQuizTitleEditText.setText("");
                            adminCreateQuizQuestionEditText.setText("");
                            adminCreateQuizOption1EditText.setText("");
                            adminCreateQuizOption2EditText.setText("");
                            adminCreateQuizOption3EditText.setText("");
                            adminCreateQuizOption4EditText.setText("");
                            adminCreateQuizCorrectAnswerEditText.setText("");
                            Toast.makeText(AdminPanelCreateQuizActivity.this, "Question creation failed", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
        }
    }
    public boolean isSuccess(String response) {
        Log.d("is_success",response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("message").equals("inserted")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
//    @Override
//    protected void onResume() {
//
//        adminCreateQuizTitleEditText.clearFocus();
//        super.onResume();
//    }
}
