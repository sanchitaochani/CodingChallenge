package axxess.appdev.example.android.codingchallenge;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

class HttpClient {
    private static  String BASE_URL = "https://api.imgur.com/3/gallery/search/";
    private static HttpClient mInstance = new HttpClient();

    private Retrofit mRetrofit;
    private ImageService mService;

    public static HttpClient getInstance() {
        return mInstance;
    }

    private HttpClient() {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        mService = mRetrofit.create(ImageService.class);
    }

    private interface ImageService {
        @GET("1?client_id=137cda6b5008a7c")
        Call<ResponseBody> getImages(@Query("format") String format,
                                     @Query("q") String query);
    }

    public void fetchImages(String format, String query, Callback<ResponseBody> callback) {
        mService.getImages(format, query).enqueue(callback);
    }
}
