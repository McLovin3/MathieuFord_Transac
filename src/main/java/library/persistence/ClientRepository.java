package library.persistence;

import library.model.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{
    @Query("SELECT client FROM Client client LEFT JOIN FETCH client.borrows borrows WHERE client.id = :id")
    Optional<Client> findByIdWithBorrows(@Param("id") long id);

    @Query("SELECT client FROM Client client LEFT JOIN FETCH client.fines fines WHERE client.id = :id")
    Optional<Client> findByIdWithFines(@Param("id") long id);
}
