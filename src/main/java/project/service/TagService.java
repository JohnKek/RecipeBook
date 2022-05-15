package project.service;

import project.entity.Tag;

import java.util.List;

public interface TagService {
    public void saveTag(Tag tag);

    public List<Tag> getByName(String name);

    public void deleteTag(int id);

    public List<Tag> getAllTags();

    List<Tag> findAllByTagName(String tag);
}
