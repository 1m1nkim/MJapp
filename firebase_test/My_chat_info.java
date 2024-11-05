package com.example.firebase_test;

import java.util.List;

public class My_chat_info {
    private String chatRoomId;
    private String userId1;
    private String userId2;
    private List<Message> messages;
    public My_chat_info(){

    }
    public My_chat_info(String chatRoomId, String userId1, String userId2, List<Message> messages){
        this.chatRoomId = chatRoomId;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.messages = messages;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
