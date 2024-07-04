package com.lenora.staj.websocket.rest.response;

import lombok.Data;

@Data
public class TopicListView {
    private String id;
    private String topicName;
    private String topicDescription;
    private String topicCreatorName;

    public TopicListView(String id, String topicName, String topicDescription, String topicCreatorName) {
        this.id = id;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.topicCreatorName = topicCreatorName;
    }
}
