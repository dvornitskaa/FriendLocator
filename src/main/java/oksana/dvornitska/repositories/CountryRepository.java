package oksana.dvornitska.repositories;

import oksana.dvornitska.entities.Country;
import oksana.dvornitska.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
