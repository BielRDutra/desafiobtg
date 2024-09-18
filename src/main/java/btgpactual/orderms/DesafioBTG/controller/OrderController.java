package btgpactual.orderms.DesafioBTG.controller;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import btgpactual.orderms.DesafioBTG.controller.dto.ApiResponse;
import btgpactual.orderms.DesafioBTG.controller.dto.PaginationResponse;
import btgpactual.orderms.DesafioBTG.service.OrderService;



@RestController
public class OrderController {

    private final OrderService orderService;


    public  OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    /*Disponibilizar dados para o cliente */
    @SuppressWarnings("rawtypes")
    @GetMapping("/costumers/{costumerId}/orders")
    public ResponseEntity<ApiResponse> findAllByCostumerId(@PathVariable("costumerID") Long costumerId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "PageSize", defaultValue = "10") Integer PageSize) {


        var pageResponse = orderService.findAllByCustomerId(costumerId, PageRequest.of(page, PageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(costumerId);

        return ResponseEntity.ok(new ApiResponse<>(Map.of("totalOnOrders", totalOnOrders), pageResponse.getContent(), PaginationResponse.fromPage(pageResponse)));
    }
}