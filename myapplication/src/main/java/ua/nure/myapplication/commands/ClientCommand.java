package ua.nure.myapplication.commands;

public abstract class ClientCommand {
    public static final String POSITIVE_ANSWER = "YES";
    public static final String NEGATIVE_ANSWER = "NO";

    public abstract String execute();
}
