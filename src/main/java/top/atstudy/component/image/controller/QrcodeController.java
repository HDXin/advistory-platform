package top.atstudy.component.image.controller;

import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.image.service.QrcodeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 18:52
 */

@RestController
@RequestMapping("/api/qrcode")
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
