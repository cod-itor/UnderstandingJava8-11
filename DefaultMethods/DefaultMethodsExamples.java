package DefaultMethods;

import java.util.Objects;

public class DefaultMethodsExamples {
    public static void main(String[] args) {
        EmailFormatter formatter = new EmailFormatter();
        System.out.println(formatter.trimAndFormat("   hello@example.com  "));

        ResilientService service = new ResilientService();
        service.execute();
        service.executeWithAudit();
    }

    interface Formatter {
        String format(String input);

        default String trimAndFormat(String input) {
            String safe = Objects.toString(input, "").trim();
            return format(safe);
        }
    }

    static class EmailFormatter implements Formatter {
        @Override
        public String format(String input) {
            return input.toLowerCase();
        }
    }

    interface Retryable {
        default void withRetry(Runnable action) {
            try {
                action.run();
            } catch (Exception ex) {
                System.err.println("Retrying once: " + ex.getMessage());
                action.run();
            }
        }
    }

    interface Auditable {
        default void audit(String message) {
            System.out.println("[AUDIT] " + message);
        }
    }

    static class ResilientService implements Retryable, Auditable {
        void execute() {
            withRetry(() -> {
                audit("executing risky operation");
                System.out.println("Work done");
            });
        }

        // Resolves ambiguity if Retryable and Auditable had same default name (they don't here)
        void executeWithAudit() {
            Auditable.super.audit("before executeWithAudit");
            execute();
        }
    }
}
