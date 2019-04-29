package com.example.momsrecipes.MomContent;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.MimeTypeFilter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.momsrecipes.FireBaseAuth;
import com.example.momsrecipes.FirebaseAcitvity.LoginActivity;
import com.example.momsrecipes.FirebaseAcitvity.Utilssharedpre;
import com.example.momsrecipes.MainActivity;
import com.example.momsrecipes.Messag.Messaging;
import com.example.momsrecipes.R;
import com.example.momsrecipes.RecyclerViewAdapter.ItemObject;
import com.example.momsrecipes.RecyclerViewAdapter.RecyclerViewAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Sliding_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout dl;
    private static final int pick_image_request = 1;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ImageView imageViewdra;
    private Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    private LinearLayoutManager lLayout;
    private static ViewPager mPager;
    TabLayout tabLayout;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private Uri mImageUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    private static final Integer[] IMAGES= {R.drawable.khana1,R.drawable.khana2,R.drawable.khana3,R.drawable.khana4,R.drawable.khana5,R.drawable.khana6,R.drawable.khana7};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingradients_navigation);
        mImageUri = null;
        init();
        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(Sliding_activity.this);
        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        tabLayout = findViewById(R.id.dots);
        floatingActionButton = findViewById(R.id.fab);

        toolbar = findViewById(R.id.toolbar1);
        storageReference = FirebaseStorage.getInstance().getReference().child("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Images");

         dl = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logomyrecipe);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.threeline);
        nv = findViewById(R.id.nav_view1);
        View headerview = nv.getHeaderView(0);
        imageViewdra = headerview.findViewById(R.id.image_drawer);
        nv.setNavigationItemSelectedListener(this);
        rView.setLayoutManager(lLayout);

        tabLayout.setupWithViewPager(mPager,true);
        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(Sliding_activity.this, rowListItem);
        rView.setAdapter(rcAdapter);

      /*  imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfilechooser();
            }
        });*/

      //  SharedPreferences sp  = getApplicationContext().getSharedPreferences("name",0);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sliding_activity.this, Messaging.class));
            }
        });
    }


    public String getFileExtension(Uri uri)
    {
        ContentResolver cp = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cp.getType(uri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== pick_image_request && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
           mImageUri = data.getData();
        //   Picasso.with(Sliding_activity.this).load(mImageUri).centerCrop().resize(100,100).into(imageViewdra);
           StorageReference  reference= storageReference.child("Image" + ".jpg");
           reference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                 final String downloadurl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                   Map<String,String> map  = new HashMap<>();
                  map.put("image",downloadurl);
                 databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {

                         if(task.isSuccessful())
                         {
                             Picasso.with(Sliding_activity.this).load(mImageUri).resize(100,100).transform( new CircleTransform()).centerCrop().
                         into(imageViewdra);
                         }
                     }
                 });
               }
           });
        }
    }

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("Salad", R.drawable.saled));
        allItems.add(new ItemObject("Daily Dishes (Lunch&Dinner)", R.drawable.dal));
        allItems.add(new ItemObject("Weekly Dishes(Breakfast)", R.drawable.poha));
        allItems.add(new ItemObject("Monthly Dishes(Fast Food)", R.drawable.pizza));
        allItems.add(new ItemObject("Sweetsss", R.drawable.icecreame));

        return allItems;
    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(Sliding_activity.this,ImagesArray));


        NUM_PAGES =IMAGES.length;


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 4000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signoutmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_Signout) {
            Utilssharedpre.saveSharedSetting(Sliding_activity.this,"myclip","true");
            Utilssharedpre.shareprefsave(getApplicationContext(),"");
            Intent logout = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(logout);
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(Sliding_activity.this, MainActivity.class));
        }
        else if(menuItem.getItemId()==R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "I'm Using this ticket booking app.I'm Suggesting you to download this awesome app https://www.google.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Download this awesome app");
            startActivity(Intent.createChooser(intent, "Choose among this to share"));
        }
        else if(menuItem.getItemId()==R.id.nav_gallery)
        {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("IMAGE//");
          startActivityForResult(Intent.createChooser(intent,"select image"),pick_image_request );
            if(mImageUri != null)
            {
                StorageReference filestorage = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
                filestorage.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String  uploadfile = databaseReference.push().getKey();
                        databaseReference.child(uploadfile).setValue(uploadfile);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
            }
            else {
                Toast.makeText(this, "No file Selected", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
    @Override
    public void onBackPressed() {

        if (dl.isDrawerOpen(GravityCompat.START)) {

            dl.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder=new AlertDialog.Builder(Sliding_activity.this).setTitle("Do you Want to Exit");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("No",null);
            builder.show();

        }

    }

}