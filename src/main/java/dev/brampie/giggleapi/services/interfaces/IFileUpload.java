package dev.brampie.giggleapi.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileUpload {
    String upload(MultipartFile file) throws IOException;
}
