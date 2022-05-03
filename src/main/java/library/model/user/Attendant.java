package library.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Setter
@DiscriminatorValue("ATTENDANT")
@Entity
public class Attendant extends LibraryUser
{
}
