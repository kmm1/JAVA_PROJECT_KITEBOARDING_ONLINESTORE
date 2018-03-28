package com.company;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kate M on 19.03.2018.
 */
public class FileUploadUtility {
    private static final String ABS_PATH = "C:\\Users\\Tom\\IdeaProjects\\store-master\\web\\src\\main\\webapp\\resources\\images\\";
    private static String REAL_PATH;

    public static void uploadFile(HttpServletRequest req, MultipartFile multipartFile) {
        REAL_PATH = req.getSession().getServletContext().getRealPath("/resources/images/");
        File fileABS_PATH = new File(ABS_PATH);
        File fileREAL_PATH = new File(REAL_PATH);
        if (!fileABS_PATH.exists()) {
            fileABS_PATH.mkdir();
        }
        if (!fileREAL_PATH.exists()) {
            fileREAL_PATH.mkdir();
        }

        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(ABS_PATH + multipartFile.getOriginalFilename()));
            FileCopyUtils.copy(multipartFile.getBytes(), new File(REAL_PATH + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

