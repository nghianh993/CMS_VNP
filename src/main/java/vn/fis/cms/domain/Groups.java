package vn.fis.cms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Groups {
    private int id;
    private Integer groupid;
    private String permission;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GROUPID")
    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    @Basic
    @Column(name = "PERMISSION")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return id == groups.id &&
                Objects.equals(groupid, groups.groupid) &&
                Objects.equals(permission, groups.permission);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, groupid, permission);
    }
}
