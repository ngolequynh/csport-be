package sportstracker.service.impl;

import sportstracker.common.exception.FileStorageException;
import sportstracker.common.property.FileStorageProperties;
import sportstracker.service.BaseImageStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Storing files in the file system and retrieving them
 *
 * @author Chuc Ba Hieu
 */
@Service
@Qualifier("activityImageService")
public class ActivityImageStorageServiceImpl extends BaseImageStorageService {

    public ActivityImageStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        super(fileStorageProperties);
        this.imageStorageLocation = Paths.get(fileStorageProperties.getActivityImageDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(imageStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

}
