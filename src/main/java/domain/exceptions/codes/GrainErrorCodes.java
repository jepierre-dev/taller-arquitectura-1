package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum GrainErrorCodes implements ErrorCode {

    NAME_REQUIRED("GRAIN-001"),
    INVENTORY_REQUIRED("GRAIN-002"),
    INVENTORY_NEGATIVE("GRAIN-003");

    private final String code;

    GrainErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
