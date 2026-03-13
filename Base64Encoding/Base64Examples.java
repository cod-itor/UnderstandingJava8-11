package Base64Encoding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class Base64Examples {
    public static void main(String[] args) {
        String secret = "token-123";

        // Basic encoder/decoder
        String encoded = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);

        // URL-safe encoder (no + or /)
        String urlToken = Base64.getUrlEncoder().withoutPadding()
            .encodeToString("user:42".getBytes(StandardCharsets.UTF_8));
        System.out.println("URL token: " + urlToken);

        // HTTP Basic Auth header
        String credentials = "user:password";
        String basicHeader = "Basic " + Base64.getEncoder()
            .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        System.out.println("Auth header: " + basicHeader);

        // Binary data example (pretend payload)
        byte[] payload = new byte[] {1, 2, 3, 4, 5};
        String payloadB64 = Base64.getEncoder().encodeToString(payload);
        byte[] roundTrip = Base64.getDecoder().decode(payloadB64);
        System.out.println("Payload b64: " + payloadB64 + " | bytes length: " + roundTrip.length);

        // Registry of encoders for dependency injection pattern
        Map<String, Base64.Encoder> encoders = Map.of(
            "basic", Base64.getEncoder(),
            "url", Base64.getUrlEncoder(),
            "mime", Base64.getMimeEncoder()
        );
        System.out.println("Available encoders: " + encoders.keySet());
    }
}
