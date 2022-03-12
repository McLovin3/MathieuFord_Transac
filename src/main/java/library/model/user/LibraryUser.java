package library.model.user;

import lombok.*;
import library.model.library.Library;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
public abstract class LibraryUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    protected String password;
    @ManyToOne
    protected Library library;
}
