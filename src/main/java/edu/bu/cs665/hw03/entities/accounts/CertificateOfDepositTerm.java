package edu.bu.cs665.hw03.entities.accounts;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * CD term
 *
 * @see CertificateOfDepositAccount
 * @author dlegaspi@bu.edu
 */
public class CertificateOfDepositTerm {
    public static final Duration TERM_DURATION_6_MONTHS = Duration.ofDays(180);
    public static final Duration TERM_DURATION_1_YEAR = Duration.ofDays(365);
    public static final Duration TERM_DURATION_5_YEARS = Duration.ofDays(1_825);

    private BigDecimal endOfTermWorth;

    private BigDecimal interestRate;

    private Duration duration;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getEndOfTermWorth() {
        return endOfTermWorth;
    }

    public void setEndOfTermWorth(BigDecimal endOfTermWorth) {
        this.endOfTermWorth = endOfTermWorth;
    }
}
