package fr.boscmalo.uqac.book2roadbackend.Controller;

import com.querydsl.core.util.FileUtils;
import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Image;
import fr.boscmalo.uqac.book2roadbackend.Repository.CircuitRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CircuitRepository circuitRepository;

    @GetMapping("/images")
    public List<Image> getAll(@RequestParam(value="code") Long code) throws IOException {
        List<Image> listImage = imageRepository.getImageByCircuit(code);
        for(Image i : listImage) {
            Circuit c = circuitRepository.findCircuitsById(i.getCodeCircuit());
            File image = new File("C:\\\\image_projet_mobile\\\\" + i.getLien() + i.getCode() + ".png");
            FileInputStream fin = new FileInputStream(image);
            byte[] bytes = Files.readAllBytes(Paths.get("C:\\\\image_projet_mobile\\\\" + i.getLien() + i.getCode() + ".png"));
            String encodedFile = Base64.getEncoder().encodeToString(bytes);
            i.setLien(encodedFile);
        }

        return listImage;
    }

    @PostMapping("/images")
    public void setImage(@RequestBody List<Image> images) throws IOException {
        for(Image i : images) {
            //save to get id
            String image64 = i.getLien();
            String extension = "png";

            i.setLien("temp");
            imageRepository.save(i);

            //create image
            byte[] imageByte = Base64.getDecoder().decode(image64.getBytes(StandardCharsets.UTF_8));
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));

            //get circuit;
            Circuit c = circuitRepository.findCircuitsById(i.getCodeCircuit());
            // path : idUser/idCircuit/idImage
            String pathImage = c.getUtilisateur().getCode() + "\\\\" + c.getCode() + "\\\\";
            // path to save image;
            Files.createDirectories(Paths.get("C:\\\\image_projet_mobile\\\\" + pathImage));
            File image = new File("C:\\\\image_projet_mobile\\\\" + pathImage + i.getCode() + "." + extension);
            ImageIO.write(bufferedImage, extension, image);

            // change string to image link
            i.setLien(pathImage);

            //save
            imageRepository.save(i);
        }
    }

}
