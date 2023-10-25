package com.projectjavaweb.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectjavaweb.course.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order")//No OrderItem tem o id, e do id é que tem o pedido
    private Set<OrderItem> items = new HashSet<>();

    public Order() {
    }

    public Order(Integer id, Instant moment, User client, OrderStatus orderStatus) {
        Id = id;
        this.moment = moment;
        this.client = client;
       setOrderStatus(orderStatus);
    }

    public Integer getId() {
        return Id;
    }

    public Instant getMoment() {
        return moment;
    }

    public User getClient() {
        return client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus!=null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(Id, order.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
