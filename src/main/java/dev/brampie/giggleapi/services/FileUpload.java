package dev.brampie.giggleapi.services;

import com.cloudinary.Cloudinary;
import dev.brampie.giggleapi.services.interfaces.IFileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUpload implements IFileUpload {
    private final Cloudinary cloudinary;
    @Override
    public String upload(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), Map.of("public_id", UUID.randomUUID().toString())).get("url").toString();
    }
}
