package library.persistence;

import library.model.user.Client;
import library.model.user.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long>
{
    @Query("SELECT client FROM Client client LEFT JOIN FETCH client.borrows WHERE client.id = :id")
    Client findClientByIdWithBorrows(@Param("id") long id);
    @Query("SELECT client FROM Client client WHERE client.id = :id")
    Client findClientById(@Param("id") long id);
    @Query("SELECT client FROM Client client LEFT JOIN FETCH client.fines WHERE client.id = :id")
    Client findClientByIdWithFines(@Param("id") long id);
    @Query("SELECT client FROM Client client LEFT JOIN FETCH client.fines LEFT JOIN FETCH client.borrows WHERE client.id = :id")
    Client findClientByIdWithFinesAndBorrows(@Param("id") long id);
}
