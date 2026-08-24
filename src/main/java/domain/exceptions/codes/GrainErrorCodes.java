package domain.exceptions.codes;

import shared.domain.exceptions.ErrorCode;

public enum GrainErrorCodes implements ErrorCode {

    INVENTORY_NEGATIVE("GRAIN-001"),
    NAME_ALREADY_EXISTS("GRAIN-002"),
    GRAIN_NOT_FOUND("GRAIN-003");

    private final String code;

    GrainErrorCodes(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
