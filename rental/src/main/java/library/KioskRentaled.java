package library;

public class KioskRentaled extends AbstractEvent {

    private Long id;

    public KioskRentaled(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}