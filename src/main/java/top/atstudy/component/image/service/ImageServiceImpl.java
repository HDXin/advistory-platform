package top.atstudy.component.image.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.atstudy.component.base.util.StringUtils;
import top.atstudy.component.exception.APIException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 19:00
 */
@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Value("${filestorage.root}")
    private String fileRoot;

    private static final String IMAGE_FOLDER = "/image";
    private static final String ORIGIN = "origin";

    /**
     * 原图尺寸
     */
    public static final String DEFAULT_IMAGE_SIZE = "0x0";
    private static final Pattern IMAGE_SIZE_PATTERN = Pattern.compile("\\dx\\d");

    @Autowired
    private DefaultImageCompressor defaultImageCompressor;

    @Override
    public List<String> uploadImages(boolean compress, MultipartFile... files) throws
            IOException {

        List<String> pathList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            String buildPath = buildImagePath();
            pathList.add(buildPath);

            String suffix = StringUtils.getFileSuffix(file.getOriginalFilename());
            createFIle(file, buildPath + ORIGIN + suffix);

            logger.info("Created file: {} -> {}", ORIGIN + suffix, buildPath);
            //压缩
            File srcFile = new File(fileRoot + IMAGE_FOLDER + buildPath + ORIGIN + suffix);
            File targetFile = new File(fileRoot + IMAGE_FOLDER + buildPath + DEFAULT_IMAGE_SIZE + suffix);
            if (compress) {
                defaultImageCompressor.compressImage(srcFile, targetFile);
            } else {
                FileInputStream fileInputStream = new FileInputStream(srcFile);
                FileOutputStream fileOutputStream = new FileOutputStream(targetFile);

                IOUtils.copy(fileInputStream, fileOutputStream);
                fileOutputStream.flush();
                IOUtils.closeQuietly(fileOutputStream);
                IOUtils.closeQuietly(fileInputStream);

            }

            //添加文件记录
            createImageRecord(buildPath, file);
        }

        return pathList;
    }

    @Override
    public Map<String, Object> read(Long fid) throws IOException, APIException {
        return null;
    }

    @Override
    public Map<String, Object> read(String path) throws IOException {
        return null;
    }


    private void createImageRecord(String buildPath, MultipartFile file) {
//        FileDTO fileDTO = new FileDTO();
//        fileDTO.setOperator(sessionUser, true);
//        fileDTO.setFileName(file.getOriginalFilename());
//        fileDTO.setFilePath(buildPath);
//        fileDTO.setFileSize(file.getSize());
//        fileDTO.setFileSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
//        fileDTO.setFileType(EnumFileType.IMAGE);
//        fileDTOMapper.insertSelective(fileDTO);
    }

    //写入文件
    private void createFIle(MultipartFile file, String buildPath) throws IOException {
        byte[] fileBytes = file.getBytes();
        Path path = Paths.get(fileRoot + IMAGE_FOLDER, buildPath);
        Files.write(path, fileBytes, StandardOpenOption.CREATE);
    }

    private String buildImagePath() throws IOException {
        return StringUtils.buildImagePath(fileRoot + IMAGE_FOLDER, 0L);
    }

}
