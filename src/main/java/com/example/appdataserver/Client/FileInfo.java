package com.example.appdataserver.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInfo {
    public enum FileType {
        FILE("F"),
        DIRECTORY("D");
        private String name;
        public String getName() {
            return name;
        }
        FileType(String name) {
            this.name = name;
        }
    }





        private String filename;
        private FileType Type;

        private Long fileSize;

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public void setFileType(FileType fileType) {
            Type = fileType;
        }

        public void setFileSize(Long fileSize) {
            this.fileSize = fileSize;
        }

        public String getFilename() {
            return filename;
        }

        public FileType getFileType() {
            return Type;
        }

        public Long getFileSize() {
            return fileSize;
        }

        public  FileInfo(Path path) {
            try {
                this.filename = path.getFileName().toString();
                this.fileSize = Files.size(path);
                this.Type = Files.isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
                if (this.Type == FileType.DIRECTORY) {
                    this.fileSize = -1L;
                }
            } catch (IOException e) {
                throw new RuntimeException("Неполучилось создать FIleInfo", e);
            }

    }


}
