package library.exception;

public class NoMoreCopiesException extends Exception
{
    public NoMoreCopiesException()
    {
        super("No more copies available");
    }
}
