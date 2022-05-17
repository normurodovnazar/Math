package com.normurodov_nazar.ordinarydifferentialequations.Sources;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.normurodov_nazar.ordinarydifferentialequations.Listeners.ErrorListener;
import com.normurodov_nazar.ordinarydifferentialequations.R;

public class MyDialog extends Dialog implements View.OnClickListener{

    Button ok,cancel;
    EditText fileName;
    final ErrorListener errorListener;
    public MyDialog(@NonNull Context context, ErrorListener errorListener) {
        super(context);
        this.errorListener = errorListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);
        fileName = findViewById(R.id.fileName);
        cancel.setOnClickListener((v)-> dismiss());
        ok.setOnClickListener((x)->{
            String fileN = fileName.getText().toString();
            if (!fileN.isEmpty() && !fileN.replaceAll(" ","").isEmpty()){
                errorListener.onError(fileN,0);
            }else Toast.makeText(getContext(), R.string.give_correct_name, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
