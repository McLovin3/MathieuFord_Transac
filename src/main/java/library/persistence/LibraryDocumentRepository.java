package library.persistence;

import library.model.document.Book;
import library.model.document.BookType;
import library.model.document.LibraryDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryDocumentRepository extends JpaRepository<LibraryDocument, Long>
{
    List<LibraryDocument> findAllByTitleIgnoreCaseContaining(String title);

    List<LibraryDocument> findAllByAuthorIgnoreCaseContaining(String author);

    List<LibraryDocument> findAllByPublicationYear(int year);

    @Query("SELECT book FROM Book book WHERE book.bookType = :bookType")
    List<Book> findAllBooksByCategory(@Param("bookType") BookType category);
}
