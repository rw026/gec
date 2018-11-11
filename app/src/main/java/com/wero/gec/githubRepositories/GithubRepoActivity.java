package com.wero.gec.githubRepositories;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wero.gec.R;
import com.wero.gec.MainActivity;

public class GithubRepoActivity extends AppCompatActivity {
    private TextView mRepoName;
    private TextView mRepoUrl;
    private TextView mRepoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repo);

        mRepoName = findViewById(R.id.tv_repo_name);
        mRepoUrl = findViewById(R.id.tv_repo_url);
        mRepoDescription = findViewById(R.id.tv_repo_description);

        Repository repository = null;
        if (getIntent().hasExtra("Repository")) {
            repository = (Repository) getIntent().getSerializableExtra("Repository");
            Log.d(getClass().getName(), repository.toString());
        } else {
            Log.e(getClass().getName(), "Should not start Activity without Repository");
        }

        if (repository == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        mRepoName.setText(repository.getName());
        mRepoUrl.setText(repository.getUrl());
        mRepoDescription.setText(repository.getDescription());

    }

    public void onClickGithubUrl(View view) {
        Uri githubRepoPage = Uri.parse(mRepoUrl.getText().toString());
        // create implicit intent
        Intent intent = new Intent(Intent.ACTION_VIEW, githubRepoPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
