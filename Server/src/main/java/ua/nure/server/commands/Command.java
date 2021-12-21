package ua.nure.server.commands;

public abstract class Command {
    public static final String POSITIVE_ANSWER = "YES\n";
    public static final String NEGATIVE_ANSWER = "NO\n";

    public abstract void execute();
}
