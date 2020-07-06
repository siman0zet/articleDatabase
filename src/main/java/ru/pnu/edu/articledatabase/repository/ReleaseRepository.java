package ru.pnu.edu.articledatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnu.edu.articledatabase.entity.Release;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {

}
