package library.service;

public class NoMoreCopiesException extends Exception
{
    public NoMoreCopiesException()
    {
        super("No more copies available");
    }
}
