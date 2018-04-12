package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;


/**
 * The persistent class for the "ACTION" database table.
 * 
 */
@Entity
@Table(name="\"ACTION\"")
@NamedQuery(name="Action.findAll", query="SELECT a FROM Action a")
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal islock;

	@Column(name="\"LINK\"")
	private String link;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "PERMISTION_ACTION_MAPPING", 
 		joinColumns = @JoinColumn(name = "ACTIONID", referencedColumnName = "ID"), 
 		inverseJoinColumns = @JoinColumn(name = "PERMISSIONID", referencedColumnName = "ID"))
	private Collection<Permission> permissions = new HashSet<Permission>();

	public Action() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getIslock() {
		return this.islock;
	}

	public void setIslock(BigDecimal islock) {
		this.islock = islock;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}
}