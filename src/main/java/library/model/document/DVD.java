package library.model.document;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DVD extends LibraryDocument
{
    private int runtime;
}
