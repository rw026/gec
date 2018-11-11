package com.wero.gec.github_repositories;

import com.wero.gec.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoViewHolder> {
    private final ListItemClickListener mOnClickListener;

    private int numberOfItems;
    private List<Repository> repositories;

    public GithubRepoAdapter(List<Repository> repositories, ListItemClickListener listener) {
        this.repositories = repositories;

        numberOfItems = repositories.size();
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public GithubRepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.github_repo_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        GithubRepoViewHolder viewHolder = new GithubRepoViewHolder(view, mOnClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GithubRepoViewHolder githubRepoViewHolder, int position) {
        Log.i(this.getClass().getName(), "#" + position);
        githubRepoViewHolder.bind(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return numberOfItems;
    }
}


