package com.xlibrarykr.eduorigin.featurefragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xlibrarykr.eduorigin.HttpRequest;
import com.xlibrarykr.eduorigin.R;
import com.xlibrarykr.eduorigin.RequestHandler;
import com.xlibrarykr.eduorigin.adapters.BookLibraryAdapter;
import com.xlibrarykr.eduorigin.controllers.ApiController;
import com.xlibrarykr.eduorigin.models.ResponseModelBookLibraryBackup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookLibraryFragment extends Fragment {


    private ExecutorService executor;
    private Handler handler ;
    private final int jsoncode = 1;
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
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        recyclerView=view.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
       // processData();
      //  dataProcess();
       getLibraryData();
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

        // Process the response on the main thread
//   runOnUiThread(new Runnable() {
//  });
        Thread sendThread = new Thread(() -> {
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

                    // Parse the JSON array into a list of ResponseModelBookLibraryBackup objects
                    List<ResponseModelBookLibraryBackup> data = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        ResponseModelBookLibraryBackup model = new ResponseModelBookLibraryBackup();
                        // Create a new ResponseModelBookLibraryBackup object
                        Log.d("result123",item.getString("id")+" "+item.getString("name")+" "+item.getString("description")+" "+item.getString("image")+" "+item.getString("pdf"));

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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI here
                            // For example, update a TextView or RecyclerView
                            // Update the RecyclerView adapter on the main thread
                            adapter = new BookLibraryAdapter(data);
                            recyclerView.setAdapter(adapter);
                        }
                    });


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
            });

        // Start the thread
        sendThread.start();
    }
    protected void getLibraryData() {
        // Show progress bar if necessary
        // progbar.setVisibility(View.VISIBLE);
        // showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);

        executor.execute(() -> {
            String response = "";
            HashMap<String, String> map = new HashMap<>();

            try {
                HttpRequest req = new HttpRequest(read_url);
                // Populate the request parameters
               // map.put("title", title);
               // map.put("question", question);

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

                    try {
                        // Create a RequestHandler instance
                        RequestHandler rh = new RequestHandler();

                        // Define the request parameters (if any)
                        HashMap<String, String> param = new HashMap<>();
                        // Example: param.put("key", "value");
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray dataArray = jsonResponse.getJSONArray("data");
                        // Send the POST request and get the response
                        String result = rh.sendPostRequest(read_url, param);
                        Log.d("result123",result);
                        // Create a JSONArray from the response string
                       // JSONArray jsonArray = new JSONArray(result);

                        // Parse the JSON array into a list of ResponseModelBookLibraryBackup objects
                        List<ResponseModelBookLibraryBackup> data = new ArrayList<>();

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject item = dataArray.getJSONObject(i);
                            ResponseModelBookLibraryBackup model = new ResponseModelBookLibraryBackup();
                            // Create a new ResponseModelBookLibraryBackup object
                            Log.d("result123",item.getString("id")+" "+item.getString("name")+" "+item.getString("description")+" "+item.getString("image")+" "+item.getString("pdf"));

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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here
                                // For example, update a TextView or RecyclerView
                                // Update the RecyclerView adapter on the main thread
                                adapter = new BookLibraryAdapter(data);
                                recyclerView.setAdapter(adapter);
                            }
                        });


                    } catch (JSONException e) {
                        // Handle JSON parsing errors
                        e.printStackTrace();
                        //  Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
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

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI here
                            // For example, update a TextView or RecyclerView
                            // Update the RecyclerView adapter on the main thread

                        }
                    });


                }
        }
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