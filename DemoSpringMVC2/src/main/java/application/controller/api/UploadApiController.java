package application.controller.api;


import application.constant.Constant;
import application.model.FileUploadResult;
import application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadApiController {
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/upload-image")
    public FileUploadResult uploadImage(@RequestParam("file") MultipartFile file){
        String message ="";
        FileUploadResult fileUploadResult = new FileUploadResult();
        try {
            String newFilename = fileStorageService.store(file);
            message ="You successfully uploaded "+ file.getOriginalFilename() + " !";
            fileUploadResult.setMessages(message);
            fileUploadResult.setSuccess(true);
            fileUploadResult.setLink(Constant.PREFIX_LINK_UPLOAD + newFilename);

        }catch (Exception e){
            fileUploadResult.setSuccess(false);
            fileUploadResult.setMessages(e.getMessage());
        }
        return  fileUploadResult;
    }
}
