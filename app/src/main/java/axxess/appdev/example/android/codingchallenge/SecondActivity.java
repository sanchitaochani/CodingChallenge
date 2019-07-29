package axxess.appdev.example.android.codingchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
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
        //set title of the activity
        String title = intent.getStringExtra(ImageAdapter.MESSAGE_IMG_TITLE);
        setTitle(title);

        //load image
        String url = intent.getStringExtra(ImageAdapter.MESSAGE_IMG_URL);
        Picasso.get().load(url).into(mImageView);

    }

    //up navigation should work like the back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
