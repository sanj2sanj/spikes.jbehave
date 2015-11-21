package org.sanj2sanj.spike.jbehave.steps;

import java.util.ArrayList;
import java.util.List;

public class Match {

    String matchType;
    List<Payment> payments = new ArrayList<Payment>();
    List<SwiftMessage> swiftMessages = new ArrayList<SwiftMessage>();

    public Match(SwiftMessage sw, Payment p, String matchType) {
        this.matchType = matchType;
        payments.add(p);
        swiftMessages.add(sw);
    }
}
