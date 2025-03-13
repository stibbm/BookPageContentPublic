package matt.book.page.content.bookpagecontentpublic.service;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.model.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImageTypeConversionService {
    private IdService idService;
    private FileService fileService;
    private FfmpegService ffmpegService;

    @Autowired
    public ImageTypeConversionService(
        IdService idService,
        FileService fileService,
        FfmpegService ffmpegService
    ) {
        this.idService = idService;
        this.fileService = fileService;
        this.ffmpegService = ffmpegService;
    }

    public ImageData fixImageFormatIfNeeded(ImageData oldImageData)
        throws IOException, InterruptedException {
        log.info("image file type: " + oldImageData.getImageFileType());
        if (!oldImageData.getImageFileType().equalsIgnoreCase("png")) {
            log.info("updating the file type");
            String oldImageFileName = idService.generateRandomFileName(
                oldImageData.getImageFileType());
            String newImageFileName = idService.generateRandomFileName("png");
            fileService.writeToFile(oldImageData.getImageBytes(), oldImageFileName);
            ImageData newImageData = ffmpegService.changeImageFormat(oldImageFileName,
                newImageFileName);
            fileService.deleteFile(oldImageFileName);
            fileService.deleteFile(newImageFileName);
            log.info("new file type = " + newImageData.getImageFileType());
            return newImageData;
        } else {
            return oldImageData;
        }
    }
}
