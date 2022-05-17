package com.normurodov_nazar.ordinarydifferentialequations.Sources;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;

import com.normurodov_nazar.ordinarydifferentialequations.Listeners.ErrorListener;

public class Hey {
    public static void hideAnimation(View view){
        view.clearAnimation();
        view.setAlpha(1f);
        view.animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        }).start();
    }

    public static void showAnimation(View view, ErrorListener errorListener){
        view.clearAnimation();
        view.setAlpha(0f);
        view.animate().alpha(1f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                errorListener.onError(null,0);
            }
        }).start();
    }

    public static void combination(View v1,View v2,View v3){
        hideAnimation(v1);
        hideAnimation(v2);
        showAnimation(v3, (message, code) -> {});
        v3.setVisibility(View.VISIBLE);
    }

    public static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences("a",Context.MODE_PRIVATE);
    }

    public static void print(String a,String b){
        Log.e(a,b);
    }

    public static MyDialog showAlertDialog(Context context,ErrorListener errorListener) {
        MyDialog dialog = new MyDialog(context,errorListener);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }
}
