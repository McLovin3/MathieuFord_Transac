package library.persistence;

import library.model.library.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long>
{
    List<Borrow> findAllByClientId(long id);
}
