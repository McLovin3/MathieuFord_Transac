package model.user;

import lombok.*;

import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Admin extends LibraryUser
{
}
