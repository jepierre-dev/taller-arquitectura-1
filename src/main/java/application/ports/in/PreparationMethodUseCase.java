package application.ports.in;

import java.util.List;

import domain.models.PreparationMethod;

public interface PreparationMethodUseCase {
    public void createPreparationMethod(String name, String description, Integer timeInMinutes);
    public void updatePreparationMethod(Long id, String name, String description, Integer timeInMinutes);
    public void deletePreparationMethod(Long id);

    public List<PreparationMethod> listPreparationMethods();
}
