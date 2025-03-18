package com.xlibrarykr.eduorigin.adminpanel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xlibrarykr.eduorigin.HttpRequest;
import com.xlibrarykr.eduorigin.R;
import com.xlibrarykr.eduorigin.RequestHandler;
import com.xlibrarykr.eduorigin.controllers.ApiController;
import com.xlibrarykr.eduorigin.models.ResponseModelBookLibraryBackup;
import com.xlibrarykr.eduorigin.registration.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPanelBookUploadActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private final int jsoncode = 1;
    private ExecutorService executor;
    private Handler handler ;
    private String sendBookUrl = "https://timxn.com/ecom/EduOriginAPI/Registration/uploadbooksbackup.php";
    private Button adminLogOut;
    private TextView adminResponseWarning;
    private Toolbar toolbar;
    private Bitmap bitmap;
    private static final int REQUEST_CODE_IMAGE_PERMISSION = 1001;
    private EditText adminBookName,adminBookDescription,adminBookCategory;
    private Button adminBrowseImageButton,adminUploadImageButton;
    private ImageView adminBookImage;
    private String encodeImageString,encodePdfString,pdfNameExtract;
    private Button adminBrowsePdfButton;
    private TextView adminPDFFileSelectionShowText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setTitle("Admin Panel");
        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_admin_panel_book_upload);

        bottomNavigationView = findViewById(R.id.bottomNavigationId);
        //frameLayout = findViewById(R.id.frameLayoutContainerId);
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        adminBookCategory=findViewById(R.id.adminBookCategoryId);
        adminBookName=findViewById(R.id.adminBookNameId);
        adminBookDescription=findViewById(R.id.adminBookDescriptionId);
        adminBookImage=findViewById(R.id.adminBookImageId);
        adminBrowseImageButton=findViewById(R.id.adminBrowseImageId);
        adminUploadImageButton=findViewById(R.id.adminBookUploadId);
        adminBrowsePdfButton=findViewById(R.id.adminBookPdfBrowseId);
        adminPDFFileSelectionShowText=findViewById(R.id.adminPDFFileSelectionShowTextId);




        bottomNavigationView.setSelectedItemId(R.id.menu_bookUploadAdmin);
        //Fragment store = null;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
//                    case R.id.menu_bookUploadAdmin:
//                        // store = new AdminBookUploadFragment();
//                        //  getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, store).commit();
//                        break;


                case R.id.menu_quizCreationAdmin:
                    // store = new AdminQuizCreationFragment();
                    // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, store).commit();

                    startActivity(new Intent(getApplicationContext(), AdminPanelCreateQuizActivity.class));
                    break;

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

        });



        // userExistenceCheck();

        adminBrowseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // For Android 13+, use READ_MEDIA_IMAGES
                    if (ContextCompat.checkSelfPermission(AdminPanelBookUploadActivity.this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AdminPanelBookUploadActivity.this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_CODE_IMAGE_PERMISSION);
                    } else {
                        openImagePicker();
                    }
                } else {
                    // For Android 10-12, use READ_EXTERNAL_STORAGE
                    Dexter.withActivity(AdminPanelBookUploadActivity.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                    openImagePicker();
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                    Toast.makeText(AdminPanelBookUploadActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();
                }
            }
        });



       /* adminBrowseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(AdminPanelBookUploadActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });*/


        adminBrowsePdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // For Android 13+, use the new READ_MEDIA permissions (if needed)
                    // Note: For picking files, you don't need explicit permissions; SAF handles it.
                    openPdfPicker();
                } else {
                    // For Android 10-12, use Dexter to request READ_EXTERNAL_STORAGE (if needed)
                    Dexter.withActivity(AdminPanelBookUploadActivity.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                    openPdfPicker();
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                    // Handle permission denial
                                    Toast.makeText(AdminPanelBookUploadActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();
                }
            }
        });



       /* adminBrowsePdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(AdminPanelBookUploadActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                               // Intent intent=new Intent(Intent.ACTION_PICK);
                                //intent.setType("pdf/*");
                                intent.setType("application/pdf");
                                //startActivityForResult(Intent.createChooser(intent,"Browse PDF"),2);
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("application/pdf");
                                pdfPickerLauncher.launch(Intent.createChooser(intent, "Browse PDF"));
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });*/



        adminUploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    if(!adminBookDescription.getText().toString().isEmpty() && !adminBookName
                            .getText().toString().isEmpty() && !encodeImageString.isEmpty() && !pdfNameExtract.isEmpty()) {
                        uploadDataToDB();
                        // uploadLibraryData();

                    }else {
                        Toast.makeText(AdminPanelBookUploadActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e)
                {
                    //Log.d("excep",e.toString());
                    Toast.makeText(AdminPanelBookUploadActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();

                }



            }


        });








    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_IMAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri filepath=data.getData();
            try{

                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                adminBookImage.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);

            }catch (Exception e)
            {

            }
        }


        if(requestCode==2 && resultCode==RESULT_OK)
        {

            Uri filepath=data.getData();
            try{

//                InputStream inputStream=getContentResolver().openInputStream(filepath);
//                bitmap= BitmapFactory.decodeStream(inputStream);
//                adminBookImage.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);
                  InputStream inputStream= AdminPanelBookUploadActivity.this.getContentResolver().openInputStream(filepath);
                  byte[] pdfInBytes=new byte[inputStream.available()];
                  inputStream.read(pdfInBytes);
                  encodePdfString=Base64.encodeToString(pdfInBytes,Base64.DEFAULT);

                  String pdfName=filepath.toString();
                  pdfNameExtract=pdfName.substring(pdfName.lastIndexOf("/")+1);
                  adminPDFFileSelectionShowText.setText(pdfNameExtract);

            }catch (Exception e)
            {

            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }

  /*  ActivityResultLauncher<Intent> pdfPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedPdf = result.getData().getData();
                    if (selectedPdf != null) {
                        // Handle the selected PDF Uri
                        String filePath = selectedPdf.toString();
                        Log.d("PDF_PATH", "Selected PDF Path: " + filePath);
                        try{

//                InputStream inputStream=getContentResolver().openInputStream(filepath);
//                bitmap= BitmapFactory.decodeStream(inputStream);
//                adminBookImage.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);
                            InputStream inputStream= AdminPanelBookUploadActivity.this.getContentResolver().openInputStream(Uri.parse(filePath));
                            byte[] pdfInBytes=new byte[inputStream.available()];
                            inputStream.read(pdfInBytes);
                            encodePdfString=Base64.encodeToString(pdfInBytes,Base64.DEFAULT);

                            String pdfName=filePath.toString();
                            pdfNameExtract=pdfName.substring(pdfName.lastIndexOf("/")+1);
                            adminPDFFileSelectionShowText.setText(pdfNameExtract);

                        }catch (Exception e)
                        {

                        }
                    }
                }
            }
    );*/


    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofImage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofImage, Base64.DEFAULT);

    }

    // private void  userExistenceCheck()
    //{
    //  SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
    // if(sp.contains("email"))
    //  {
    //works like navigation drawer username appears when user is login
    //adminResponseWarning.setText(sp.getString("email",""));
    //startActivity(new Intent(getApplicationContext(),));
    //   }
    //else{
    //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
    // }
    //}







    private void uploadDataToDBA() {

        String name=adminBookName.getText().toString().trim();
        String description=adminBookDescription.getText().toString().trim();
        String image=encodeImageString.trim();
        String pdf=encodePdfString.trim();

        Call<ResponseModelBookLibraryBackup> call= ApiController.getInstance().getapi().sendBookDataFromAdminBackup(name,description,image,pdf);

        call.enqueue(new Callback<ResponseModelBookLibraryBackup>() {
            @Override
            public void onResponse(Call<ResponseModelBookLibraryBackup> call, Response<ResponseModelBookLibraryBackup> response) {

                Toast.makeText(getApplicationContext(), "Book uploaded successfully", Toast.LENGTH_SHORT).show();

                adminBookName.setFocusableInTouchMode(true);
                adminBookDescription.setFocusable(false);
                adminBookDescription.setFocusableInTouchMode(true);
                adminBookName.setText("");
                adminBookDescription.setText("");
                adminBookImage.setImageBitmap(null);
                adminBookImage.setImageResource(R.drawable.ic_upload_photo);
                adminPDFFileSelectionShowText.setText("");

            }

            @Override
            public void onFailure(Call<ResponseModelBookLibraryBackup> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                adminBookName.setFocusableInTouchMode(true);
                adminBookDescription.setFocusable(false);
                adminBookDescription.setFocusableInTouchMode(true);
                adminBookName.setText("");
                adminBookDescription.setText("");
                adminBookImage.setImageBitmap(null);
                adminBookImage.setImageResource(R.drawable.ic_upload_photo);
                adminPDFFileSelectionShowText.setText("");

                Toast.makeText(getApplicationContext(), "Book uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void uploadDataToDB() {


        //displayLoader();
        Thread sendThread = new Thread() {

            public void run() {

                RequestHandler rh = new RequestHandler();
                HashMap<String, String> param = new HashMap<String, String>();
                //----------------------------------------------------------------
                String name=adminBookName.getText().toString().trim();
                String description=adminBookDescription.getText().toString().trim();
                String image=encodeImageString.trim();
                String pdf=encodePdfString.trim();

                //Populate the request parameters
                param.put("name", name);
                param.put("description", description);
                param.put("image", image);
                param.put("pdf", pdf);
                String result=rh.sendPostRequest(sendBookUrl, param);
                try {
                    // Create a JSONObject from the JSON string
                    JSONObject jsonObject = new JSONObject(result);

                    // Extract the value associated with the key "message"
                    String output = jsonObject.getString("status");

                    // Print the value
                    System.out.println("Message: " + output); // Output: Message: exist
                    if(output.equals("true"))
                    {

                        runOnUiThread(() -> {

                            // Stuff that updates the UI
                            Toast.makeText(AdminPanelBookUploadActivity.this, "Book uploaded successfully", Toast.LENGTH_SHORT).show();
                        });
                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        finish();
                    }
                    if(output.equals("false"))
                        runOnUiThread(() -> {

                            // Stuff that updates the UI
                            Toast.makeText(AdminPanelBookUploadActivity.this, "Book upload failed", Toast.LENGTH_SHORT).show();
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

    protected void uploadLibraryData() {
        // Show progress bar if necessary
        // progbar.setVisibility(View.VISIBLE);
        // showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);

        executor.execute(() -> {
            String response = "";
            HashMap<String, String> map = new HashMap<>();

            try {
                HttpRequest req = new HttpRequest(sendBookUrl);
                // Populate the request parameters
                String name=adminBookName.getText().toString().trim();
                String description=adminBookDescription.getText().toString().trim();
                String image=encodeImageString.trim();
                String pdf=encodePdfString.trim();

                //Populate the request parameters
                map.put("name", name);
                map.put("description", description);
                map.put("image", image);
                map.put("pdf", pdf);

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


                            Toast.makeText(AdminPanelBookUploadActivity.this, "Book uploaded successfully", Toast.LENGTH_SHORT).show();

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

                            Toast.makeText(AdminPanelBookUploadActivity.this, "Book upload failed", Toast.LENGTH_SHORT).show();
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

    private void openPdfPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        pdfPickerLauncher.launch(Intent.createChooser(intent, "Browse PDF"));
    }
    // ActivityResultLauncher for handling the PDF picker result
    private final ActivityResultLauncher<Intent> pdfPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri pdfUri = result.getData().getData();
                        if (pdfUri != null) {
                            // Handle the selected PDF file (e.g., upload or process it)
                            handlePdfFile(pdfUri);
                        }
                    }
                }
            }
    );

    // Method to handle the selected PDF file
    private void handlePdfFile(Uri pdfUri) {
        // Example: Get the file path or process the PDF
        String filePath = getFilePathFromUri(pdfUri);
        if (filePath != null) {
            // Do something with the file path
            Toast.makeText(this, "Selected PDF: " + filePath, Toast.LENGTH_SHORT).show();
            try{

//                InputStream inputStream=getContentResolver().openInputStream(filepath);
//                bitmap= BitmapFactory.decodeStream(inputStream);
//                adminBookImage.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);
                InputStream inputStream= AdminPanelBookUploadActivity.this.getContentResolver().openInputStream(pdfUri);
                byte[] pdfInBytes=new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                encodePdfString=Base64.encodeToString(pdfInBytes,Base64.DEFAULT);

                String pdfName=filePath.toString();
                pdfNameExtract=pdfName.substring(pdfName.lastIndexOf("/")+1);
                adminPDFFileSelectionShowText.setText(pdfNameExtract);

            }catch (Exception e)
            {

            }
        } else {
            Toast.makeText(this, "Failed to get file path", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to get the file path from the URI
    private String getFilePathFromUri(Uri uri) {
        String filePath = null;
        if (uri != null) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (displayNameIndex != -1) {
                        String displayName = cursor.getString(displayNameIndex);
                        filePath = getCacheDir() + "/" + displayName; // Save to cache or internal storage
                        try (InputStream inputStream = getContentResolver().openInputStream(uri);
                             FileOutputStream outputStream = new FileOutputStream(filePath)) {
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, length);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    // Method to open the image picker
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagePickerLauncher.launch(Intent.createChooser(intent, "Browse Image"));
    }

    // ActivityResultLauncher for handling the image picker result
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        if (imageUri != null) {
                            // Handle the selected image (e.g., display or upload it)
                            handleImageFile(imageUri);
                        }
                    }
                }
            }
    );
    // Method to handle the selected image file
    private void handleImageFile(Uri imageUri) {
        // Example: Get the file path or process the image
        String filePath = getFilePathFromUri(imageUri);
        if (filePath != null) {
            // Do something with the file path

            try{

                InputStream inputStream=getContentResolver().openInputStream(imageUri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                adminBookImage.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);

            }catch (Exception e)
            {

            }
            Toast.makeText(this, "Selected Image: " + filePath, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to get file path", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to get the file path from the URI

}