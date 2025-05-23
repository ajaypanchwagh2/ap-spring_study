package com.example.demo;

public class Message {
    private String sender;
    private String content;

    // Required no-arg constructor for deserialization
    public Message() {}

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
