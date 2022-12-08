package dto;

public class ApartmentDTO {
    private long id;
    private int floor;
    private double area;
    private boolean hasPet;
    private double fee;

    public ApartmentDTO() {
    }

    public ApartmentDTO(long id, int floor, double area, boolean hasPet, double fee) {
        this.id = id;
        this.floor = floor;
        this.area = area;
        this.hasPet = hasPet;
        this.fee = fee;
    }
}
