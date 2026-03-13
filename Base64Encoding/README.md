# Base64 Encoding/Decoding - Java 8

## 🔑 API
- `Base64.getEncoder()` / `Base64.getDecoder()`
- Variants: `getUrlEncoder()`, `getMimeEncoder()`

---

## 🛠️ Basic Usage
```java
String original = "hello";
String encoded = Base64.getEncoder().encodeToString(original.getBytes(StandardCharsets.UTF_8));
String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
```

---

## 🌍 Real-World Examples
- Encoding binary payloads for JSON transport
- HTTP Basic Auth headers (`Basic base64(username:password)`)
- URL-safe tokens (`getUrlEncoder()` avoids `+` and `/`)
- Email attachments (MIME encoder)

---

## ⚠️ Tips
- Always specify charset (`StandardCharsets.UTF_8`).
- For large payloads, use streaming encoders (`wrap(OutputStream)`).
- URL encoder replaces `+` with `-` and `/` with `_`; no padding if `withoutPadding()`.
