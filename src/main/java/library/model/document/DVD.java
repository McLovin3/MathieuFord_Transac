package library.model.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("DVD")
public class DVD extends LibraryDocument
{
    @Transient
    private final int RETURN_DAYS = 7;
    private int runtime;

    @Override
    public int getReturnDays()
    {
        return RETURN_DAYS;
    }
}
