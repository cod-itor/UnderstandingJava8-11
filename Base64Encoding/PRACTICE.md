# Base64 - Practice Pack

Use `Base64Encoding/Base64Examples.java` for solutions.

## How to run

- Open `Base64Examples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Encode text**: Encode "hello" with basic encoder; print.
2. **Decode text**: Decode the result from #1; print.
3. **URL-safe**: Encode "user:42" with `getUrlEncoder().withoutPadding()`; print.
4. **Binary bytes**: Encode `new byte[]{1,2,3,4}` and print length.
5. **Round-trip**: Encode then decode a string with UTF-8 and assert equals.
6. **Auth header**: Build `"Basic " + Base64.getEncoder().encodeToString("u:p".getBytes(UTF_8))`; print.
7. **MIME**: Use `getMimeEncoder()` to encode a long string (100 chars) and show line breaks.
8. **Error handling**: Attempt to decode an invalid string; catch `IllegalArgumentException` and print message.
9. **Stream wrap**: Wrap a `ByteArrayOutputStream` with `Base64.getEncoder().wrap` and write bytes; print encoded result.
10. **File-like**: Simulate encoding a byte[] payload, then decoding and checking original length.

## Mini Project

**Token Generator**: Build a method `String token(String userId)` that:

- Creates payload `userId + ":" + Instant.now()` bytes
- Encodes with URL encoder, no padding
- Returns the token
  Add a verifier that decodes, splits on `:`, and prints the pieces.
