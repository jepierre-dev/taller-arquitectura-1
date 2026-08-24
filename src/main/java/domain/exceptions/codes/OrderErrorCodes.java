package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum OrderErrorCodes implements ErrorCode {

    QUANTITY_NOT_POSITIVE("ORDER-001"),
    INSUFFICIENT_INVENTORY("ORDER-002"),
    NOT_PENDING("ORDER-003");

    private final String code;

    OrderErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
