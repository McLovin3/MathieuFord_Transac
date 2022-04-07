package library.service;

public class NonExistentDocumentException extends Exception
{
    public NonExistentDocumentException()
    {
        super("Document does not exist");
    }
}
