package library.exception;

public class ClientDidNotBorrowException extends Exception
{
    public ClientDidNotBorrowException()
    {
        super("Client did not borrow book");
    }
}
