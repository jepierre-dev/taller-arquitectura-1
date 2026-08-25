package application.ports.out;

import java.util.List;

import domain.models.Grain;

public interface GrainRepositoryPort {
    public void saveGrain(Grain grain);
    public void updateGrain(Grain grain);
    public void deleteGrain(Long id);

    public boolean existsByName(String name);
    public boolean existsByNameAndIdNot(String name, Long id);

    public Grain findGrainById(Long id);
    public List<Grain> findAllGrains();

}
