package application.ports.out;

import java.util.List;

import domain.models.PreparationMethod;

public interface PreparationMethodRepositoryPort {
    public void savePreparationMethod(PreparationMethod preparationMethod);
    public void updatePreparationMethod(PreparationMethod preparationMethod);
    public void deletePreparationMethod(Long id);

    public boolean existsByName(String name);
    public boolean existsByNameAndIdNot(String name, Long id);

    public PreparationMethod findPreparationMethodById(Long id);
    public List<PreparationMethod> findAllPreparationMethods();

}
