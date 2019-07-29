package axxess.appdev.example.android.codingchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        mImageView = findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        String title = intent.getStringExtra(ImageAdapter.MESSAGE_IMG_TITLE);
        setTitle(title);
        String url = intent.getStringExtra(ImageAdapter.MESSAGE_IMG_URL);
        Log.i("Second Activity", url);
        Picasso.get().load(url).into(mImageView);

    }
}
