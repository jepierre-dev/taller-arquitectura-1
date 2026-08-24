package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum GrainErrorCodes implements ErrorCode {

    INVENTORY_NEGATIVE("GRAIN-001");

    private final String code;

    GrainErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
