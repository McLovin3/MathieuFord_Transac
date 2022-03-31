package library.model.user;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("ATTENDANT")
@Entity
public class Attendant extends LibraryUser {}
