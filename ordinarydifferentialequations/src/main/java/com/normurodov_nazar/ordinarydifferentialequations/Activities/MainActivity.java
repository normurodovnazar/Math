package com.normurodov_nazar.ordinarydifferentialequations.Activities;

import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Hey.combination;
import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Hey.hideAnimation;
import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Hey.showAlertDialog;
import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Hey.showAnimation;
import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Key.myFolder;
import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Key.url;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.normurodov_nazar.ordinarydifferentialequations.Listeners.PageListener;
import com.normurodov_nazar.ordinarydifferentialequations.R;
import com.normurodov_nazar.ordinarydifferentialequations.Sources.MWebClient;
import com.normurodov_nazar.ordinarydifferentialequations.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;
    String path;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        path = getDir(myFolder,MODE_PRIVATE).getPath();
        b.savedPages.bringToFront();
        b.savePage.bringToFront();
        b.savePage.setOnClickListener((c)-> showAlertDialog(this, (message, code) -> {
            if (b.web.getProgress()==100) b.web.saveWebArchive(path+"/"+message+".mht"); else
                Toast.makeText(this, R.string.page_not_loaded, Toast.LENGTH_SHORT).show();
        }));
        b.savedPages.setOnClickListener((c)-> startActivity(new Intent(this,SavedPages.class)));
        b.retry.setOnClickListener((v)->{
            b.web.reload();
        });
        MWebClient client = new MWebClient(new PageListener() {
            @Override
            public void onPageSuccess() {
                showWebPage();
            }

            @Override
            public void onPageFail(String message) {
                if (message.equals("0")) b.errorText.setText(R.string.error_connection);
                else b.errorText.setText(message);
                showErrorPage();
            }

            @Override
            public void onStartedLoading() {
                showLoadingBar();
            }
        });
        b.web.getSettings().setJavaScriptEnabled(true);
        b.web.getSettings().setDomStorageEnabled(true);
        b.web.getSettings().setAppCacheEnabled(true);
        b.web.setWebViewClient(client);
        b.web.loadUrl(url);
    }

    void showWebPage() {
        showAnimation(b.savePage,(a,b)->{});
        b.savePage.setVisibility(View.VISIBLE);
        combination(b.errorPage, b.loadingBar, b.web);
    }

    void showLoadingBar() {
        hideAnimation(b.savePage);
        combination(b.errorPage, b.web, b.loadingBar);
    }

    void showErrorPage() {
        hideAnimation(b.savePage);
        combination(b.web, b.loadingBar, b.errorPage);
    }
}