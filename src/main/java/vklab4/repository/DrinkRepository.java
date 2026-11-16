package vklab4.repository;

import vklab4.Data.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    @Query("""
        SELECT d FROM Drink d
        WHERE (:name = '' OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:type = '' OR LOWER(d.type) LIKE LOWER(CONCAT('%', :type, '%')))
          AND (:minVolume IS NULL OR d.volume >= :minVolume)
          AND (:maxVolume IS NULL OR d.volume <= :maxVolume)
          AND (:minPrice IS NULL OR d.price >= :minPrice)
          AND (:maxPrice IS NULL OR d.price <= :maxPrice)
        ORDER BY d.name
        """)
    List<Drink> search(@Param("name") String name,
                       @Param("type") String type,
                       @Param("minVolume") Double minVolume,
                       @Param("maxVolume") Double maxVolume,
                       @Param("minPrice") Double minPrice,
                       @Param("maxPrice") Double maxPrice);
}