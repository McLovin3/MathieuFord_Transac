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
    @OneToMany (mappedBy = "library")
    private List<LibraryDocument> documents = new ArrayList<>();
    @OneToMany (mappedBy = "library")
    private List<LibraryUser> users = new ArrayList<>();
    @OneToMany (mappedBy = "library")
    private List<Borrow> borrows = new ArrayList<>();
}
