package fr.boscmalo.uqac.book2roadbackend.Controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import fr.boscmalo.uqac.book2roadbackend.Model.*;
import fr.boscmalo.uqac.book2roadbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class CircuitController {
	@Autowired
	private CircuitRepository circuitRepository;
	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private DepartementRepository departementRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired AvisRepository avisRepository;

	@Autowired UtilisateurRepository utilisateurRepository;
	
	@Autowired ReservationRepository reservationRepository;
	
	@Autowired FavorisRepository favorisRepository;

	/**
	 * Get all tracks
	 * @return
	 */
	@GetMapping("/circuits")
	public @ResponseBody Page<Circuit> getCircuit(
			@QuerydslPredicate(root = Circuit.class) Predicate predicate,
			@RequestParam(name="dateDebut", required = false) Date dateDebut ,
			@RequestParam(name="dateFin", required = false)  Date dateFin,
			@PageableDefault(size = 5, sort="Code") Pageable pageable) {

		System.out.println(predicate);
		return circuitRepository.findAll(predicate, pageable);
	}

	/**
	 * Get track by Id
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/circuits/{code}", method = RequestMethod.GET)
	public Circuit getById(@PathVariable Long code) {
		return circuitRepository.findCircuitsById(code);
	}

	/**
	 * Get track by name
	 * @param nom
	 * @return
	 */
	@RequestMapping(value="/circuits/search", method = RequestMethod.GET)
	public List<Circuit> getByName(@RequestParam(value="nom") String nom) {
		return circuitRepository.findCircuitsByName(nom.toUpperCase());
	}

	/**
	 * Get track by user
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/circuits/user", method = RequestMethod.GET)
	public List<Circuit> getByUser(@RequestParam(value="user") int user) {
		return circuitRepository.findCircuitsByUser(user);
	}

	@RequestMapping(value="/circuits/rating", method = RequestMethod.GET)
	public List<Circuit> getCircuitsByRating() {
		List<Long> codeCircuit = avisRepository.getBestCircuit();
		List<Circuit> circuits = new ArrayList<>();
		for(Long circuit : codeCircuit) {
			circuits.add(circuitRepository.findCircuitsById(circuit));
		}

		return circuits;
	}

	@RequestMapping(value="/circuits/region/{codeUtilisateur}", method = RequestMethod.GET)
	public List<Circuit> getCircuitsByRegion(@PathVariable Long codeUtilisateur) {
		Utilisateur u = utilisateurRepository.getById(codeUtilisateur);
		Long codeRegion = u.getVille().getDepartement().getRegion().getCode();
		return circuitRepository.findCircuitsByRegion(codeRegion);
	}

	/**
	 * Create or update a track
	 * @param c
	 * @throws IOException 
	 */
	@RequestMapping(value="/circuits", method = RequestMethod.POST)
	public long setCircuit(@RequestBody Circuit c) throws IOException {
		Ville v = c.getVille();

		v.setLongitude("NaN"); v.setLatitude("NaN");
		c.setNom(c.getNom().toLowerCase());

		Departement d = departementRepository.findDepartementById(Long.valueOf(String.valueOf(v.getCodePostal()).substring(0, 2)));
		v.setDepartement(d);
		villeRepository.save(v);

		c.setCodeRegion(d.getRegion().getCode());
		circuitRepository.save(c);
		
		List<Image> images = imageRepository.getImageByCircuit(c.getCode());

		for(Image i : images) {
			removeImage(i);
			imageRepository.delete(i);
		}
		
		return c.getCode();
	}

	@RequestMapping(value="/circuits", method = RequestMethod.DELETE)
	public void removeCircuit(@RequestBody Circuit c) throws IOException {
		List<Image> images = imageRepository.getImageByCircuit(c.getCode());
		List<Avis> avis = avisRepository.findAvisFromCircuit(c.getCode());
		List<Reservation> reservations = reservationRepository.findReservationByCircuit(c.getCode());
		List<Favoris> favoris = favorisRepository.findFavorisByCircuit(c.getCode());
		for(Image i : images) {
			removeImage(i);
			imageRepository.delete(i);
		}
		for(Avis a : avis) {
			avisRepository.delete(a);
		}
		
		for(Reservation r : reservations) {
			reservationRepository.delete(r);
		}
		
		for(Favoris f : favoris) {
			favorisRepository.delete(f);
		}
		
		circuitRepository.delete(c);
	}

	@RequestMapping(value="/circuits/nb", method = RequestMethod.GET)
	public int getNbCircuit(@RequestParam(value="user") int intUser) {
		return circuitRepository.findNbCircuit(intUser);
	}

	public void removeImage(Image i) throws IOException {
		Circuit c = circuitRepository.findCircuitsById(i.getCodeCircuit());
		// path : idUser/idCircuit/idImage
		String pathImage = c.getUtilisateur().getCode() + "\\\\" + c.getCode() + "\\\\";
		Files.deleteIfExists(Paths.get("C:\\\\image_projet_mobile\\\\" + pathImage + i.getCode() + ".png"));
	}
}
