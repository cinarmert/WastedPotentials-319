package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    public Account(){}

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}