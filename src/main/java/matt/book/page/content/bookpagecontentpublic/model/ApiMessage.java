package matt.book.page.content.bookpagecontentpublic.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessage implements Serializable {

    public ApiMessage(Object body, String message, Boolean error) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.message = message;
        this.error = error;
    }

    public ApiMessage(Object body, String message) {
        this(body, message, false);
    }

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Builder.Default
    private Boolean error = false;
    private Object body;
    private String message;
}
