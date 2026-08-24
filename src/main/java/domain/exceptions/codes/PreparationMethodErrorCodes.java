package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum PreparationMethodErrorCodes implements ErrorCode {

    TIME_NEGATIVE("PREPARATION-001");

    private final String code;

    PreparationMethodErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
