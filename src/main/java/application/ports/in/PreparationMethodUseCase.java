package application.ports.in;

public interface PreparationMethodUseCase {
    public void createPreparationMethod(String name, String description, Integer timeInMinutes);
    public void updatePreparationMethod(Long id, String name, String description, Integer timeInMinutes);
    public void deletePreparationMethod(Long id);
}
