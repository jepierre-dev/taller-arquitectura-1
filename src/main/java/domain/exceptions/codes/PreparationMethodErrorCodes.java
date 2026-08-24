package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum PreparationMethodErrorCodes implements ErrorCode {

    TIME_NEGATIVE("PREPARATION-001"),
    NAME_ALREADY_EXISTS("PREPARATION-002"),
    PREPARATION_METHOD_NOT_FOUND("PREPARATION-003");

    private final String code;

    PreparationMethodErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
