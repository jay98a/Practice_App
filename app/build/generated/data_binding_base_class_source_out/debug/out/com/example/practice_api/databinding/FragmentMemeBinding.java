// Generated by view binder compiler. Do not edit!
package com.example.practice_api.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.practice_api.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMemeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Button memeNext;

  @NonNull
  public final Button memeShare;

  @NonNull
  public final ImageView memeView;

  private FragmentMemeBinding(@NonNull ConstraintLayout rootView, @NonNull Guideline guideline2,
      @NonNull Button memeNext, @NonNull Button memeShare, @NonNull ImageView memeView) {
    this.rootView = rootView;
    this.guideline2 = guideline2;
    this.memeNext = memeNext;
    this.memeShare = memeShare;
    this.memeView = memeView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMemeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMemeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_meme, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMemeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.meme_next;
      Button memeNext = ViewBindings.findChildViewById(rootView, id);
      if (memeNext == null) {
        break missingId;
      }

      id = R.id.meme_share;
      Button memeShare = ViewBindings.findChildViewById(rootView, id);
      if (memeShare == null) {
        break missingId;
      }

      id = R.id.meme_view;
      ImageView memeView = ViewBindings.findChildViewById(rootView, id);
      if (memeView == null) {
        break missingId;
      }

      return new FragmentMemeBinding((ConstraintLayout) rootView, guideline2, memeNext, memeShare,
          memeView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}