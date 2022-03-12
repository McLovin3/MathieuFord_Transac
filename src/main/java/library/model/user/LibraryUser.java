package library.model.user;

import lombok.*;
import library.model.library.Library;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
public abstract class LibraryUser
{
    @Id
    protected long id;
    protected String name;
    protected String password;
    @ManyToOne
    protected Library library;
}
