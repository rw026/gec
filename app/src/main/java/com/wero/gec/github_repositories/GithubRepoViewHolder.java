package com.wero.gec.github_repositories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.wero.gec.R;

public class GithubRepoViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
    private final ListItemClickListener mOnClickListener;
    private TextView listRepoItemView;

    public GithubRepoViewHolder(@NonNull View itemView, ListItemClickListener listener) {
        super(itemView);

        listRepoItemView = itemView.findViewById(R.id.tv_github_repo_item);
        mOnClickListener = listener;
        itemView.setOnClickListener(this);
    }

    public void bind(Repository repository) {
        listRepoItemView.setText(repository.getName());
    }

    @Override
    public void onClick(View v) {
        mOnClickListener.onListItemClick(getAdapterPosition());
    }
}
