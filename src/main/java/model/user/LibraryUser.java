package model.user;

import lombok.*;
import model.library.Library;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public abstract class LibraryUser
{
    @Id
    protected long id;
    protected String username;
    protected String password;
    @ManyToOne
    protected Library library;
}
