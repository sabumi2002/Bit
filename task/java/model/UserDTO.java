package model;

public class UserDTO {  // 회원 DTO
    private int id;
    private String username;
    private String password;
    private String nickname;
    private int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public UserDTO() {

    }

    public UserDTO(UserDTO u) {
        this.id = u.id;
        this.username = u.username;
        this.password = u.password;
        this.nickname = u.nickname;
        this.rank = u.rank;
    }

    public UserDTO(int id) {
        this.id = id;
    }

    public boolean equals(Object o){
        if(o instanceof UserDTO){
            UserDTO u = (UserDTO) o;
            return id==u.id;
        }
        return false;
    }
}

























