package com.example.practice_api.ui.meme_frag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practice_api.MainActivity;
import com.example.practice_api.databinding.FragmentMemeBinding;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MemeFragment extends Fragment {

    private FragmentMemeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MemeViewModel memeViewModel =
                new ViewModelProvider(this).get(MemeViewModel.class);

        binding = FragmentMemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        callMeme_Api();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void callMeme_Api() {

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(getContext());
        CronetEngine cronetEngine = myBuilder.build();

        Executor executor = Executors.newSingleThreadExecutor();
        MyCallback callback = new MyCallback();
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(
                "https://meme-api.herokuapp.com/gimme", callback, executor);
        UrlRequest request = requestBuilder.build();
        request.start();

    }

}

class MyCallback extends UrlRequest.Callback {
    private static final String TAG = "MyUrlRequestCallback";

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        Log.i(TAG, "onRedirectReceived method called.");
        // You should call the request.followRedirect() method to continue
        // processing the request.
        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        int httpStatusCode = info.getHttpStatusCode();
        if (httpStatusCode == 200) {
            // The request was fulfilled. Start reading the response.
            request.read(ByteBuffer.allocateDirect(102400));
        } else if (httpStatusCode == 503) {
            // The service is unavailable. You should still check if the request
            // contains some data.
            request.read(ByteBuffer.allocateDirect(102400));
        }
        //responseHeaders = info.getAllHeaders();
    }

    @Override
    public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
//        Log.i(TAG, "onReadCompleted method called.");
//        // You should keep reading the request until there's no more data.
//        byteBuffer.clear();
//        request.read(byteBuffer);

        Log.i(TAG, "onReadCompleted method called.");
        // You should keep reading the request until there's no more data.
        request.read(byteBuffer);

        int statusCode = info.getHttpStatusCode();
        this.httpStatusCode = statusCode;

        byte[] bytes;
        if (byteBuffer.hasArray()) {
            bytes = byteBuffer.array();
        } else {
            bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
        }

        String responseBodyString = new String(bytes); //Convert bytes to string

        //Properly format the response String
        responseBodyString = responseBodyString.trim().replaceAll("(\r\n|\n\r|\r|\n|\r0|\n0)", "");
        if (responseBodyString.endsWith("0")) {
            responseBodyString = responseBodyString.substring(0, responseBodyString.length()-1);
        }

        this.responseBody = responseBodyString;

        Map<String, List<String>> headers = info.getAllHeaders(); //get headers

        String reqHeaders = createHeaders(headers);

        JSONObject results = new JSONObject();
        try {
            results.put("headers", reqHeaders);
            results.put("body", responseBodyString);
            results.put("statusCode", statusCode);
        } catch (JSONException e ) {
            e.printStackTrace();
        }

        //Send to OnFinishRequest which we will override in activity to read results gotten.
        delegate.onFinishRequest(results);

    }

    @Override
    public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
        Log.i(TAG, "onSucceeded method called.");
    }

    @Override
    public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
        // Request has failed. responseInfo might be null.
        Log.e("MyCallback", "Request failed. " + error.getMessage());
        // Maybe handle error here. Typical errors include hostname
        // not resolved, connection to server refused, etc.
    }
}
