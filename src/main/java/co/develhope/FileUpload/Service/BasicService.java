package co.develhope.FileUpload.Service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class BasicService {

    @Value("${fileRepositoryFolder}")
    private String fileRepositoryFolder;

    public String upload(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString();
        String compleateName = newFileName + "." + extension;
        File finalDestination = new File( fileRepositoryFolder + "\\" + compleateName);
        file.transferTo(finalDestination);
        return compleateName;
    }

    public byte[] download(String fileName) throws IOException {
        File fileRepository = new File(fileRepositoryFolder + "\\" + fileName);
        if(!fileRepository.exists()) throw new IOException("file doesn't exist");
        return IOUtils.toByteArray(new FileInputStream(fileRepository));
    }
}
