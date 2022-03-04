package persistence;

public interface ClientDAO
{
    void addClient(String username, String password) throws IllegalArgumentException;
}
