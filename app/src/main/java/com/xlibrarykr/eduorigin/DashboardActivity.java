package com.xlibrarykr.eduorigin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.xlibrarykr.eduorigin.adapters.BookLibraryAdapter;
import com.xlibrarykr.eduorigin.featurefragments.BookLibraryFragment;
import com.xlibrarykr.eduorigin.featurefragments.OnlineQuizFragment;
import com.xlibrarykr.eduorigin.learningresourcefragments.GeeksForGeeksFragment;
import com.xlibrarykr.eduorigin.learningresourcefragments.JavaTPointFragment;
import com.xlibrarykr.eduorigin.learningresourcefragments.TutorialsPointFragment;
import com.xlibrarykr.eduorigin.learningresourcefragments.W3SchoolFragment;
import com.xlibrarykr.eduorigin.models.ResponseModelBookLibraryBackup;
import com.xlibrarykr.eduorigin.registration.SignInActivity;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {


    private NavigationView navigationView,navigationView2;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toogle;
    private TextView navHeaderEmail;
    private String output;
    private View headerView;
    private AdView adView;
    private String delete_url = "https://timxn.com/ecom/EduOriginAPI/Registration/delete.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        String output=sp.getString("email","");
        FrameLayout adContainerView = findViewById(R.id.ad_view_container);


        navigationView2 = (NavigationView) findViewById(R.id.navigationViewId );
        headerView = navigationView2.getHeaderView(0);
        navHeaderEmail = (TextView) headerView.findViewById(R.id.navHeaderEmailId);
        navHeaderEmail.setText(output);

        // Create a new ad view.
        adView = new AdView(this);
      //  adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");// test unit id
        adView.setAdUnitId("ca-app-pub-8127887406337874/9794083203");
       // Request an anchored adaptive banner with a width of 360.
        adView.setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, 360));
        adContainerView.removeAllViews();
        adContainerView.addView(adView);
       // Replace ad container with new ad view.
        //adContainerView.removeAllViews();
       // adContainerView.addView(adView);
        // Request an anchored adaptive banner with a width of 360.

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});
                })
                .start();


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // userExistenceCheck();




//        signoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
//                SharedPreferences.Editor editor=sp.edit();
//                editor.remove("email");
//                editor.remove("password");
//                editor.commit();
//                editor.apply();
//
//                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
//                finish();
//            }
//        });


        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.navigationViewId);
        drawerLayout=findViewById(R.id.drawerId);

        toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);

        toogle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,new BookLibraryFragment()).commit();
        getSupportActionBar().setTitle("Book Library");
        navigationView.setCheckedItem(R.id.menu_bookLibrary);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            Fragment store;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId())
                {

                    case R.id.menu_logout:

                        onLogOut();
                        break;

                    case R.id.delete_account:

                        deleteDialog();
                        break;


                    case R.id.menu_bookLibrary:

                        store=new BookLibraryFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.menu_quiz:

                        store=new OnlineQuizFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;



                    case R.id.menu_w3School:
                        store=new W3SchoolFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                    case R.id.menu_geeksForGeeks:
                        store=new GeeksForGeeksFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                    case R.id.menu_tutorialPoint:
                        store=new TutorialsPointFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.menu_javaTPoint:
                        store=new JavaTPointFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                }

                return true;


            }
        });


    }

    private void onLogOut()
    {
        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("email");
        editor.remove("password");
        editor.remove("result");
        //editor.remove("jishanc@gmail.com");
        //editor.remove("hero@gmail.com");
        editor.commit();
        editor.apply();

        startActivity(new Intent(DashboardActivity.this, SignInActivity.class));
        finish();
    }
    private void deleteDialog()
    {
        showDeleteAccountDialog();
    }

    private void showDeleteAccountDialog() {
        // Create an AlertDialog.Builder instance
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message for the dialog
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure you want to delete your account? This action cannot be undone.");

        // Set the positive button (e.g., "Delete")
        
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform the delete account action here
                deleteAccount();
            }
        });

        // Set the negative button (e.g., "Cancel")
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteAccount() {
        // Implement the logic to delete the account
  
            Thread sendThread = new Thread(() -> {
                try {
                    // Create a RequestHandler instance
                    RequestHandler rh = new RequestHandler();
                    SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
                    String email=sp.getString("email","");
                    String password=sp.getString("password","");
                    // Define the request parameters (if any)
                    HashMap<String, String> param = new HashMap<>();
                    param.put("email", email);
                    param.put("password", password);


                    // Send the POST request and get the response
                    String result = rh.sendPostRequest(delete_url, param);
                    Log.d("result123",result);
         
               runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isSuccess(result)) {
                                // Update UI here
                                Toast.makeText(getApplicationContext(), "Account deleted", Toast.LENGTH_SHORT).show();
                                SharedPreferences sp = getSharedPreferences("credential", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.remove("email");
                                editor.remove("password");
                                editor.remove("result");
                                //editor.remove("jishanc@gmail.com");
                                //editor.remove("hero@gmail.com");
                                editor.apply();
                                startActivity(new Intent(DashboardActivity.this, SignInActivity.class));
                                finish();
                            }
                        }
                    });


                } catch (Exception e) {
                    // Handle other errors
                    e.printStackTrace();
                    //  Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            });

            // Start the thread
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
    // private void  userExistenceCheck()
   //  {
       //  SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
       //  if(sp.contains("email"))
       //  {
             //works like navigation drawer username appears when user is login
             //responseWarningDashboard.setText(sp.getString("email",""));
             //navHeaderEmail.setText("out");
//             output=getIntent().getStringExtra("emailKey");
//             navigationView2 = (NavigationView) findViewById(R.id.navigationViewId );
//             headerView = navigationView2.getHeaderView(0);
//             navHeaderEmail = (TextView) headerView.findViewById(R.id.navHeaderEmailId);
              //navHeaderEmail.setText(output);

        // }
         //else{
         //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        // }



 //   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//         getMenuInflater().inflate(R.menu.search_menu,menu);
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
//               // adapter.getFilter().filter(newText);
//               // BookLibraryFragment bf=new BookLibraryFragment();
//               // bf.returnString(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//   }
}