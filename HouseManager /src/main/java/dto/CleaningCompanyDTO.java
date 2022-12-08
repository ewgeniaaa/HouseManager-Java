package dto;

public class CleaningCompanyDTO {
    private long id;
    private String name;
    private double capital;

    public CleaningCompanyDTO() {
    }

    public CleaningCompanyDTO(long id, String name, double capital) {
        this.id = id;
        this.name = name;
        this.capital = capital;
    }
}
