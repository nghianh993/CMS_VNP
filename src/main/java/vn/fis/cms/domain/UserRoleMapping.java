package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_ROLE_MAPPING database table.
 * 
 */
@Entity
@Table(name="USER_ROLE_MAPPING")
@NamedQuery(name="UserRoleMapping.findAll", query="SELECT u FROM UserRoleMapping u")
public class UserRoleMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_ROLE_MAPPING_SEQ", sequenceName="USER_ROLE_MAPPING_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USER_ROLE_MAPPING_SEQ")
	private long id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLEID")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public UserRoleMapping() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}