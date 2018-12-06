package kubitz.client.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty
    String payload;

    public Message(String a){
        payload = a;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
