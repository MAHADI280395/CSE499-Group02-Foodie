package com.cse486.ece.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.lang.ref.Reference;

public class chef_work_station extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageview;
    private Button uploadButton, saveButton, showButton;
    public Uri imageUri;
    private EditText itemdetails, price;
    private ProgressBar progressbar;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask storageTask;

    private static final int IMAGE_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_work_station);

        databaseReference = FirebaseDatabase.getInstance().getReference("upload");
        storageReference = FirebaseStorage.getInstance().getReference("upload");

        imageview = findViewById(R.id.image1);
        uploadButton = findViewById(R.id.uploadItem);
        saveButton = findViewById(R.id.saveItem);
        showButton = findViewById(R.id.showItem);
        itemdetails = findViewById(R.id.itemDetails);
        price = findViewById(R.id.price);
        progressbar = findViewById(R.id.progressbar);

        uploadButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadItem:
                openFileChooser();
            break;
            case R.id.saveItem:
                if(storageTask!=null && storageTask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"Image is uploading", Toast.LENGTH_SHORT).show();
                }
                else{

                    saveData();
                }

            break;
            case R.id.showItem:
                Intent intent = new Intent(chef_work_station.this, ChefItemViewer.class);
                startActivity(intent);
                break;
        }
    }


    void openFileChooser(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(imageview);
        }
    }

    //getting database image extension
    private String getFileExtension(Uri imagUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap =MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imagUri));
    }

    private void saveData() {

        String fooditemdetails = itemdetails.getText().toString().trim();
        String fooditemprice = price.getText().toString().trim();

        if (fooditemdetails.isEmpty()){
            itemdetails.setError("Enter Food Detais!");
            itemdetails.requestFocus();
            return;
        }
        if(fooditemprice.isEmpty()){
            price.setError("Set a price");
            price.requestFocus();
            return;
        }

        StorageReference reference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

        reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Image is stored successfully!",Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isSuccessful());

                Uri downloadUri = uriTask.getResult();




                upload up = new upload(fooditemdetails, fooditemprice, downloadUri.toString());

                String uploadId = databaseReference.push().getKey();
                databaseReference.child(uploadId).setValue(up);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Image is not stored successfully!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}