package matt.book.page.content.bookpagecontentpublic.service;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.model.VideoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VideoScalingService {

    private FfmpegService ffmpegService;
    private IdService idService;
    private FileService fileService;

    @Autowired
    public VideoScalingService(FfmpegService ffmpegService, IdService idService, FileService fileService) {
        this.ffmpegService = ffmpegService;
        this.idService = idService;
        this.fileService = fileService;
    }

    public VideoData upscaleVideoAndMaintainAspectRatio(
        VideoData videoData
    ) throws IOException, InterruptedException {
        log.info("upscaleVideoAndMaintainAspectVideo");
        log.info(videoData.getVideoFileType());
        String inputVideoFileName = idService.generateRandomFileName(videoData.getVideoFileType());
        log.info("inputVideoFileName: " + inputVideoFileName);
        fileService.writeToFile(videoData.getVideoBytes(), inputVideoFileName);
        String outputVideoFileName = idService.generateRandomFileName(videoData.getVideoFileType());
        log.info("outputVideoFileName: " + outputVideoFileName);
        VideoData modifiedVideoData = ffmpegService.upscaleVideoTo1080pMaintainAspectRatio(inputVideoFileName, outputVideoFileName);
        log.info("completed upscaling video and maintaining the aspect ratio");
        fileService.deleteFile(inputVideoFileName);
        //fileService.deleteFile(outputVideoFileName);
        return modifiedVideoData;
    }

}
