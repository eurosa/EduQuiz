package com.example.eduorigin.featurefragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduorigin.R;
import com.example.eduorigin.RequestHandler;
import com.example.eduorigin.adapters.BookLibraryAdapter;
import com.example.eduorigin.controllers.ApiController;
import com.example.eduorigin.models.ResponseModelBookLibraryBackup;
import com.example.eduorigin.registration.SignInActivity;
import com.example.eduorigin.registration.SignUpActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookLibraryFragment extends Fragment {




    private String read_url = "https://timxn.com/ecom/EduOriginAPI/Registration/read.php";
    private RecyclerView recyclerView;
    private BookLibraryAdapter adapter;
    Context context;
    String receive;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book_library, container, false);

        recyclerView=view.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        processData();
        //dataProcess();

        SearchView sv;
        sv=view.findViewById(R.id.searchViewId);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        return view;
    }



    private void processData() {

        Call<List<ResponseModelBookLibraryBackup>> call= ApiController.getInstance().getapi().getBookLibraryData();
        call.enqueue(new Callback<List<ResponseModelBookLibraryBackup>>() {
            @Override
            public void onResponse(Call<List<ResponseModelBookLibraryBackup>> call, Response<List<ResponseModelBookLibraryBackup>> response) {
                List<ResponseModelBookLibraryBackup> data=response.body();
                adapter=new BookLibraryAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ResponseModelBookLibraryBackup>> call, Throwable t) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void dataProcess() {
        // Display a loader (if needed)
        // displayLoader();

        Thread sendThread = new Thread(new Runnable() {



                // Process the response on the main thread
             //   runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Create a RequestHandler instance
                            RequestHandler rh = new RequestHandler();

                            // Define the request parameters (if any)
                            HashMap<String, String> param = new HashMap<>();
                            // Example: param.put("key", "value");

                            // Send the POST request and get the response
                            String result = rh.sendPostRequest(read_url, param);
                            Log.d("result123",result);
                            // Create a JSONArray from the response string
                            JSONArray jsonArray = new JSONArray(result);
                            ResponseModelBookLibraryBackup model = new ResponseModelBookLibraryBackup();
                            // Parse the JSON array into a list of ResponseModelBookLibraryBackup objects
                            List<ResponseModelBookLibraryBackup> data = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);

                                // Create a new ResponseModelBookLibraryBackup object


                                // Populate the model object with data from the JSON object
                                // Replace these with the actual column names from your database
                                model.setId(item.getString("id")); // Assuming "id" is a column in your table
                                model.setName(item.getString("name")); // Assuming "title" is a column in your table
                                model.setDescription(item.getString("description")); // Assuming "description" is a column in your table
                                model.setImage(item.getString("image")); // Assuming "image_url" is a column in your table
                                model.setPdf(item.getString("pdf")); // Assuming "image_url" is a column in your table

                                // Add the model to the list
                                data.add(model);
                            }

                            // Update the RecyclerView adapter on the main thread
                            adapter = new BookLibraryAdapter(data);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            // Handle JSON parsing errors
                            e.printStackTrace();
                          //  Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            // Handle other errors
                            e.printStackTrace();
                          //  Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show();
                        } finally {
                            // Dismiss the loader (if used)
                            // pDialog.dismiss();
                        }
                    }
              //  });

        });

        // Start the thread
        sendThread.start();
    }


//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//
//        inflater.inflate(R.menu.search_menu,menu);
//        MenuItem menuItem=menu.findItem(R.id.search_from_menuId);
//        SearchView searchView= (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//       // return super.onCreateOptionsMenu(menu,inflater);
//    }
}