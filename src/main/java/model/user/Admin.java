package model.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends LibraryUser
{
}
