package project.service;


import project.entity.Ingridient;

public interface IngridientService {


    public void saveIngridient(Ingridient ingridient);

    public Ingridient getIngridient(int id) throws Exception;

    public void deleteProduct(int id);
}
