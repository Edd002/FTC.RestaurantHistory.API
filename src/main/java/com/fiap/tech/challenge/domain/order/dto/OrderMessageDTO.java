package com.fiap.tech.challenge.domain.order.dto;

import com.fiap.tech.challenge.domain.menuitem.dto.MenuItemMessageDTO;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessageDTO {
    private String hashId;
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private List<MenuItemMessageDTO> orderItems;
    private Date createdDate;
    private Date deliveredDate;
    private String restaurantName;
    private String userName;

    public void setDeliveredDate(Date deliveredDate) {
        if(OrderStatusEnum.DELIVERED.equals(this.status)) {
            this.deliveredDate = deliveredDate;
        }
    }
}
