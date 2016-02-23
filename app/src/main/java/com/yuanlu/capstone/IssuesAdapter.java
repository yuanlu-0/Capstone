package com.yuanlu.capstone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssueViewHolder> {

    private List<Issue> mIssueList;

    public IssuesAdapter() {
        mIssueList = new ArrayList<>();
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.issue_cell, viewGroup, false);
        return new IssueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder issueViewHolder, int position) {
        final Issue issue = mIssueList.get(position);
        issueViewHolder.mTitle.setText(issue.getTitle());
        issueViewHolder.mBody.setText(issue.getBody());
        issueViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // view.getContext().startActivity(IssueDetailsDialog.intentFor(view.getContext(), issue));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIssueList.size();
    }

    public void populate(@NonNull List<Issue> issueList) {
        mIssueList = issueList;
        Collections.sort(mIssueList, mIssueComparator);
        notifyDataSetChanged();
    }

    public static class IssueViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mBody;

        public IssueViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }
    }

    private final Comparator<Issue> mIssueComparator = new Comparator<Issue>() {
        @Override
        public int compare(Issue lhs, Issue rhs) {
            return rhs.getLastUpdated().compareTo(lhs.getLastUpdated());
        }
    };
}