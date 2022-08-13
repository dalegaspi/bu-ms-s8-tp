package edu.bu.cs665.program;

public class CertificateProgram extends Program {
    @Override
    public int minimumYearsToComplete() {
        return 0;
    }

    public CertificateProgram(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("Certificate Program %s", getTitle());
    }
}
