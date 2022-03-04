package model.library;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.document.LibraryDocument;
import model.user.LibraryUser;

import java.util.List;

@Data
@NoArgsConstructor
public class Library
{
    private List<LibraryDocument> documents;
    private List<LibraryUser> users;
    private List<Borrow> borrows;
}
