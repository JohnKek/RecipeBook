package project.dao;


import project.entity.Ingridient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngridientRep extends JpaRepository<Ingridient, Integer> {
}
