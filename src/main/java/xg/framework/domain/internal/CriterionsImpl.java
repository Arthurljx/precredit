package xg.framework.domain.internal;

import java.util.Collection;

import xg.framework.domain.Criterions;
import xg.framework.domain.QueryCriterion;



/**
 * 一个工具类，作为各种查询条件的工厂
 * @author yyang (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 *
 */
public class CriterionsImpl extends Criterions {

	public CriterionsImpl() {
	}

	public QueryCriterion eq(String propName, Object value) {
		return new EqCriterion(propName, value);
	}
	
	public QueryCriterion notEq(String propName, Object value) {
		return new NotEqCriterion(propName, value);
	}
	
	public QueryCriterion ge(String propName, Comparable<?> value) {
		return new GeCriterion(propName, value);
	}
	
	public QueryCriterion gt(String propName, Comparable<?> value) {
		return new GtCriterion(propName, value);
	}
	
	public QueryCriterion le(String propName, Comparable<?> value) {
		return new LeCriterion(propName, value);
	}
	
	public QueryCriterion lt(String propName, Comparable<?> value) {
		return new LtCriterion(propName, value);
	}
	
	public QueryCriterion eqProp(String propName1, String propName2) {
		return new EqPropCriterion(propName1, propName2);
	}
	
	public QueryCriterion notEqProp(String propName1, String propName2) {
		return new NotEqPropCriterion(propName1, propName2);
	}
	
	public QueryCriterion gtProp(String propName1, String propName2) {
		return new GtPropCriterion(propName1, propName2);
	}
	
	public QueryCriterion geProp(String propName1, String propName2) {
		return new GePropCriterion(propName1, propName2);
	}
	
	public QueryCriterion ltProp(String propName1, String propName2) {
		return new LtPropCriterion(propName1, propName2);
	}
	
	public QueryCriterion leProp(String propName1, String propName2) {
		return new LePropCriterion(propName1, propName2);
	}
	
	public QueryCriterion sizeEq(String propName, int size) {
		return new SizeEqCriterion(propName, size);
	}
	
	public QueryCriterion sizeNotEq(String propName, int size) {
		return new SizeNotEqCriterion(propName, size);
	}
	
	public QueryCriterion sizeGt(String propName, int size) {
		return new SizeGtCriterion(propName, size);
	}
	
	public QueryCriterion sizeGe(String propName, int size) {
		return new SizeGeCriterion(propName, size);
	}
	
	public QueryCriterion sizeLt(String propName, int size) {
		return new SizeLtCriterion(propName, size);
	}
	
	public QueryCriterion sizeLe(String propName, int size) {
		return new SizeLeCriterion(propName, size);
	}

	public QueryCriterion containsText(String propName, String value) {
		return new ContainsTextCriterion(propName, value);
	}

	public QueryCriterion startsWithText(String propName, String value) {
		return new StartsWithTextCriterion(propName, value);
	}

	public QueryCriterion in(String propName, Collection<?> value) {
		return new InCriterion(propName, value);
	}

	public QueryCriterion in(String propName, Object[] value) {
		return new InCriterion(propName, value);
	}

	public QueryCriterion notIn(String propName, Collection<?> value) {
		return new NotInCriterion(propName, value);
	}

	public QueryCriterion notIn(String propName, Object[] value) {
		return new NotInCriterion(propName, value);
	}

	public QueryCriterion between(String propName, Comparable<?> from, Comparable<?> to) {
		return new BetweenCriterion(propName, from, to);
	}
	
	public QueryCriterion isNull(String propName) {
		return new IsNullCriterion(propName);
	}
	
	public QueryCriterion notNull(String propName) {
		return new NotNullCriterion(propName);
	}
	
	public QueryCriterion isEmpty(String propName) {
		return new IsEmptyCriterion(propName);
	}
	
	public QueryCriterion notEmpty(String propName) {
		return new NotEmptyCriterion(propName);
	}
	
	public QueryCriterion not(QueryCriterion criterion) {
		return new NotCriterion(criterion);
	}
	
	public QueryCriterion and(QueryCriterion... criterions) {
		return new AndCriterion(criterions);
	}
	
	public QueryCriterion or(QueryCriterion... criterions) {
		return new OrCriterion(criterions);
	}
	
}
