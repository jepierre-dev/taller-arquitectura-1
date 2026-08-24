package application.ports.out;

import domain.models.Grain;

public interface GrainRepositoryPort {
    public void saveGrain(Grain grain);
    public void updateGrain(Grain grain);
    public void deleteGrain(Long id);

    public boolean existsByName(String name);

    public Grain findGrainById(Long id);
}
