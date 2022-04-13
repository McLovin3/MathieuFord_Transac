package library.exception;

public class ClientAlreadyHasBorrowException extends Exception
{
    public ClientAlreadyHasBorrowException()
    {
        super("Client already borrowed this document");
    }    
}
