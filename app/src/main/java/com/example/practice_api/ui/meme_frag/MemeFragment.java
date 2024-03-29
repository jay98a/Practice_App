package com.example.practice_api.ui.meme_frag;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.practice_api.MyVollySingleton;
import com.example.practice_api.R;
import com.example.practice_api.databinding.FragmentMemeBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MemeFragment extends Fragment {

    private FragmentMemeBinding binding;
    private ImageView imageView;
    private Button btn_next,btn_share;
    private ProgressBar progressBar;
    String image_url;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MemeViewModel memeViewModel =
                new ViewModelProvider(this).get(MemeViewModel.class);

        binding = FragmentMemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageView = (ImageView) root.findViewById(R.id.meme_view);
        progressBar = (ProgressBar) root.findViewById(R.id.meme_progressBar);

        btn_next = (Button) root.findViewById(R.id.meme_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_memeApi();
            }
        });

        btn_share = (Button) root.findViewById(R.id.meme_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openShareFun(); }
        });

        call_memeApi();
        return root;

    }

    private void openShareFun() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+image_url);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }

    private void call_memeApi() {

        //Progress Bar update
        progressBar.setVisibility(View.VISIBLE);

        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://meme-api.herokuapp.com/gimme";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: "+response);
                        try {
                            image_url = response.getString("url");
                            Glide.with(getContext()).load(image_url).into(imageView);
                            progressBar.setVisibility(View.INVISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        //queue.add(jsonObjectRequest);
        MyVollySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
