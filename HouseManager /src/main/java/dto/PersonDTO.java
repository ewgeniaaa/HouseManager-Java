package dto;

public class PersonDTO {
    private long id;
    private String name;
    private int age;
    private boolean useLift;

    public PersonDTO() {
    }

    public PersonDTO(long id, String name, int age, boolean useLift) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.useLift = useLift;
    }
}
