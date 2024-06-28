package com.lenora.staj.websocket.rest.response;

import lombok.Data;

@Data
public class TopicListView {
    private String id;
    private String topicName;
    private String topicTitle;
    private String topicCreatorName;

    public TopicListView(String id, String topicName, String topicTitle, String topicCreatorName) {
        this.id = id;
        this.topicName = topicName;
        this.topicTitle = topicTitle;
        this.topicCreatorName = topicCreatorName;
    }
}
