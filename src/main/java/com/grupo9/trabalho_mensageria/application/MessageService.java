package com.grupo9.trabalho_mensageria.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo9.trabalho_mensageria.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MessageService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderItemService orderItemService;


    public Boolean processMessage(String message) {
        try {
            System.out.println("[MessageService] Mensagem recebida: " + message);
            JsonNode root = objectMapper.readTree(message);
            if (root.isArray()) {
                for (JsonNode node : root) {
                   processOrder(node);
                }
            } else {
                processOrder(root);
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error while processing message: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Order processOrder(JsonNode node) {

        Order order = new Order();
        order.setUuid(UUID.fromString(node.path("uuid").asText()));
        order.setChannel(node.path("channel").asText());

        String createdAtStr = node.path("created_at").asText();
        if (!createdAtStr.isEmpty()) {
            order.setCreatedAt(Date.from(java.time.OffsetDateTime.parse(createdAtStr).toInstant()));
        }

        order.setStatus(node.path("status").asText());

        order.setProcessedAt(new Date());

        Map<String, Object> shipment = objectMapper.convertValue(node.path("shipment"), Map.class);
        order.setShipment(shipment);

        Map<String, Object> payment = objectMapper.convertValue(node.path("payment"), Map.class);
        order.setPayment(payment);

        Map<String, Object> metadata = objectMapper.convertValue(node.path("metadata"), Map.class);
        order.setMetadata(metadata);

        Customer customer = processCustomer(node.path("customer"));
        order.setCustomer(customer);

        Seller seller = processSeller(node.path("seller"));
        order.setSeller(seller);

        JsonNode itemsNode = node.path("items");
        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();
        for (JsonNode itemNode : itemsNode){
            OrderItem item = processItem(itemNode);
            items.add(item);
            total = total.add(item.getTotal());
        }

        order.setTotal(node.path("total").isMissingNode() ? total : BigDecimal.valueOf(node.path("total").asDouble()));

        Order savedOrder = orderService.save(order);
        saveOrderItems(items, savedOrder);
        System.out.println("[MessageService] Pedido processado com sucesso: " + savedOrder.getUuid());
        return savedOrder;
    }

    private Customer processCustomer(JsonNode customerNode) {
        Customer customer = new Customer();
        customer.setId(customerNode.path("id").asInt());
        customer.setName(customerNode.path("name").asText());
        customer.setDocument(customerNode.path("document").asText());
        customer.setEmail(customerNode.path("email").asText());
        return customerService.save(customer);
    }

    private Seller processSeller(JsonNode sellerNode) {
        Seller seller = new Seller();

        seller.setId(sellerNode.path("id").asInt());
        seller.setName(sellerNode.path("name").asText());
        seller.setCity(sellerNode.path("city").asText());
        seller.setState(sellerNode.path("state").asText());

        return sellerService.save(seller);
    }

    private OrderItem processItem (JsonNode itemNode) {
        OrderItem item = new OrderItem();
        item.setId(itemNode.path("id").asInt());
        item.setProduct(processProduct(itemNode));

        Integer quantity = itemNode.path("quantity").asInt();
        item.setQuantity(quantity);

        BigDecimal unitPrice = BigDecimal.valueOf(itemNode.path("unit_price").asDouble());
        item.setUnitPrice(unitPrice);

        BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        item.setTotal(itemTotal);

        return item;
    }



    private List<OrderItem> saveOrderItems(List<OrderItem> orderItems, Order order) {
        List<OrderItem> saved = new ArrayList<>();
        for (OrderItem item : orderItems) {
            item.setOrder(order);
            saved.add(orderItemService.save(item));
        }
        return saved;
    }

    private Product processProduct(JsonNode productNode) {
        Product product = new Product();
        product.setId(productNode.path("product_id").asInt());
        product.setName(productNode.path("product_name").asText());
        JsonNode productCategoryNode = productNode.path("category");
        product.setCategory(processCategory(productCategoryNode));
        return productService.save(product);
    }


    private Category processCategory (JsonNode categoryNode) {
        Category category = new Category();
        category.setId(categoryNode.path("id").asText());
        category.setName(categoryNode.path("name").asText());

        Category categorySave = categoryService.save(category);

        JsonNode subCategoryNode = categoryNode.path("sub_category");
        Category subCategory = new Category();

        subCategory.setId(subCategoryNode.path("id").asText());
        subCategory.setName(subCategoryNode.path("name").asText());
        subCategory.setParent(categorySave);

        return categoryService.save(subCategory);
    }
}
