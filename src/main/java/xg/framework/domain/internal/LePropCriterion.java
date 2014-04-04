package xg.framework.domain.internal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import xg.framework.domain.QueryCriterion;
import xg.framework.domain.QueryException;


public class LePropCriterion implements QueryCriterion {
	
	private String propName1;
	private String propName2;

	public LePropCriterion(String propName1, String propName2) {
		if (StringUtils.isEmpty(propName1) || StringUtils.isEmpty(propName2)) {
			throw new QueryException("One of property name is null!");
		}
		this.propName1 = propName1;
		this.propName2 = propName2;
	}

	public String getPropName1() {
		return propName1;
	}

	public String getPropName2() {
		return propName2;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LePropCriterion)) {
			return false;
		}
		LePropCriterion castOther = (LePropCriterion) other;
		return new EqualsBuilder()
			.append(this.getPropName1(), castOther.getPropName1())
			.append(propName2, castOther.propName2).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getPropName1()).append(propName2).toHashCode();
	}

	@Override
	public String toString() {
		return getPropName1() + " <= " + propName2;
	}

	
}
