package top.atstudy.component.image.controller;

import com.google.zxing.WriterException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import top.atstudy.component.enums.EnumImageType;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.image.service.ImageService;
import top.atstudy.component.image.service.QrcodeService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("/qrcode")
public class QrcodeController {
    private static final Logger logger = LoggerFactory.getLogger(QrcodeController.class);

    @Autowired
    private QrcodeService qrcodeService;

    @GetMapping("/file")
    public String generalQcCodeImage(@RequestParam String content) throws APIException, IOException, WriterException {
        return qrcodeService.writeQrCodeFile(content);
    }

    @GetMapping("/stream")
    public void generalQcCodeStream(HttpServletResponse response, @RequestParam String content) throws APIException, IOException, WriterException {
        qrcodeService.writeQrCodeOutputStream(content, response.getOutputStream());
    }

}
