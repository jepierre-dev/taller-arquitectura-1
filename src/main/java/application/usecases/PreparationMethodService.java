package application.usecases;

import application.ports.in.PreparationMethodUseCase;
import application.ports.out.PreparationMethodRepositoryPort;
import domain.exceptions.PreparationMethodErrors;
import domain.models.PreparationMethod;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PreparationMethodService implements PreparationMethodUseCase {

    private final PreparationMethodRepositoryPort preparationMethodRepositoryPort;

    public PreparationMethodService(PreparationMethodRepositoryPort preparationMethodRepositoryPort) {
        this.preparationMethodRepositoryPort = preparationMethodRepositoryPort;
    }

    @Override
    public void createPreparationMethod(String name, String description, Integer timeInMinutes) {
        if (preparationMethodRepositoryPort.existsByName(name)) {
            throw PreparationMethodErrors.nameAlreadyExists(name);
        }
        preparationMethodRepositoryPort.savePreparationMethod(
                new PreparationMethod(null, name, description, timeInMinutes));
    }

    @Override
    public void updatePreparationMethod(Long id, String name, String description, Integer timeInMinutes) {
        if (preparationMethodRepositoryPort.existsByNameAndIdNot(name, id)) {
            throw PreparationMethodErrors.nameAlreadyExists(name);
        }
        validateNotNull(preparationMethodRepositoryPort.findPreparationMethodById(id), id);
        preparationMethodRepositoryPort.updatePreparationMethod(
                new PreparationMethod(id, name, description, timeInMinutes));
    }

    @Override
    public void deletePreparationMethod(Long id) {
        validateNotNull(preparationMethodRepositoryPort.findPreparationMethodById(id), id);
        preparationMethodRepositoryPort.deletePreparationMethod(id);
    }

    private void validateNotNull(PreparationMethod preparationMethod, Long preparationMethodId) {
        if (preparationMethod == null) {
            throw PreparationMethodErrors.preparationMethodNotFound(preparationMethodId);
        }
    }

}
