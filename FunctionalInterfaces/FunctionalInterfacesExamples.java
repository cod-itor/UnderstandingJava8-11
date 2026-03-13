package FunctionalInterfaces;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacesExamples {

    @FunctionalInterface
    interface PriceRule {
        double apply(double price);

        default PriceRule andThen(PriceRule next) {
            return price -> next.apply(this.apply(price));
        }

        static PriceRule identity() {
            return p -> p;
        }
    }

    public static void main(String[] args) {
        // Supplier: lazy configuration load
        Supplier<String> configSupplier = () -> "https://api.example.com";
        System.out.println("Config endpoint: " + configSupplier.get());

        // Predicate: validation pipeline
        Predicate<User> isAdult = u -> u.age >= 18;
        Predicate<User> isActive = u -> u.active;
        Predicate<User> hasCompanyEmail = u -> u.email.endsWith("@company.com");
        Predicate<User> canLogin = isAdult.and(isActive).and(hasCompanyEmail);

        List<User> users = Arrays.asList(
            new User("Ana", 22, true, "ana@company.com"),
            new User("Bob", 17, true, "bob@company.com"),
            new User("Cara", 30, false, "cara@company.com"),
            new User("Dan", 28, true, "dan@gmail.com")
        );

        users.stream()
             .filter(canLogin)
             .forEach(u -> System.out.println("Allow login: " + u.name));

        // Function: DTO mapping
        Function<User, UserDTO> toDto = u -> new UserDTO(u.name, u.email);
        Function<UserDTO, User> toEntity = dto -> new User(dto.name(), 0, true, dto.email());
        UserDTO dto = toDto.apply(users.get(0));
        System.out.println("DTO: " + dto);
        System.out.println("Entity (defaulted age/active): " + toEntity.apply(dto));

        // Comparator: chain with thenComparing
        Comparator<Order> byAmountDesc = Comparator.comparing(Order::amount).reversed();
        Comparator<Order> byCreated = Comparator.comparing(Order::createdAtSeconds);

        List<Order> orders = Arrays.asList(
            new Order("A", 120.0, 1700000000),
            new Order("B", 120.0, 1700000100),
            new Order("C", 90.0, 1699999900)
        );

        orders.stream()
              .sorted(byAmountDesc.thenComparing(byCreated))
              .forEach(System.out::println);

        // Custom functional interface with composition
        PriceRule weekendDiscount = p -> p * 0.9;
        PriceRule tax = p -> p * 1.07;
        PriceRule finalPrice = PriceRule.identity().andThen(weekendDiscount).andThen(tax);
        System.out.println("Final price on 100: " + finalPrice.apply(100));

        // BiConsumer: audit logging
        BiConsumer<String, Throwable> auditError = (action, ex) ->
            System.err.println("[AUDIT] action=" + action + ", error=" + ex.getMessage());

        performNetworkCall(() -> {
            throw new IOException("Timeout");
        }, auditError);
    }

    static void performNetworkCall(NetworkCall call, BiConsumer<String, Throwable> audit) {
        try {
            call.run();
        } catch (Exception ex) {
            audit.accept("networkCall", ex);
        }
    }

    @FunctionalInterface
    interface NetworkCall {
        void run() throws Exception;
    }

    record User(String name, int age, boolean active, String email) {}
    record UserDTO(String name, String email) {}
    record Order(String id, double amount, long createdAtSeconds) {}
}
