package library.service;

public class ClientHasFinesException extends Exception
{
    public ClientHasFinesException()
    {
        super("Client has unpaid fines");
    }
}
