package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ReservationRepository;
import repositories.VolRepository;

@Service
public class VolService {

@Autowired
private VolRepository volRepo;

@Autowired
private ReservationRepository resaRepo;
	
}
