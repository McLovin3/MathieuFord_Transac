package service;

import lombok.Data;
import persistence.LibraryDao;

@Data
public class ClientService
{
    private final LibraryDao libraryDao;
}
