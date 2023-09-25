package oksana.dvornitska.repositories;

import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByName(String name);
    Optional<User> findUserById (Integer id);
    List<User> findUsersByNameStartsWith (String name);
    @Query("SELECT u.country AS country, " +
            "CAST(COUNT(u) * 100.0 / (SELECT COUNT(*) FROM User) AS DOUBLE) AS userPercentage " +
            "FROM User u GROUP BY u.country")
    List<Object[]> getCountryStatistics();

    default Map<String, Double> getCountryStatisticsAsMap() {
        List<Object[]> results = getCountryStatistics();
        Map<String, Double> statisticsMap = new HashMap<>();

        for (Object[] result : results) {
            String country = (String) result[0];
            Double userPercentage = (Double) result[1];
            statisticsMap.put(country, userPercentage);
        }

        return statisticsMap;
    }

}
