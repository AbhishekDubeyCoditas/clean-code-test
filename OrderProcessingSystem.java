import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private int orderId;
    private List<Product> products;

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void cancelOrder() {
        products.clear();
    }

    public List<Product> getProducts() {
        return products;
    }
}

class OrderProcessingSystem {
    private List<Product> products;
    private List<Order> orders;
    private int orderIdCounter;

    public OrderProcessingSystem() {
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.orderIdCounter = 1;
    }

    public void addProduct(String name, double price) {
        products.add(new Product(name, price));
    }

    public void removeProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
    }

    public int placeOrder() {
        Order order = new Order(orderIdCounter);
        orders.add(order);
        orderIdCounter++;
        return order.getOrderId();
    }

    public void cancelOrder(int orderId) {
        Order orderToCancel = orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
        if (orderToCancel != null) {
            orderToCancel.cancelOrder();
        }
    }

    public void listAllProducts() {
        for (Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }

    public void listAllOrders() {
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            List<Product> products = order.getProducts();
            if (products.isEmpty()) {
                System.out.println("No products in this order.");
            } else {
                for (Product product : products) {
                    System.out.println("- " + product.getName() + " - $" + product.getPrice());
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OrderProcessingSystem system = new OrderProcessingSystem();

        system.addProduct("Product A", 10.0);
        system.addProduct("Product B", 20.0);

        int orderId = system.placeOrder();
        Order order = system.getOrder(orderId);
        order.addProduct(system.getProduct("Product A"));

        orderId = system.placeOrder();
        order = system.getOrder(orderId);
        order.addProduct(system.getProduct("Product B"));
        order.addProduct(system.getProduct("Product A"));

        system.listAllProducts();
        system.listAllOrders();
    }
}
