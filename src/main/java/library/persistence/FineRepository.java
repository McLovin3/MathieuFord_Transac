package library.persistence;

import library.model.library.Fine;
import library.model.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long>
{
    List<Fine> findAllByClient(Client client);
}
