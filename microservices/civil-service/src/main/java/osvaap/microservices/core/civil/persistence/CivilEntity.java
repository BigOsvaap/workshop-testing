package osvaap.microservices.core.civil.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "civilians")
public class CivilEntity {

    @Id private String id;
    @Version private Integer version;
    private String name;
    private String lastName;
    @Indexed(unique = true) private String RFC;

    public CivilEntity() {}

    public CivilEntity(String name, String lastName, String RFC) {
        this.name = name;
        this.lastName = lastName;
        this.RFC = RFC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
