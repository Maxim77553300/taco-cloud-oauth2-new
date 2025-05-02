package org.example.tacocloud.repository;

import org.example.tacocloud.domain.TacoOrder;
import org.example.tacocloud.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepositoryJpa extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date strartdate, Date endDate);

    List<TacoOrder> findByDeliveryStreetAndDeliveryCityAllIgnoreCase(String deliveryTo, String deliveryCity);

    List<TacoOrder> findByDeliveryCityOrderByDeliveryStreet(String deliveryCity);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

//    @Query("Order o where o.delivery='Seattle'")?
//    List<TacoOrder> readOrdersDeliveredInSeattle();
}
