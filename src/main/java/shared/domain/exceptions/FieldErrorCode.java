package shared.domain.exceptions;

/** Validaciones estructurales que no pertenecen a ningun agregado: cualquier modelo las comparte. */
public enum FieldErrorCode implements ErrorCode {

    /** Un campo obligatorio llego sin valor. */
    REQUIRED("FIELD-001");

    private final String code;

    FieldErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
