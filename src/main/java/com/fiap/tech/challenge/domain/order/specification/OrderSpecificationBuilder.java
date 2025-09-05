package com.fiap.tech.challenge.domain.order.specification;

import com.fiap.tech.challenge.domain.order.dto.OrderFilterInput;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.global.search.enumerated.SearchOperationEnum;
import com.fiap.tech.challenge.global.search.specification.BasicSpecificationBuilder;
import com.fiap.tech.challenge.global.search.specification.SearchCriteria;
import com.fiap.tech.challenge.global.util.ValidationUtil;

public class OrderSpecificationBuilder extends BasicSpecificationBuilder<Order, OrderSpecification, OrderFilterInput> {

    @Override
    protected void initParams(OrderFilterInput filter) {
        if (filter == null) {
            return;
        }

        if (ValidationUtil.isNotNull(filter.getStatus())) {
            where("status", SearchOperationEnum.EQUAL, filter.getStatus());
        }

        if (ValidationUtil.isNotNull(filter.getType())) {
            where("type", SearchOperationEnum.EQUAL, filter.getType());
        }

        if (ValidationUtil.isNotBlank(filter.getRestaurantName())) {
            where("restaurantName", SearchOperationEnum.LIKE, filter.getRestaurantName());
        }

        if (ValidationUtil.isNotBlank(filter.getUserName())) {
            where("userName", SearchOperationEnum.LIKE, filter.getUserName());
        }

        if (ValidationUtil.isNotNull(filter.getFromDate()) && ValidationUtil.isNotNull(filter.getToDate())) {
            where("orderCreationDate", SearchOperationEnum.BETWEEN, filter.getFromDate(), filter.getToDate());
        } else if (ValidationUtil.isNotNull(filter.getFromDate())) {
            where("orderCreationDate", SearchOperationEnum.GREATEST, filter.getFromDate());
        } else if (ValidationUtil.isNotNull(filter.getToDate())) {
            where("orderCreationDate", SearchOperationEnum.LEAST, filter.getToDate());
        }
    }

    @Override
    protected OrderSpecification buildSpecification(String key, SearchOperationEnum operation, Object value) {
        return new OrderSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected OrderSpecification buildSpecification(String key, SearchOperationEnum operation, Object value, Object param) {
        return new OrderSpecification(new SearchCriteria(key, operation, value, param));
    }
}
