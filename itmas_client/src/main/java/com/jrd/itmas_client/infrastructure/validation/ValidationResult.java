package com.jrd.itmas_client.infrastructure.validation;

/**
 * Created by jakub on 23.06.16.
 */
public class ValidationResult {
    private boolean isCorrent;
    private String errorDescription;

    public ValidationResult() {}

    public ValidationResult(boolean isCorrent, String errorDescription) {
        this.isCorrent = isCorrent;
        this.errorDescription = errorDescription;
    }

    public boolean isCorrent() {
        return isCorrent;
    }

    public boolean isNotCorrect() {
        return !isCorrent;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
