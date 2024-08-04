package org.skhuton.fitpete.record.diet.external;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.skhuton.fitpete.auth.global.error.exception.BadRequestException;
import org.skhuton.fitpete.auth.global.error.exception.ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile multipartFile, String folder) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket + "/" + folder + "/image", fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(bucket + "/" + folder + "/image", fileName).toString();
        } catch (IOException e) {
            throw new ImageUploadException("S3에 이미지를 업로드하는 데 실패했습니다.", e);
        }
    }

    // 파일명 생성 (중복 방지)
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // 파일 확장자 유효성 검사
    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.length() == 0) {
            throw new BadRequestException("파일 이름이 유효하지 않습니다.");
        }
        ArrayList<String> validExtensions = new ArrayList<>();
        validExtensions.add(".jpg");
        validExtensions.add(".jpeg");
        validExtensions.add(".png");
        validExtensions.add(".JPG");
        validExtensions.add(".JPEG");
        validExtensions.add(".PNG");
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (!validExtensions.contains(fileExtension)) {
            throw new BadRequestException("파일 확장자가 유효하지 않습니다.");
        }
        return fileExtension;
    }

    // 이미지 삭제
    public void deleteFile(String imageUrl) {
        String keyword = bucket + "/";
        int keywordIndex = imageUrl.indexOf(keyword);

        if (keywordIndex != -1) {
            String imageKey = imageUrl.substring(keywordIndex + keyword.length());
            amazonS3.deleteObject(bucket, imageKey);
        } else {
            throw new RuntimeException("S3 로직 오류. 서버에게 문의하세요.");
        }
    }
}
