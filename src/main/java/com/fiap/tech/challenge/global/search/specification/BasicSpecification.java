package com.fiap.tech.challenge.global.search.specification;

import com.fiap.tech.challenge.global.search.enumerated.SearchOperationEnum;
import com.fiap.tech.challenge.global.util.ValidationUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public abstract class BasicSpecification<TYPE> {

    private final transient SearchCriteria criteria;

    public BasicSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    protected Predicate genericPredicate(Root<TYPE> root, CriteriaBuilder builder) {
        boolean isChild = ValidationUtil.isNotBlank(criteria.getKey()) && criteria.getKey().contains(".");

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.EQUAL) && criteria.getValue() instanceof Date) {
            return builder.equal(
                    builder.function("date", Date.class, buildExpressionWithChild(root)),
                    builder.function("date", Date.class, builder.literal(criteria.getValue())));
        }

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.EQUAL)) {
            return builder.equal(buildExpressionWithChild(root), criteria.getValue());
        }

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.EQUAL_IGNORE_CASE)) {
            return builder.like(builder.lower(buildExpressionWithChild(root)), StringUtils.normalizeSpace(String.valueOf(criteria.getValue())).toLowerCase());
        }

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.LIKE)) {
            return builder.like(
                    builder.lower(buildExpressionWithChild(root)),
                    "%" + StringUtils.normalizeSpace(String.valueOf(criteria.getValue())).toLowerCase() + "%"
            );
        }

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.BETWEEN) && criteria.getValue() instanceof Date) {
            return builder.between(
                    builder.function("date", Date.class, buildExpressionWithChild(root)),
                    builder.function("date", Date.class, builder.literal((Date) criteria.getValue())),
                    builder.function("date", Date.class, builder.literal((Date) criteria.getParam())));
        }

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.IN)) {
            return buildExpressionWithChild(root).in((Collection<?>) criteria.getValue());
        }

        if (isChild && criteria.getOperation().equals(SearchOperationEnum.IN_JOIN)) {
            return buildExpressionWithChild(root).in((Collection<?>) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.EQUAL) && criteria.getValue() instanceof Date) {
            return builder.equal(
                    builder.function("date", Date.class, root.get(criteria.getKey())),
                    builder.function("date", Date.class, builder.literal(criteria.getValue())));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.EQUAL)) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.EQUAL_IGNORE_CASE)) {
            return builder.like(builder.lower(root.get(criteria.getKey())), StringUtils.normalizeSpace(String.valueOf(criteria.getValue())).toLowerCase());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.NOT_EQUAL) && criteria.getValue() instanceof Date) {
            return builder.notEqual(
                    builder.function("date", Date.class, root.get(criteria.getKey())),
                    builder.function("date", Date.class, builder.literal(criteria.getValue())));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.NOT_EQUAL)) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.IS_NULL)) {
            return builder.isNull(root.get(criteria.getKey()));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.IS_NOT_NULL)) {
            return builder.isNotNull(root.get(criteria.getKey()));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.LIKE)) {
            return builder.like(builder.lower(root.get(criteria.getKey())), "%" + StringUtils.normalizeSpace(String.valueOf(criteria.getValue())).toLowerCase() + "%");
        }

        if (criteria.getOperation().equals(SearchOperationEnum.GREATEST) && criteria.getValue() instanceof Date) {
            return builder.greaterThanOrEqualTo(
                    builder.function("date", Date.class, root.get(criteria.getKey())),
                    builder.function("date", Date.class, builder.literal((Date) criteria.getValue())));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.GREATEST) && criteria.getValue() instanceof Long) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Long) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.GREATEST) && criteria.getValue() instanceof BigDecimal) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (BigDecimal) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.LEAST) && criteria.getValue() instanceof Date) {
            return builder.lessThanOrEqualTo(
                    builder.function("date", Date.class, root.get(criteria.getKey())),
                    builder.function("date", Date.class, builder.literal((Date) criteria.getValue())));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.LEAST) && criteria.getValue() instanceof Long) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Long) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.LEAST) && criteria.getValue() instanceof BigDecimal) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (BigDecimal) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.BETWEEN) && criteria.getValue() instanceof Date) {
            return builder.between(
                    builder.function("date", Date.class, root.get(criteria.getKey())),
                    builder.function("date", Date.class, builder.literal((Date) criteria.getValue())),
                    builder.function("date", Date.class, builder.literal((Date) criteria.getParam())));
        }

        if (criteria.getOperation().equals(SearchOperationEnum.BETWEEN) && criteria.getValue() instanceof Long) {
            return builder.between(root.get(criteria.getKey()), (Long) criteria.getValue(), (Long) criteria.getParam());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.BETWEEN) && criteria.getValue() instanceof BigDecimal) {
            return builder.between(root.get(criteria.getKey()), (BigDecimal) criteria.getValue(), (BigDecimal) criteria.getParam());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.IN)) {
            return root.get(criteria.getKey()).in((Collection<?>) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperationEnum.IN_JOIN)) {
            return root.join(criteria.getKey()).in((Collection<?>) criteria.getValue());
        }

        return null;
    }

    private <Y> Path<Y> buildExpressionWithChild(Root<TYPE> root) {
        String[] params = criteria.getKey().split("\\.");
        Path<Y> expression = root.join(params[0]);
        for (String param : params) {
            if (Objects.equals(param, params[0])) continue;
            expression = expression.get(param);
        }
        return expression;
    }
}
