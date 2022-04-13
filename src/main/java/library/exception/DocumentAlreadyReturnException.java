package library.exception;

public class DocumentAlreadyReturnException extends Exception
{
    public DocumentAlreadyReturnException()
    {
        super("Document already returned");
    }
}
