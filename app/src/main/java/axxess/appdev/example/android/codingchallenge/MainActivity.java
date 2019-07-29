package axxess.appdev.example.android.codingchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GridView mGridView;
    TextView mTextView;
    SearchView mSearchView;
    ImageAdapter mAdapter;
    ArrayList<Items> mItemsList = new ArrayList<>();
    private final int IMAGE_DIMENSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mAdapter = new ImageAdapter(this, mItemsList);
        mGridView.setAdapter(mAdapter);
        searchListener();

    }

    private void searchListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onSearch(newText);
                return false;
            }
        });
    }

    private void initViews() {
        mSearchView = findViewById(R.id.searchBar);
        mGridView = findViewById(R.id.gridView);
        mTextView = findViewById(R.id.errorMessage);
        //ensuring grid view behaves as it should
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels / displayMetrics.density;
        int columns = Math.round(width/IMAGE_DIMENSION);
        mGridView.setNumColumns(columns);
    }

    private void onSearch(String query) {
        //remove extra spaces
        query = query.trim();
        HttpClient.getInstance().fetchImages("json", query, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseString = response.body().string();
                    JSONObject data = new JSONObject(responseString);
                    JSONArray items = data.getJSONArray("data");
                    for (int i=0; i<items.length(); i++) {
                        JSONObject object = items.optJSONObject(i);
                        String id;
                        if(object.getBoolean("is_album")) {
                            id = object.getString("cover");
                        } else {
                            id = object.getString("id");
                        }
                        String title = object.getString("title");
                        mItemsList.add(new Items(id, title));
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //show no results page
                if (mItemsList.size()==0) {
                    mGridView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mGridView.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
            }
        });
    }

}
