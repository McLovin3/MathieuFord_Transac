package library.exception;

public class NotEnoughCopiesException extends Exception
{
    public NotEnoughCopiesException()
    {
        super("Must have one or more copies");
    }
}
