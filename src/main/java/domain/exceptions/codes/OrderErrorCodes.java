package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum OrderErrorCodes implements ErrorCode {

    NAME_REQUIRED("ORDER-001"),
    INVENTORY_REQUIRED("ORDER-002"),
    INVENTORY_NEGATIVE("ORDER-003");

    private final String code;

    OrderErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
