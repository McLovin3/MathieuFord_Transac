package model.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class LibraryDocument
{
    protected int id;
    protected String title;
    protected String author;
    protected int publicationYear;
    protected boolean isTaken;
    protected LocalDateTime lastDateRented;
}
