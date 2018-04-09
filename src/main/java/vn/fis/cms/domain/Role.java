package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name="\"ROLES\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLES_SEQ", sequenceName="ROLES_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ROLES_SEQ")
	private long id;

	private String rolename;

	//bi-directional many-to-one association to RolesPermission
	@OneToMany(mappedBy="role")
	private List<RolesPermission> rolesPermissions;

	//bi-directional many-to-one association to UserRoleMapping
	@OneToMany(mappedBy="role")
	private List<UserRoleMapping> userRoleMappings;

	public Role() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<RolesPermission> getRolesPermissions() {
		return this.rolesPermissions;
	}

	public void setRolesPermissions(List<RolesPermission> rolesPermissions) {
		this.rolesPermissions = rolesPermissions;
	}

	public RolesPermission addRolesPermission(RolesPermission rolesPermission) {
		getRolesPermissions().add(rolesPermission);
		rolesPermission.setRole(this);

		return rolesPermission;
	}

	public RolesPermission removeRolesPermission(RolesPermission rolesPermission) {
		getRolesPermissions().remove(rolesPermission);
		rolesPermission.setRole(null);

		return rolesPermission;
	}

	public List<UserRoleMapping> getUserRoleMappings() {
		return this.userRoleMappings;
	}

	public void setUserRoleMappings(List<UserRoleMapping> userRoleMappings) {
		this.userRoleMappings = userRoleMappings;
	}

	public UserRoleMapping addUserRoleMapping(UserRoleMapping userRoleMapping) {
		getUserRoleMappings().add(userRoleMapping);
		userRoleMapping.setRole(this);

		return userRoleMapping;
	}

	public UserRoleMapping removeUserRoleMapping(UserRoleMapping userRoleMapping) {
		getUserRoleMappings().remove(userRoleMapping);
		userRoleMapping.setRole(null);

		return userRoleMapping;
	}

}