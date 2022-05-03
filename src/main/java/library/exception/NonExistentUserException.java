package library.exception;

public class NonExistentUserException extends Exception
{
    public NonExistentUserException()
    {
        super("Client does not exist");
    }
}