package com.yuanlu.capstone;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class IssuesRequest extends ApiRequest<List<Issue>> {

    public IssuesRequest(RequestCallback<List<Issue>> requestCallback) {
        super("https://api.github.com/repos/uber/hyperbahn/issues", requestCallback);
    }

    @Override
    public List<Issue> process(JSONArray jsonArray) throws JSONException {
        List<Issue> issueList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Issue issue = new Issue(jsonArray.getJSONObject(i));
            issueList.add(issue);
        }
        return issueList;
    }
}
