package project.service;

import project.dao.TagRep;
import project.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    private TagRep tagRep;


    public List<Tag> getAllTags() {
        return tagRep.findAll();
    }

    @Override
    public List<Tag> findAllByTagName(String tag) {
        List<Tag> tags = tagRep.findAllByTagName(tag);
        return tags;
    }

    @Override
    public void saveTag(Tag tag) {
        tagRep.save(tag);
    }

    @Override
    public List<Tag> getByName(String name) {
        List<Tag> tag = tagRep.findAll();
        Iterator<Tag> tagIterator = tag.iterator();
        while (tagIterator.hasNext()) {
            Tag tagE = tagIterator.next();
            if (!tagE.getTagName().equals(name)) {
                tagIterator.remove();
            }
        }
        return tag;
    }

    @Override
    public void deleteTag(int id) {
        tagRep.deleteById(id);
    }
}
