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

@Entity
@Table(name="sys_postinfo")
public class Postinfo extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 8886775226159032883L;

	
	@Column(name = "post_name")
	private String postName;
	
	@Column(name = "post_desc")
	private String postDesc;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "posts")
//	private Set<Userinfo> users = new HashSet<Userinfo>();
	
	@ManyToMany(cascade = CascadeType.ALL)  
    @JoinTable(name = "sys_postpriv", inverseJoinColumns = @JoinColumn(name = "privId"), 
    joinColumns = @JoinColumn(name = "id"))  
	private Set<Privinfo> privs = new HashSet<Privinfo>();
	

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
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
