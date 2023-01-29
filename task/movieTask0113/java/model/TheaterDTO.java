package model;

public class TheaterDTO { // 극장DTO
    private int id;
    private String thaterName;
    private String location;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThaterName() {
        return thaterName;
    }

    public void setThaterName(String thaterName) {
        this.thaterName = thaterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public TheaterDTO(){}
    public TheaterDTO(TheaterDTO t){
        this.id = t.id;
        this.thaterName = t.thaterName;
        this.location = t.location;
        this.phoneNumber = t.phoneNumber;
    }
    public TheaterDTO(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof TheaterDTO){
            TheaterDTO t = (TheaterDTO) o;
            return id == t.id;
        }
        return false;
    }
}










