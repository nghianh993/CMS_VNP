package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PERMISSION database table.
 * 
 */
@Entity
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERMISSION_SEQ", sequenceName="PERMISSION_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="PERMISSION_SEQ")
	private long id;

	private String code;

	private String description;

	@Column(name="\"LINK\"")
	private String link;

	public Permission() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}