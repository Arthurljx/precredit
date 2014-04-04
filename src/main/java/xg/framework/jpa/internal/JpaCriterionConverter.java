package xg.framework.jpa.internal;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import xg.framework.domain.QueryCriterion;
import xg.framework.domain.internal.AndCriterion;
import xg.framework.domain.internal.BetweenCriterion;
import xg.framework.domain.internal.ContainsTextCriterion;
import xg.framework.domain.internal.EqCriterion;
import xg.framework.domain.internal.EqPropCriterion;
import xg.framework.domain.internal.GeCriterion;
import xg.framework.domain.internal.GePropCriterion;
import xg.framework.domain.internal.GtCriterion;
import xg.framework.domain.internal.GtPropCriterion;
import xg.framework.domain.internal.InCriterion;
import xg.framework.domain.internal.IsEmptyCriterion;
import xg.framework.domain.internal.IsNullCriterion;
import xg.framework.domain.internal.LeCriterion;
import xg.framework.domain.internal.LePropCriterion;
import xg.framework.domain.internal.LtCriterion;
import xg.framework.domain.internal.LtPropCriterion;
import xg.framework.domain.internal.NotCriterion;
import xg.framework.domain.internal.NotEmptyCriterion;
import xg.framework.domain.internal.NotEqCriterion;
import xg.framework.domain.internal.NotEqPropCriterion;
import xg.framework.domain.internal.NotInCriterion;
import xg.framework.domain.internal.NotNullCriterion;
import xg.framework.domain.internal.OrCriterion;
import xg.framework.domain.internal.SizeEqCriterion;
import xg.framework.domain.internal.SizeGeCriterion;
import xg.framework.domain.internal.SizeGtCriterion;
import xg.framework.domain.internal.SizeLeCriterion;
import xg.framework.domain.internal.SizeLtCriterion;
import xg.framework.domain.internal.SizeNotEqCriterion;
import xg.framework.domain.internal.StartsWithTextCriterion;


/**
 * 一个工具类,用于将QueryCriterion转换成Jpa的Criterion
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JpaCriterionConverter {

	private CriteriaBuilder builder;
	private Root root;

	public JpaCriterionConverter(CriteriaBuilder builder, Root root) {
		super();
		this.builder = builder;
		this.root = root;
	}

	public Predicate convert(QueryCriterion criterion) {
		if (criterion instanceof EqCriterion) {
			Path path = getPropPath(root, ((EqCriterion) criterion).getPropName());
			return builder.equal(path, ((EqCriterion) criterion).getValue());
		}
		if (criterion instanceof NotEqCriterion) {
			Path path = getPropPath(root, ((NotEqCriterion) criterion).getPropName());
			return builder.notEqual(path, ((NotEqCriterion) criterion).getValue());
		}
		if (criterion instanceof GtCriterion) {
			GtCriterion theCriteron = (GtCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			Comparable value = theCriteron.getValue();
			return builder.greaterThan(path, value);
		}
		if (criterion instanceof GeCriterion) {
			GeCriterion theCriteron = (GeCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			Comparable value = theCriteron.getValue();
			return builder.greaterThanOrEqualTo(path, value);
		}
		if (criterion instanceof LtCriterion) {
			LtCriterion theCriteron = (LtCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			Comparable value = theCriteron.getValue();
			return builder.lessThan(path, value);
		}
		if (criterion instanceof LeCriterion) {
			LeCriterion theCriteron = (LeCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			Comparable value = theCriteron.getValue();
			return builder.lessThanOrEqualTo(path, value);
		}
		if (criterion instanceof EqPropCriterion) {
			EqPropCriterion theCriteron = (EqPropCriterion) criterion;
			Path path1 = getPropPath(root, theCriteron.getPropName1());
			Path path2 = getPropPath(root, theCriteron.getPropName2());
			return builder.equal(path1, path2);
		}
		if (criterion instanceof NotEqPropCriterion) {
			NotEqPropCriterion theCriteron = (NotEqPropCriterion) criterion;
			Path path1 = getPropPath(root, theCriteron.getPropName1());
			Path path2 = getPropPath(root, theCriteron.getPropName2());
			return builder.notEqual(path1, path2);
		}
		if (criterion instanceof GtPropCriterion) {
			GtPropCriterion theCriteron = (GtPropCriterion) criterion;
			Path path1 = getPropPath(root, theCriteron.getPropName1());
			Path path2 = getPropPath(root, theCriteron.getPropName2());
			return builder.greaterThan(path1, path2);
		}
		if (criterion instanceof GePropCriterion) {
			GePropCriterion theCriteron = (GePropCriterion) criterion;
			Path path1 = getPropPath(root, theCriteron.getPropName1());
			Path path2 = getPropPath(root, theCriteron.getPropName2());
			return builder.greaterThanOrEqualTo(path1, path2);
		}
		if (criterion instanceof LtPropCriterion) {
			LtPropCriterion theCriteron = (LtPropCriterion) criterion;
			Path path1 = getPropPath(root, theCriteron.getPropName1());
			Path path2 = getPropPath(root, theCriteron.getPropName2());
			return builder.lessThan(path1, path2);
		}
		if (criterion instanceof LePropCriterion) {
			LePropCriterion theCriteron = (LePropCriterion) criterion;
			Path path1 = getPropPath(root, theCriteron.getPropName1());
			Path path2 = getPropPath(root, theCriteron.getPropName2());
			return builder.lessThanOrEqualTo(path1, path2);
		}
		if (criterion instanceof SizeEqCriterion) {
			SizeEqCriterion theCriteron = (SizeEqCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.equal(builder.size(path), theCriteron.getValue());
		}
		if (criterion instanceof SizeNotEqCriterion) {
			SizeNotEqCriterion theCriteron = (SizeNotEqCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.notEqual(builder.size(path), theCriteron.getValue());
		}
		if (criterion instanceof SizeGtCriterion) {
			SizeGtCriterion theCriteron = (SizeGtCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.gt(builder.size(path), theCriteron.getValue());
		}
		if (criterion instanceof SizeGeCriterion) {
			SizeGeCriterion theCriteron = (SizeGeCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.ge(builder.size(path), theCriteron.getValue());
		}
		if (criterion instanceof SizeLtCriterion) {
			SizeLtCriterion theCriteron = (SizeLtCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.lt(builder.size(path), theCriteron.getValue());
		}
		if (criterion instanceof SizeLeCriterion) {
			SizeLeCriterion theCriteron = (SizeLeCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.le(builder.size(path), theCriteron.getValue());
		}
		if (criterion instanceof StartsWithTextCriterion) {
			StartsWithTextCriterion theCriteron = (StartsWithTextCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.like(path, theCriteron.getValue() + "%");
		}
		if (criterion instanceof ContainsTextCriterion) {
			ContainsTextCriterion theCriteron = (ContainsTextCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.like(path, "%" + theCriteron.getValue() + "%");
		}
		if (criterion instanceof BetweenCriterion) {
			BetweenCriterion theCriteron = (BetweenCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			Comparable from = theCriteron.getFrom();
			Comparable to = theCriteron.getTo();
			return builder.between(path, from, to);
		}
		if (criterion instanceof InCriterion) {
			InCriterion theCriteron = (InCriterion) criterion;
			Collection<? extends Object> value = theCriteron.getValue();
			if (value == null || value.isEmpty()) {
				return builder.isTrue(builder.literal(false));
			}
			Path path = getPropPath(root, theCriteron.getPropName());
			return path.in(value);
		}
		if (criterion instanceof NotInCriterion) {
			NotInCriterion theCriteron = (NotInCriterion) criterion;
			Collection<? extends Object> value = theCriteron.getValue();
			if (value == null || value.isEmpty()) {
				return builder.isTrue(builder.literal(true));
			}
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.not(path.in(value));
		}
		if (criterion instanceof IsNullCriterion) {
			IsNullCriterion theCriteron = (IsNullCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.isNull(path);
		}
		if (criterion instanceof NotNullCriterion) {
			NotNullCriterion theCriteron = (NotNullCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.isNotNull(path);
		}
		if (criterion instanceof IsEmptyCriterion) {
			IsEmptyCriterion theCriteron = (IsEmptyCriterion) criterion;
			Path path = getPropPath(root, theCriteron.getPropName());
			return builder.isEmpty(path);
		}
		if (criterion instanceof NotEmptyCriterion) {
			NotEmptyCriterion isEmptyCriteron = (NotEmptyCriterion) criterion;
			Path propName = root.get(isEmptyCriteron.getPropName());
			Class propType = propName.getJavaType();
			return builder.isNotEmpty(propName.as(propType));
		}
		if (criterion instanceof NotCriterion) {
			return builder.not(convert(((NotCriterion) criterion).getCriteron()));
		}
		if (criterion instanceof AndCriterion) {
			AndCriterion andCriterion = (AndCriterion) criterion;
			QueryCriterion[] criterions = andCriterion.getCriterons();
			Predicate predicate = convert(criterions[0]);
			for (int i = 1; i < criterions.length; i++) {
				predicate = builder.and(predicate, convert(criterions[i]));
			}
			return predicate;
		}
		if (criterion instanceof OrCriterion) {
			OrCriterion orCriterion = (OrCriterion) criterion;
			QueryCriterion[] criterions = orCriterion.getCriterons();
			Predicate predicate = convert(criterions[0]);
			for (int i = 1; i < criterions.length; i++) {
				predicate = builder.or(predicate, convert(criterions[i]));
			}
			return predicate;
		}
		return null;
	}

	private Path<?> getPropPath(Root<?> root, String propName) {
		String[] props = propName.split("\\.");
		Path<?> path = root;
		for (int j = 0; j < props.length; j++) {
			path = path.get(props[j]);
		}
		return path;
	}
}
