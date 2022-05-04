package library.model.document;

import library.exception.InvalidBookTypeException;

public enum BookType
{
    NOVEL, SCHOOL, STUDY, MAGAZINE;

    public static BookType getBookType(String string) throws InvalidBookTypeException
    {
        return switch (string.toUpperCase())
        {
        case ("NOVEL") -> BookType.NOVEL;
        case ("SCHOOL") -> BookType.SCHOOL;
        case ("STUDY") -> BookType.STUDY;
        case ("MAGAZINE") -> BookType.MAGAZINE;
        default -> throw new InvalidBookTypeException();
        };
    }
}
