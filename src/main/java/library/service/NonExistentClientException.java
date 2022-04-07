package library.service;

class NonExistentClientException extends Exception
{
    public NonExistentClientException()
    {
        super("Client does not exist");
    }
}