package ua.nure.server.exception;

import java.sql.SQLException;

public class ConnectionException extends SQLException {

    public ConnectionException(String message) { super(message); }

    public ConnectionException() { }
}
