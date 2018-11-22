package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailyChallenges {

    @JsonProperty
    List<ClassicChallenge> classicChallenges;

    public List<ClassicChallenge> getClassicChallenges() {
        return classicChallenges;
    }

    public void setClassicChallenges(List<ClassicChallenge> classicChallenges) {
        this.classicChallenges = classicChallenges;
    }
}
