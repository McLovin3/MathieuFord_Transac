package model.library;

import lombok.*;
import model.document.LibraryDocument;
import model.user.LibraryUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Library
{
    @Id
    private long id;
    private String name;
    @OneToMany (mappedBy = "library")
    private final List<LibraryDocument> DOCUMENTS = new ArrayList<>();
    @OneToMany (mappedBy = "library")
    private final List<LibraryUser> USERS = new ArrayList<>();
    @OneToMany (mappedBy = "library")
    private final List<Borrow> BORROWS = new ArrayList<>();
}
