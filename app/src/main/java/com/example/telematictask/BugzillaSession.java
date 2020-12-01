package com.example.telematictask;

import java.net.MalformedURLException;
import java.net.URL;

import b4j.core.DefaultIssue;
import b4j.core.DefaultSearchData;
import b4j.core.Issue;
import b4j.core.session.BugzillaHttpSession;

public class BugzillaSession {
    public static void main(String[] args) throws MalformedURLException {
        BugzillaHttpSession session = new BugzillaHttpSession();
        session.setBaseUrl(new URL("http://www.mybugzilla.com"));
        session.setBugzillaBugClass(DefaultIssue.class);

        AuthorizationCallback authCallback = new AuthorizationCallback("username", "password");
        session.setAuthorizationCallback(authCallback);

        if (session.open()) {
            DefaultSearchData searchData = new DefaultSearchData();
            searchData.add("classification", "Java Projects");
            searchData.add("product", "Bugzilla for Java");

            Iterable i = session.searchBugs(searchData, null);
            for (Issue issue : i) {
                System.out.println("Bug found: " + issue.getId() + " - " + issue.getDescription());
            }

            session.close();
        }
    }
}
