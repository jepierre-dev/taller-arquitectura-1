package shared.domain.exceptions;

/** Codigo de error estable de cara al cliente. Cada agregado aporta su propio enum. */
public interface ErrorCode {

    String code();
}