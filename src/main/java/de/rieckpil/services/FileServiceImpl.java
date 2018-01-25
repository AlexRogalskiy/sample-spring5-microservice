package de.rieckpil.services;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import de.rieckpil.domain.File;
import de.rieckpil.repositories.FileRepository;

@Service
public class FileServiceImpl implements FileService {

  private final FileRepository fileRepository;

  public FileServiceImpl(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  @Override
  public void saveFile(String name, MultipartFile file) throws IOException {

    if (file == null) {
      throw new RuntimeException("No file provided!");
    }

    Byte[] byteObjects = new Byte[file.getBytes().length];

    int i = 0;

    for (byte b : file.getBytes()) {
      byteObjects[i++] = b;
    }

    File fileToSave = File.builder().fileName(name).originalFileName(file.getOriginalFilename())
        .contentType(file.getContentType()).size(file.getSize()).file(byteObjects).build();

    fileRepository.save(fileToSave);

  }

}
