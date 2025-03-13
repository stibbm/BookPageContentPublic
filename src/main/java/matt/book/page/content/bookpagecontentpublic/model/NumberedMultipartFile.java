package matt.book.page.content.bookpagecontentpublic.model;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberedMultipartFile implements Serializable {
    private MultipartFile multipartFile;
    private Long imageNumber;
    private String fileType;
}
