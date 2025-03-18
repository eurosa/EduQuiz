package com.xlibrarykr.eduorigin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class QuizResultShowActivity extends AppCompatActivity {

    private String send_quiz_result_url = "https://timxn.com/ecom/EduOriginAPI/Registration/quizresultsubmit.php";
    private TextView quizResultShowEmail,quizResultShowResult,quizResultShowVerdict,quizCorrectAnswerShow;
    private String verdict="";
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private ImageView quizResultVerdictIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result_show);

        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();

        String emailForKey=sp.getString("email","");


       // editor.putBoolean("submitButtonIsCheckedFalse",false);
        //editor.commit();
       // editor.apply();

        int result=sp.getInt(emailForKey,0);

        //quizResultShowEmail=findViewById(R.id.quizResultShowEmailId);
        quizResultShowResult=findViewById(R.id.quizResultShowResultId);
        quizResultShowVerdict=findViewById(R.id.quizResultShowVerdictId);
        quizResultVerdictIcon=findViewById(R.id.verdictIconId);
        quizCorrectAnswerShow=findViewById(R.id.quizCorrectAnswerShowId);

        String correct_answer=getIntent().getStringExtra("correct_answer");
        Boolean radioButton1Result=getIntent().getExtras().getBoolean("radioButton1Result");
        Boolean radioButton2Result=getIntent().getExtras().getBoolean("radioButton2Result");
        Boolean radioButton3Result=getIntent().getExtras().getBoolean("radioButton3Result");
        Boolean radioButton4Result=getIntent().getExtras().getBoolean("radioButton4Result");
        int recyclerViewItemSizeForScoring=getIntent().getIntExtra("recylerViewItemSizeForScoring",0);


        String radioButton1Text=getIntent().getStringExtra("radioButton1Text");
        String radioButton2Text=getIntent().getStringExtra("radioButton2Text");
        String radioButton3Text=getIntent().getStringExtra("radioButton3Text");
        String radioButton4Text=getIntent().getStringExtra("radioButton4Text");
        String showCorrectAnswer="";

        if(recyclerViewItemSizeForScoring>result)
        {


            if(radioButton1Result==true && correct_answer.equals("1"))
            {

                verdict = "Correct Answer";
                showCorrectAnswer=radioButton1Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }


            else if(radioButton2Result==true && correct_answer.equals("2"))
            {
                verdict = "Correct Answer";
                showCorrectAnswer=radioButton2Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }

            else if(radioButton3Result==true && correct_answer.equals("3"))
            {
                verdict = "Correct Answer";
                showCorrectAnswer=radioButton3Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }

            else if(radioButton4Result==true && correct_answer.equals("4"))
            {
                verdict = "Correct Answer";
                showCorrectAnswer=radioButton4Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }

            else{

                verdict="Wrong Answer";

            }

        }

        else {

                if (radioButton1Result == true && correct_answer.equals("1")) {

                    verdict = "Correct Answer";


                } else if (radioButton2Result == true && correct_answer.equals("2")) {
                    verdict = "Correct Answer";


                } else if (radioButton3Result == true && correct_answer.equals("3")) {
                    verdict = "Correct Answer";


                } else if (radioButton4Result == true && correct_answer.equals("4")) {
                    verdict = "Correct Answer";


                } else {

                    verdict = "Wrong Answer";

                }


        }



        if(correct_answer.equals("1"))
            showCorrectAnswer="A";

        else if(correct_answer.equals("2"))
            showCorrectAnswer="B";

        else if(correct_answer.equals("3"))
            showCorrectAnswer="C";

        else
            showCorrectAnswer="D";






        if(verdict=="Correct Answer")
        {
            String[] name=emailForKey.split("@");
            quizResultShowVerdict.setText(verdict);
            quizResultShowResult.setText("Total Score : "+String.valueOf(result)+"/"+recyclerViewItemSizeForScoring);
        }

        else{

            quizResultVerdictIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wrong_answer));
            quizResultShowVerdict.setText(verdict);
            quizResultShowResult.setText("Total Score : "+String.valueOf(result)+"/"+recyclerViewItemSizeForScoring);
            quizCorrectAnswerShow.setText("Correct Answer: "+showCorrectAnswer);

        }


        sendQuizResultToDB();

    }


    private void sendQuizResultToDB() {



        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        String email=sp.getString("email","");

        int spResult=sp.getInt(email,0);
       // Call<ResponseModelQuizResult> call= ApiController.getInstance().getapi().submitQuizResult(email,spResult,verdict);
        sendQuizResultToDB(email,spResult,verdict);
    /*    call.enqueue(new Callback<ResponseModelQuizResult>() {
            @Override
            public void onResponse(Call<ResponseModelQuizResult> call, Response<ResponseModelQuizResult> response) {

            }

            @Override
            public void onFailure(Call<ResponseModelQuizResult> call, Throwable t) {

                Toast.makeText(QuizResultShowActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    private void sendQuizResultToDB(String email,int spResult,String verdict) {


        //displayLoader();
        Thread sendThread = new Thread() {

            public void run() {

                RequestHandler rh = new RequestHandler();
                HashMap<String, String> param = new HashMap<String, String>();
                //----------------------------------------------------------------


                //Populate the request parameters
                //Populate the request parameters
                param.put("email", email);
                param.put("result", String.valueOf(spResult));
                param.put("verdict", verdict);
                String result=rh.sendPostRequest(send_quiz_result_url, param);
                // Assuming 'result' is the JSON response string
                try {
                    JSONObject jsonResponse = new JSONObject(result);
                    // boolean status = jsonResponse.getBoolean("message");

                    if (jsonResponse.optString("message").equals("inserted")) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here
                                Toast.makeText(QuizResultShowActivity.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                                // For example, update a TextView or RecyclerView
                                // Update the RecyclerView adapter on the main thread
// Update RecyclerView
                            }
                        });


                    }else if(jsonResponse.optString("message").equals("updated")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here
                                Toast.makeText(QuizResultShowActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                // For example, update a TextView or RecyclerView
                                // Update the RecyclerView adapter on the main thread
// Update RecyclerView
                            }
                        });
                    }else if(jsonResponse.optString("message").equals("failed")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here
                                Toast.makeText(QuizResultShowActivity.this, "Failed Error", Toast.LENGTH_SHORT).show();
                                // For example, update a TextView or RecyclerView
                                // Update the RecyclerView adapter on the main thread
// Update RecyclerView
                            }
                        });
                    } else {
                        Log.e("API Error", "Status is false");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here
                                Toast.makeText(QuizResultShowActivity.this, "API Error", Toast.LENGTH_SHORT).show();
                                // For example, update a TextView or RecyclerView
                                // Update the RecyclerView adapter on the main thread
// Update RecyclerView
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSON Parsing Error", e.getMessage());
                }
                // pDialog.dismiss();

            }
        };
        sendThread.start();

    }
    public boolean isSuccess(String response) {
        Log.d("is_success",response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("status").equals("true")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();

        //Intent intent=new Intent(getApplicationContext(),OnlineQuizFragment.class);
       // startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

}
