package fr.boscmalo.uqac.book2roadbackend.Controller;


import fr.boscmalo.uqac.book2roadbackend.Model.Reservation;
import fr.boscmalo.uqac.book2roadbackend.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value="/reservation/{codeCircuit}", method = RequestMethod.GET)
    public List<Reservation> getAll(@PathVariable Long codeCircuit) {
        return reservationRepository.findReservationByCircuit(codeCircuit);
    }

    @RequestMapping(value="/reservation", method= RequestMethod.GET)
    public List<Reservation> getAllByUser(@PathParam(value="user") Long codeUser) {
        return reservationRepository.findReservationByUser(codeUser);
    }

    @RequestMapping(value="/reservation/check")
    public boolean checkReservation(@RequestBody Reservation reservation) {
        return reservationRepository.findReservationByIdAndDate(reservation.getCodeCircuit(),
                reservation.getDateDebut(), reservation.getDateFin()) == 0;
    }

    @RequestMapping(value="/reservation", method = RequestMethod.POST)
    public void setReservation(@RequestBody Reservation reservation) {
        if(checkReservation(reservation)) {
            reservationRepository.save(reservation);
        }
    }

    @RequestMapping(value="/reservation", method = RequestMethod.DELETE)
    public void removeReservation(@RequestBody Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
