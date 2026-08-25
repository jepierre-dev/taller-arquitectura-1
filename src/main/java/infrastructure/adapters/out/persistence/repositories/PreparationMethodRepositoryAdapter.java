package infrastructure.adapters.out.persistence.repositories;

import java.util.List;

import application.ports.out.PreparationMethodRepositoryPort;
import domain.exceptions.PreparationMethodErrors;
import domain.models.PreparationMethod;
import infrastructure.adapters.out.persistence.entities.PreparationMethodEntity;
import infrastructure.adapters.out.persistence.mappers.PreparationMethodMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PreparationMethodRepositoryAdapter
        implements PreparationMethodRepositoryPort, PanacheRepository<PreparationMethodEntity> {

    @Override
    public void savePreparationMethod(PreparationMethod preparationMethod) {
        persist(PreparationMethodMapper.toEntity(preparationMethod));
    }

    @Override
    public void updatePreparationMethod(PreparationMethod preparationMethod) {
        PreparationMethodEntity entity = findById(preparationMethod.id());
        if (entity == null) {
            throw PreparationMethodErrors.preparationMethodNotFound(preparationMethod.id());
        }
        PreparationMethodMapper.copy(preparationMethod, entity);
    }

    @Override
    public void deletePreparationMethod(Long id) {
        deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return count("name", name) > 0;
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return count("name = ?1 and id <> ?2", name, id) > 0;
    }

    @Override
    public PreparationMethod findPreparationMethodById(Long id) {
        return PreparationMethodMapper.toDomain(findById(id));
    }

    @Override
    public List<PreparationMethod> findAllPreparationMethods() {
        return list("order by timeInMinutes").stream().map(PreparationMethodMapper::toDomain).toList();
    }
}
