package library.exception;

public class NonExistentClientException extends Exception
{
    public NonExistentClientException()
    {
        super("Client does not exist");
    }
}