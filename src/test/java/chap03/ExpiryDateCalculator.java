package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDte(PayData payData) {
        int addedMonths = getAddedMonths(payData);
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private int getAddedMonths(PayData payData) {
        int addedMonths = 0;
        if (payData.getPayAmount() == 100_000) {
            addedMonths = 12;
        }
        if (payData.getPayAmount() > 100_000) {
            int tens = (payData.getPayAmount() / 10_000) / 10;
            int first = (payData.getPayAmount()  / 10_000) % 10;
            addedMonths = (tens*10) + 2 + first;
        }
        if (payData.getPayAmount() < 100_000) {
            addedMonths = payData.getPayAmount() / 10_000;
        }
        return addedMonths;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if (isSameDayOfMonth(candidateExp, payData.getFirstBillingDate())) {
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            final int datOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (dayLenOfCandiMon < datOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(datOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate candidateExp, LocalDate datOfFirstBilling) {
        return datOfFirstBilling.getDayOfMonth() != candidateExp.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
