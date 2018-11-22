package kubitz.server.database.dailychallenge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dailychallenges")
public class DailyChallenge {

    @Id
    private int id;

    private int size;

    private int[][] mission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMission() {
        return mission;
    }

    public void setMission(int[][] mission) {
        this.mission = mission;
    }
}