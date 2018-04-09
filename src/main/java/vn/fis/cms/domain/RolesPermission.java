package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ROLES_PERMISSION database table.
 * 
 */
@Entity
@Table(name="ROLES_PERMISSION")
@NamedQuery(name="RolesPermission.findAll", query="SELECT r FROM RolesPermission r")
public class RolesPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLES_PERMISSION_SEQ", sequenceName="ROLES_PERMISSION_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ROLES_PERMISSION_SEQ")
	private long id;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	@JoinColumn(name="PERMISSIONID")
	private Permission permission;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLEID")
	private Role role;

	public RolesPermission() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}