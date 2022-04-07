package library.model.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("CD")
public class CD extends LibraryDocument
{
    private int runtime;
}
