package com.acacia.common.db;

/**
 * Data access exception, be thrown during DB access.
 * @author zhonglizhi.
 */
public class DataAccessException extends Exception {

    /** version id. */
    private static final long serialVersionUID = -1451395575410999627L;

    public DataAccessException() {
        super();
    }

    public DataAccessException(final String message) {
        super(message);
    }

    public DataAccessException(final Throwable cause) {
        super(cause);
    }

    public DataAccessException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
