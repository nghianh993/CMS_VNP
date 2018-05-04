package vn.fis.cms.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_permission", schema = "CMS", catalog = "")
public class UserPermission {
    private long id;
    private long userid;
    private long permissionid;
    private boolean group;

    @Id

    @Column(name = "id")
    @SequenceGenerator(name="user_permission_SEQ", sequenceName="user_permission_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="user_permission_SEQ")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid")
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "permissionid")
    public long getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(long permissionid) {
        this.permissionid = permissionid;
    }

    @Basic
    @Column(name = "group")
    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermission that = (UserPermission) o;
        return id == that.id &&
                userid == that.userid &&
                permissionid == that.permissionid &&
                group == that.group;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userid, permissionid, group);
    }
}
