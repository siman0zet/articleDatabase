package ru.pnu.edu.articledatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnu.edu.articledatabase.entity.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {
}
