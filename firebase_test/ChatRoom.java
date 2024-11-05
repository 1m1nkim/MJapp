package com.example.firebase_test;

import java.util.List;

// ChatRoom.java
public class ChatRoom {
    private String roomId;
    private String userId1;
    private String userId2;
    private List<Message> messages;

    public ChatRoom() {
        // 기본 생성자가 필요합니다. Firebase에서 데이터를 가져올 때 사용됩니다.
    }

    public ChatRoom(String roomId, String userId1, String userId2, List<Message> messages) {
        this.roomId = roomId;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.messages = messages;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
