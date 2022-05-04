package library.exception;

public class InvalidBookTypeException extends Exception
{
    public InvalidBookTypeException()
    {
        super("Invalid book type");
    }
}
