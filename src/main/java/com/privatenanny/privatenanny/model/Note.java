package com.privatenanny.privatenanny.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Note {
    private String subject;
    private String content;
    private List<Utilisateur> receivers;

    public Note(String subject, String content, List<Utilisateur> receivers) {
        this.subject = subject;
        this.content = content;
        this.receivers = receivers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Utilisateur> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Utilisateur> receivers) {
        this.receivers = receivers;
    }

    public Map<String, String> getData() {
        Map<String, String> result = new HashMap<>();

        for (Utilisateur user : receivers) {
            result.put(user.getUid(), user.getUid());
        }

        return result;
    }
}
