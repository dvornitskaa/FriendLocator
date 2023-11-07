package oksana.dvornitska.repositories;

import oksana.dvornitska.entities.Location;
import oksana.dvornitska.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
