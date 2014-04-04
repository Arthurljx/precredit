package xg.framework.domain.internal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import xg.framework.domain.QueryCriterion;
import xg.framework.domain.QueryException;


public class SizeNotEqCriterion implements QueryCriterion {

	private String propName;
	private int value;

	public SizeNotEqCriterion(String propName, int value) {
		if (StringUtils.isEmpty(propName)) {
			throw new QueryException("Property name is null!");
		}
		this.propName = propName;
		this.value = value;
	}

	public String getPropName() {
		return propName;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SizeNotEqCriterion)) {
			return false;
		}
		SizeNotEqCriterion castOther = (SizeNotEqCriterion) other;
		return new EqualsBuilder().append(this.getPropName(), castOther.getPropName()).append(value, castOther.value)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getPropName()).append(value).toHashCode();
	}

	@Override
	public String toString() {
		return "size of " + getPropName() + " != " + value;
	}
}
