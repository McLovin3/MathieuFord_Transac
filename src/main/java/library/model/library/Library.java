package library.model.library;

import lombok.*;
import library.model.document.LibraryDocument;
import library.model.user.LibraryUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Library
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ToString.Exclude
    @OneToMany (mappedBy = "library")
    private final List<LibraryDocument> DOCUMENTS = new ArrayList<>();
    @ToString.Exclude
    @OneToMany (mappedBy = "library")
    private final List<LibraryUser> USERS = new ArrayList<>();
    @ToString.Exclude
    @OneToMany (mappedBy = "library")
    private final List<Borrow> BORROWS = new ArrayList<>();
}
