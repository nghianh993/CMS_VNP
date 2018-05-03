package vn.fis.cms.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "GROUP_PERMISSION", schema = "CMS", catalog = "")
public class GroupPermission {
    private int id;
    private String name;
    private Integer parentid;

    @Id
    @SequenceGenerator(name="GROUP_PERMISSION_SEQ", sequenceName="GROUP_PERMISSION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="GROUP_PERMISSION_SEQ")
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PARENTID")
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupPermission that = (GroupPermission) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentid, that.parentid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, parentid);
    }
}
