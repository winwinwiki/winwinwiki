package org.winwin.request;

import lombok.Data;

@Data
public class GreetingResponse {

    private final long id;
    private final String content;

    public GreetingResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }
}