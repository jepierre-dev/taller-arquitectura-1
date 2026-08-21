package shared.domain.exceptions;

/** Errores del borde HTTP: la peticion no llega a formarse bien, nunca alcanza el dominio. */
public enum RequestErrorCode implements ErrorCode {

    /** Violaciones de Bean Validation sobre el cuerpo o los parametros. */
    VALIDATION("REQUEST-001"),

    /** El JSON no encaja con el tipo esperado. */
    MALFORMED_BODY("REQUEST-002");

    private final String code;

    RequestErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
