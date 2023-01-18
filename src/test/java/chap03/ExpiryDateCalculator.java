package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDte(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if (payData.getFirstBillingDate() != null) {
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            final int datOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (datOfFirstBilling != candidateExp.getDayOfMonth()) {
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
    //todo 리팩토링 더 해볼것
    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
