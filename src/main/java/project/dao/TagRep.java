package project.dao;

import project.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRep extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByTagName(String tag);
}
