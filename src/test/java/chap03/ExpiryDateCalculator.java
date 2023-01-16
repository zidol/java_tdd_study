package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDte(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            final int datOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (datOfFirstBilling != candidateExp.getDayOfMonth()) {
                final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMon < datOfFirstBilling) {
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(datOfFirstBilling);
            } else {
                return candidateExp;
            }
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }
}
