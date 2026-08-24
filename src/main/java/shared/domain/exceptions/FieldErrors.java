package shared.domain.exceptions;

import java.util.Map;

/** Fabrica unica para los errores de campo comunes a todos los agregados. */
public final class FieldErrors {

    private FieldErrors() {
    }

    public static DomainException required(String field) {
        return new DomainException.RuleViolation(FieldErrorCode.REQUIRED, Map.of("field", field));
    }

    /** Guarda de uso directo en los constructores compactos de los records. */
    public static void requirePresent(Object value, String field) {
        if (value == null) {
            throw required(field);
        }
    }
}
