package library.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import library.model.user.Attendant;

public interface AttendantRepository extends JpaRepository<Attendant, Long>
{

}
