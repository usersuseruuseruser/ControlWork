package com.solncev.net.servlet;

import com.cloudinary.Cloudinary;
import com.solncev.net.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;


@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class FileUploadingServlet extends HttpServlet {

    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    public static final String FILE_PATH_PREFIX = "/tmp";
    public static final int DIRECTORIES_COUNT = 100;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");

        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File(FILE_PATH_PREFIX + File.separator
                + filename.hashCode() % DIRECTORIES_COUNT + File.separator + filename);
        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();

//        Path p =File.createTempFile();
//        Files.write(p, buffer, StandardOpenOption.DELETE_ON_CLOSE);
//        file.deleteOnExit();

        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];

        content.read(buffer);

        outputStream.write(buffer);
        outputStream.close();

        cloudinary.uploader().upload(file, new HashMap<>());
    }
}
