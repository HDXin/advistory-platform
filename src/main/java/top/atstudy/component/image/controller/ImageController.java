package top.atstudy.component.image.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.image.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 18:52
 */

@RestController
@RequestMapping("/image")
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @PostMapping("/single")
    public String uploadSingleImage(@RequestBody HttpServletRequest request) throws IOException, APIException {
        MultipartFile[] files = parseMultiFiles(request);
        if (files == null || files.length == 0) {
            return null;
        }

        List<String> paths = imageService.uploadImages(checkImageCompress(request), files[0]);
        return paths.size() != 0 ? paths.get(0) : null;
    }

    @PostMapping("/multi")
    public List<String> uploadMultiImages(@RequestBody HttpServletRequest request) throws IOException, APIException {
        MultipartFile[] files = parseMultiFiles(request);
        String fileNames = Arrays.stream(files).map(MultipartFile::getOriginalFilename)
                .filter(StringUtils::isNotBlank).collect(Collectors.joining(" , "));

        if (StringUtils.isBlank(fileNames)) {
            return null;
        }

        return imageService.uploadImages(getSessionUserFromGateway(), checkImageCompress(), files);
    }


    private MultipartFile[] parseMultiFiles(HttpServletRequest httpServletRequest) {
        StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) httpServletRequest;
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();

        MultipartFile[] files = new MultipartFile[multiFileMap.size()];
        Iterator<Map.Entry<String, List<MultipartFile>>> iterator = multiFileMap.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, List<MultipartFile>> next = iterator.next();
            files[i++] = next.getValue().get(0);
        }
        return files;
    }

    private boolean checkImageCompress(HttpServletRequest request){
        String notCompress = request.getParameter("notCompress");
        logger.info(">>> 图片上传是否压缩：{}", notCompress);
        return Boolean.parseBoolean(notCompress);
    }

}
