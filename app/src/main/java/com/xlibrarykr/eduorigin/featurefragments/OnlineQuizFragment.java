package com.xlibrarykr.eduorigin.featurefragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xlibrarykr.eduorigin.R;
import com.xlibrarykr.eduorigin.RequestHandler;
import com.xlibrarykr.eduorigin.adapters.QuizAdapter;
import com.xlibrarykr.eduorigin.controllers.ApiController;
import com.xlibrarykr.eduorigin.models.ResponseModelQuiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineQuizFragment extends Fragment {

    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    Context context;
    private String read_quiz_url = "https://timxn.com/ecom/EduOriginAPI/Registration/readquiz.php";
    public OnlineQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_online_quiz, container, false);


        SharedPreferences sp= getActivity().getSharedPreferences("credential",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        String emailForKey=sp.getString("email","");
        editor.putInt(emailForKey,0);
        editor.commit();
        editor.apply();


        recyclerView=view.findViewById(R.id.quizRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //processQuizData();
        getQuizData();

        return view;
    }



    private void processQuizData() {


        Call<List<ResponseModelQuiz>> call= ApiController.getInstance().getapi().getQuizData();
        call.enqueue(new Callback<List<ResponseModelQuiz>>() {
            @Override
            public void onResponse(Call<List<ResponseModelQuiz>> call, Response<List<ResponseModelQuiz>> response) {

                List<ResponseModelQuiz> data=response.body();
                //ResponseModelQuiz fetch=response.body();
                adapter=new QuizAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ResponseModelQuiz>> call, Throwable t) {
                //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getQuizData() {


        //displayLoader();
        Thread sendThread = new Thread() {

            public void run() {

                RequestHandler rh = new RequestHandler();
                HashMap<String, String> param = new HashMap<String, String>();
                //----------------------------------------------------------------


                //Populate the request parameters

                String result=rh.sendPostRequest(read_quiz_url, param);
                // Assuming 'result' is the JSON response string
                try {
                    JSONObject jsonResponse = new JSONObject(result);
                   // boolean status = jsonResponse.getBoolean("status");

                    if (isSuccess(result)) {
                        JSONArray dataArray = jsonResponse.getJSONArray("data");
                        List<ResponseModelQuiz> dataList = new ArrayList<>();

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject item = dataArray.getJSONObject(i);

                            /*String id = item.getString("id");
                            String title = item.getString("title");
                            String question = item.getString("question");
                            String option_1 = item.getString("option_1");
                            String option_2 = item.getString("option_2");
                            String option_3 = item.getString("option_3");
                            String option_4 = item.getString("option_4");
                            String correct_answer = item.getString("correct_answer");*/
                            Log.d("my_data",item.getString("id")+" "+item.getString("option_4")+" "+item.getString("correct_answer"));
                            ResponseModelQuiz model = new ResponseModelQuiz();
                            // Populate the model object with data from the JSON object
                            // Replace these with the actual column names from your database
                            model.setId(item.getString("id")); // Assuming "id" is a column in your table
                            model.setTitle(item.getString("title")); // Assuming "title" is a column in your table
                            model.setQuestion(item.getString("question")); // Assuming "title" is a column in your table
                            model.setOption_1(item.getString("option_1")); // Assuming "description" is a column in your table
                            model.setOption_2(item.getString("option_2")); // Assuming "image_url" is a column in your table
                            model.setOption_3(item.getString("option_3")); // Assuming "image_url" is a column in your table
                            model.setOption_4(item.getString("option_4")); // Assuming "image_url" is a column in your table
                            model.setCorrect_answer(item.getString("correct_answer")); // Assuming "image_url" is a column in your table
                            dataList.add(model);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here
                                // For example, update a TextView or RecyclerView
                                // Update the RecyclerView adapter on the main thread
// Update RecyclerView
                                adapter = new QuizAdapter(dataList);
                                recyclerView.setAdapter(adapter);
                            }
                        });


                    } else {
                        Log.e("API Error", "Status is false");
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
}