package project.service;


import project.dao.IngridientRep;
import project.entity.Ingridient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngridientServiceImp implements IngridientService {

    @Autowired
    private IngridientRep ingridientRep;

    @Override
    public void saveIngridient(Ingridient ingridient) {
        ingridientRep.save(ingridient);
    }

    @Override
    public Ingridient getIngridient(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        ingridientRep.deleteById(id);
    }
}
