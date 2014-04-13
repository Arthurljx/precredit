package xg.precredit.domain.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import xg.framework.domain.AbstractEntity;

@Entity
@Table(name="sys_orginfo")
public class Orginfo extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1093659559394203379L;
	
	@Column(name = "org_id")
	private String orgId;
	
	@Column(name = "org_name")
	private String orgName;
	
	@Column(name = "org_desc")
	private String orgDesc;
	
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "org_level")
	private Integer orgLevel;
	
	@Column(name = "status")
	private Integer status;
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
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
