package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty
    private String id;

    public Account(){}

    public Account(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        return id.equals(((Account)o).id);
    }
}