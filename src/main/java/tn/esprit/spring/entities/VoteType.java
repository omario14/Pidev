package tn.esprit.spring.entities;



import java.util.Arrays;

import tn.esprit.spring.exception.SpringVoteException;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SpringVoteException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}
