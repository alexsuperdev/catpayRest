package domiu.dto;

public class Client {

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        this.Version = version;
    }

    private String Name;
    private String Version;

    @Override
    public String toString() {
        return "Client{" +
                "Name='" + Name + '\'' +
                ", Version='" + Version + '\'' +
                '}';
    }
}
