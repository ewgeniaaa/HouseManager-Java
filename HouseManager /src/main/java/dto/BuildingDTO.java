package dto;

public class BuildingDTO {
    private long id;
    private String name;
    private String address;

    public BuildingDTO() {
    }

    public BuildingDTO(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
