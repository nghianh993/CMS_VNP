package vn.fis.cms.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="PERMISSION_AUTH")
public class PermissionAuth {

    @Id
    @SequenceGenerator(name="PERMISSION_AUTH_SEQ", sequenceName="PERMISSION_AUTH_SEQ")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="PERMISSION_AUTH_SEQ")
    private int id;
    private String code;
    private String description;
    private Boolean islock;
    private String link;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionAuth that = (PermissionAuth) o;
        return id == that.id &&
                Objects.equals(code, that.code) &&
                Objects.equals(description, that.description) &&
                Objects.equals(islock, that.islock) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, description, islock, link);
    }
}
