package top.atstudy.component.image.service;

import org.springframework.web.multipart.MultipartFile;
import top.atstudy.component.exception.APIException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Spector on 2017/6/26.
 */
public interface ImageService {

    List<String> uploadImages(boolean compress, MultipartFile... files) throws IOException;

    Map<String, Object> read(Long fid) throws IOException, APIException;

    Map<String, Object> read(String path) throws IOException;

}
