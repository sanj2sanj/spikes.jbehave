Scenario: Match by Transaction reference and primary attributes

Given a swiftMessage with a Transaction Reference Number of UK111, account Id 123, amount 10, value date 2014-10-10 and currency GBP
Given a payment with a Country Focus Group of UK, payment Id of 111, account Id 123, amount 10, payment date 2014-10-10 and currency GBP
When the engine has processed the swiftMessage and payment
Then 1 match event should be generated with a link to the swiftMessage and the payment with a match type of Transaction Reference

Scenario: Match by Related reference and primary attributes

Given a swiftMessage with a Related Reference Number of US111, account Id 123, amount 10, value date 2014-10-10 and currency GBP
Given a payment with a Country Focus Group of US, payment Id of 111, account Id 123, amount 10, payment date 2014-10-10 and currency GBP
When the engine has processed the swiftMessage and payment
Then 1 match event should be generated with a link to the swiftMessage and the payment with a match type of Related Reference



