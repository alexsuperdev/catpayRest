package domiu.dto;

public class Credentials {

    private String UUID;
    private String Username;
    private String Password;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "UUID='" + UUID + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' ;
    }
}
