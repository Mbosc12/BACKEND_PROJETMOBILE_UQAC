package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Utilisateur;
import fr.boscmalo.uqac.book2roadbackend.Model.Ville;
import fr.boscmalo.uqac.book2roadbackend.Repository.UtilisateurRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.VilleRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private VilleRepository villeRepository;

    /**
     * Get all users
     * @return
     */
    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    /**
     * Get user info by his pseudo
     * @param user
     * @return
     */
    @GetMapping("/utilisateur")
    public Utilisateur getUser(@RequestParam(value="user") String user) throws IOException {
        Utilisateur u = utilisateurRepository.getUser(user);

        byte[] bytes = Files.readAllBytes(Paths.get("C:\\\\image_projet_mobile\\\\" + u.getCode() + "\\\\userImage\\\\avatar.png"));
        String encodedFile = Base64.getEncoder().encodeToString(bytes);
        u.setImage(encodedFile);

        return utilisateurRepository.getUser(user);
    }

    /**
     * Check if user exist
     * @param user
     * @return
     */
    @GetMapping("/login/checkUser")
    public boolean userExist(@RequestParam(value="user") String user) {
        return (utilisateurRepository.userExist(user) > 0);
    }

    /**
     * Check if information are true
     * @param user
     * @param mdp
     * @return
     */
    @GetMapping("/login")
    public boolean userConnection(@RequestParam(value="user") String user, @RequestParam(value="mdp") String mdp) {
        return (utilisateurRepository.connection(user, mdp) > 0);
    }

    @RequestMapping(value="/utilisateur", method = RequestMethod.POST)
    public void userRegister(@RequestBody Utilisateur u) throws IOException {
        Ville v = villeRepository.findVilleById(12000);
        u.setVille(v);

        String image = u.getImage();

        u.setImage("temp");
        utilisateurRepository.save(u);

        String newImageLink = setImage((image != null) ? image : getDefaultStringImage(), u.getCode());
        u.setImage(newImageLink);
        utilisateurRepository.save(u);
    }

    public String setImage(String image, Long codeUtilisateur) throws IOException {
        String extension = "png";

        //create image
        byte[] imageByte = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));

        // path : idUser/idImage
        String pathImage = codeUtilisateur + "\\\\" + "userImage" + "\\\\";
        // path to save image;
        Files.createDirectories(Paths.get("C:\\\\image_projet_mobile\\\\" + pathImage));
        String pathToSave = "C:\\\\image_projet_mobile\\\\" + pathImage + "avatar." + extension;
        File img = new File(pathToSave);
        ImageIO.write(bufferedImage, extension, img);

        return pathImage;
    }

    public String getDefaultStringImage() throws IOException{
        String s = "";

        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/images/defaultuserimage.png").toAbsolutePath());
        s = Base64.getEncoder().encodeToString(bytes);
        return s;
    }

}
