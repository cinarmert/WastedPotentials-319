package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    public Account(){}

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o){
        return id.equals( ((Account)o).id) && name.equals(((Account)o).name);
    }
}