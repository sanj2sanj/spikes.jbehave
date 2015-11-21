package org.sanj2sanj.spike.jbehave.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class MySteps {

    Engine engine = new Engine();
    private Payment payment;
    private SwiftMessage swiftMessage;

    @BeforeScenario
    public void beforeScenario() {
        engine = new Engine();
    }

    @Given("a swiftMessage with a Transaction Reference Number of ${tRef}, account Id ${accountId}, amount ${amount}, value date ${valueDate} and currency ${ccy}")
    public void aSwiftMessage(String tRef, String accountId, BigDecimal amount,
                              Date valueDate, String ccy) {
        swiftMessage = new SwiftMessage();
        swiftMessage.tRef = tRef;
        swiftMessage.accountId = accountId;
        swiftMessage.amount = amount;
        swiftMessage.valueDate = valueDate;
        swiftMessage.ccy = ccy;
    }

    @Given("a payment with a Country Focus Group of ${cfg}, payment Id of ${paymentId}, account Id ${accountId}, amount ${amount}, payment date ${paymentDate} and currency ${ccy}")
    public void aPayment(String cfg, String paymentId, String accountId,
                         BigDecimal amount, Date paymentDate, String ccy) {
        payment = new Payment();
        payment.cfg = cfg;
        payment.paymentId = paymentId;
        payment.accountId = accountId;
        payment.amount = amount;
        payment.valueDate = paymentDate;
        payment.ccy = ccy;
    }

    @Given("a swiftMessage with a Related Reference Number of ${tRef}, account Id ${accountId}, amount ${amount}, value date ${valueDate} and currency ${ccy}")
    public void aSwiftMessageRelatedRef(String rRef, String accountId,
                                        BigDecimal amount, Date valueDate, String ccy) {
        swiftMessage = new SwiftMessage();
        swiftMessage.rRef = rRef;
        swiftMessage.accountId = accountId;
        swiftMessage.amount = amount;
        swiftMessage.valueDate = valueDate;
        swiftMessage.ccy = ccy;
    }

    @When("the engine has processed the swiftMessage and payment")
    public void whenTheEngineHasProcessedTheSwiftMessageAndPayment() {
        engine.process(payment, swiftMessage);

    }

    @Then("${number} match event should be generated with a link to the swiftMessage and the payment with a match type of ${matchType}")
    public void thenAMatchEventShouldBeGeneratedWithALinkToTheSwiftMessageAndThePaymentWithAMatchTypeOfTransactionReference(
            int number, String matchType) {
        Set<Match> matches = engine.getMatches();
        Assert.assertNotNull("Should have matches", matches);
        Assert.assertEquals("Should have 1 match", number, matches.size());
        Assert.assertEquals(matchType, matches.iterator().next().matchType);
    }

}
