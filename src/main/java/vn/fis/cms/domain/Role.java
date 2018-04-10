package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Collection;
import java.util.HashSet;

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

	@ManyToMany
	@JoinTable(name = "ROLES_PERMISSION", 
 		joinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID"), 
 		inverseJoinColumns = @JoinColumn(name = "PERMISSIONID", referencedColumnName = "ID"))
	
 	private Collection<Permission> permissions = new HashSet<Permission>();

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
	
	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

}