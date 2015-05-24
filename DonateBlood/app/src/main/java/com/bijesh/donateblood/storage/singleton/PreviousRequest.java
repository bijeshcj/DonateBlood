package com.bijesh.donateblood.storage.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bijesh on 24-05-2015.
 */
public enum PreviousRequest {INS;
    private List<String> previousMessages = new ArrayList<>();

    public List<String> getPreviousMessages() {
        return previousMessages;
    }

    public void setPreviousMessage(String previousMessage) {
        this.previousMessages.add(previousMessage);
    }
}
