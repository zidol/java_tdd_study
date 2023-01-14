package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDte(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
