package matt.book.page.content.bookpagecontentpublic.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.model.NumberedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageListHelperService {

    private StringUtilService stringUtilService;

    @Autowired
    public ImageListHelperService(StringUtilService stringUtilService) {
        this.stringUtilService = stringUtilService;
    }

    public List<NumberedMultipartFile> numberImages(List<MultipartFile> imageList) {
        List<NumberedMultipartFile> numberedImageList = new ArrayList<>();
        for (MultipartFile multipartFile: imageList) {
            try {
                Long imageNumber = stringUtilService
                    .extractLongFromDirtyString(multipartFile.getOriginalFilename());
                if (imageNumber != null) {
                    NumberedMultipartFile numberedMultipartFile =
                        NumberedMultipartFile.builder()
                            .multipartFile(multipartFile)
                            .imageNumber(imageNumber)
                            .build();
                    String ogFileName = numberedMultipartFile.getMultipartFile().getOriginalFilename();
                    String fileType = ogFileName.substring(ogFileName.lastIndexOf(".")+1);
                    numberedMultipartFile.setFileType(fileType);
                    numberedImageList.add(numberedMultipartFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(numberedImageList, Comparator.comparing(NumberedMultipartFile::getImageNumber));
        return numberedImageList;
    }
}
