package org.sanj2sanj.spike.jbehave.steps;

import java.util.HashSet;
import java.util.Set;

public class Engine {

    Set<Payment> payments = new HashSet<Payment>();
    Set<SwiftMessage> swiftMessages = new HashSet<SwiftMessage>();
    Set<Match> matches = new HashSet<Match>();

    public void process(Payment payment, SwiftMessage swiftMessage) {
        payments.add(payment);
        swiftMessages.add(swiftMessage);

        for (SwiftMessage sw : swiftMessages) {
            for (Payment p : payments) {
                transactionRefMatcher(sw, p);
                relatedRefMatcher(sw, p);
            }
        }
    }

    private void relatedRefMatcher(SwiftMessage sw, Payment p) {
        if (sw.rRef != null && sw.rRef.equalsIgnoreCase(p.cfg + p.paymentId)
                && sw.accountId.equals(p.accountId)
                && sw.amount.equals(p.amount)
                && sw.valueDate.equals(p.valueDate) && sw.ccy.equals(p.ccy)) {
            matches.add(new Match(sw, p, "Related Reference"));
        }
    }

    private void transactionRefMatcher(SwiftMessage sw, Payment p) {
        if (sw.tRef != null && sw.tRef.equalsIgnoreCase(p.cfg + p.paymentId)
                && sw.accountId.equals(p.accountId)
                && sw.amount.equals(p.amount)
                && sw.valueDate.equals(p.valueDate) && sw.ccy.equals(p.ccy)) {
            matches.add(new Match(sw, p, "Transaction Reference"));
        }
    }

    public Set<Match> getMatches() {
        return matches;
    }

}
