package library.persistence;

import library.model.document.LibraryDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryDocumentRepository extends JpaRepository<LibraryDocument, Long>
{

}
