package com.wero.gec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wero.gec.Utils.NetworkUtil;
import com.wero.gec.githubRepositories.GithubAdapter;
import com.wero.gec.githubRepositories.GithubRepoActivity;
import com.wero.gec.githubRepositories.GithubRepoQueryTask;
import com.wero.gec.githubRepositories.ListItemClickListener;
import com.wero.gec.githubRepositories.Repository;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements ListItemClickListener, Serializable {
    private Integer numberElements;
    private List<Repository> repositories;

    private RecyclerView mGithubRes;
    private GithubAdapter mGithubAdapter;
    private EditText mSearchBox;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        numberElements = 50;
        mSearchBox = findViewById(R.id.et_github_search_box);
        mGithubRes = findViewById(R.id.rv_github);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mGithubRes.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemWasSelected = item.getItemId();
        if (menuItemWasSelected == R.id.action_search) {
            Context context = this;
            String message = "Search clicked!";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            repositories = getRepositories();
            mGithubAdapter = new GithubAdapter(repositories, this);
            mGithubRes.setAdapter(mGithubAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Repository> getRepositories() {
        String queryParam = mSearchBox.getText().toString();
        URL searchUrl = NetworkUtil.buildUrl(queryParam);
        String response = null;
        try {
            response = new GithubRepoQueryTask().execute(searchUrl).get();
        } catch (ExecutionException e) {
            Log.e(getClass().getName(), e.getMessage());
        } catch (InterruptedException e) {
            Log.e(getClass().getName(), e.getMessage());
        }

        return parseRepositories(response);
    }

    private List<Repository> parseRepositories(String response) {
        List<Repository> repositories = new ArrayList<>(numberElements);
        try {
            JSONObject github = new JSONObject(response);
            JSONArray items = github.getJSONArray("items");
            // TODO: Fix this to retrieve more than only 30 items
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                repositories.add(new Repository(item.getString("name"),
                                                item.getString("html_url"),
                                                item.getString("description")));
            }

        } catch (JSONException e) {
            Log.e(this.getClass().getName(), e.getMessage(), e);
        }

        return repositories;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        Context context = MainActivity.this;
        Class destActivity = GithubRepoActivity.class;
        Intent intent = new Intent(context, destActivity);
        intent.putExtra("Repository", repositories.get(clickedItemIndex));
        startActivity(intent);
    }
}
