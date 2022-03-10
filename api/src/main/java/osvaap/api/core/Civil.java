package osvaap.api.core;

public class Civil {

    private String name;
    private String lastName;
    private String RFC;

    public Civil() {
        name = null;
        lastName = null;
        RFC = null;
    }

    public Civil(String name, String lastName, String RFC) {
        this.name = name;
        this.lastName = lastName;
        this.RFC = RFC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }
}
