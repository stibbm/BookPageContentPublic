package matt.book.page.content.bookpagecontentpublic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {
    private String productName;
    private List<String> imageList;
    private Long amount;
}
