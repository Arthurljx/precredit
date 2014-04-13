package xg.precredit.domain.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import xg.framework.domain.AbstractEntity;

/**
 * @Description
 * @author lujx
 * @date   2014-4-13
 * @change
 */
@Entity
@Table(name="sys_privinfo")
public class Privinfo extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 8340725255023756859L;
	
	@Column(name = "priv_name")
	private String privName;
	

	@Column(name = "priv_type")
	private String privType;
	
	@Column(name = "priv_desc")
	private String privDesc;
	
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "priv_level")
	private Integer privLevel;
	
	@Column(name = "status")
	private Integer status;
	
	@ManyToMany(cascade = CascadeType.ALL)  
    @JoinTable(name = "sys_postpriv", inverseJoinColumns = @JoinColumn(name = "id"), 
    joinColumns = @JoinColumn(name = "privId"))  
	private Set<Postinfo> posts = new HashSet<Postinfo>();
	

	public String getPrivName() {
		return privName;
	}

	public void setPrivName(String privName) {
		this.privName = privName;
	}

	public String getPrivType() {
		return privType;
	}

	public void setPrivType(String privType) {
		this.privType = privType;
	}

	public String getPrivDesc() {
		return privDesc;
	}

	public void setPrivDesc(String privDesc) {
		this.privDesc = privDesc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getPrivLevel() {
		return privLevel;
	}

	public void setPrivLevel(Integer privLevel) {
		this.privLevel = privLevel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
