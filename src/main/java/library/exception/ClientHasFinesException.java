package library.exception;

public class ClientHasFinesException extends Exception
{
    public ClientHasFinesException()
    {
        super("Client has unpaid fines");
    }
}
