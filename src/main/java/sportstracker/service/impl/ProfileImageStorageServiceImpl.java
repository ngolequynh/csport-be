package sportstracker.service.impl;

import sportstracker.common.exception.FileStorageException;
import sportstracker.common.property.FileStorageProperties;
import sportstracker.service.BaseImageStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Qualifier("profileImageService")
public class ProfileImageStorageServiceImpl extends BaseImageStorageService {

    public ProfileImageStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        super(fileStorageProperties);
        this.imageStorageLocation = Paths.get(fileStorageProperties.getProfileImageDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(imageStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

}
