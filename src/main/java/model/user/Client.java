package model.user;

import lombok.*;
import model.library.Fine;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client extends LibraryUser
{
    @OneToMany
    private List<Fine> Fines;
}
