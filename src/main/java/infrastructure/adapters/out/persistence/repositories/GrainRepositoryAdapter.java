package infrastructure.adapters.out.persistence.repositories;

import java.util.List;

import application.ports.out.GrainRepositoryPort;
import domain.exceptions.GrainErrors;
import domain.models.Grain;
import infrastructure.adapters.out.persistence.entities.GrainEntity;
import infrastructure.adapters.out.persistence.mappers.GrainMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GrainRepositoryAdapter implements GrainRepositoryPort, PanacheRepository<GrainEntity> {

    @Override
    public void saveGrain(Grain grain) {
        persist(GrainMapper.toEntity(grain));
    }

    @Override
    public void updateGrain(Grain grain) {
        GrainEntity entity = findById(grain.id());
        if (entity == null) {
            throw GrainErrors.grainNotFound(grain.id());
        }
        GrainMapper.copy(grain, entity);
    }

    @Override
    public void deleteGrain(Long id) {
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
    public Grain findGrainById(Long id) {
        return GrainMapper.toDomain(findById(id));
    }

    @Override
    public List<Grain> findAllGrains() {
        return list("order by name").stream().map(GrainMapper::toDomain).toList();
    }
}
