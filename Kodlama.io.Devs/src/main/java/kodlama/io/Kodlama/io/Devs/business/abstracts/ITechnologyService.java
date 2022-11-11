package kodlama.io.Kodlama.io.Devs.business.abstracts;

import java.util.List;

import kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.entities.concretes.Technology;

public interface ITechnologyService {
	List<GetAllTechnologiesResponse> getAllTechnologies();
	void addTechnology(CreateTechnologyRequest createTechnologyRequest) throws Exception;
	void updateTechnology(UpdateTechnologyRequest updateTechnologyRequest) throws Exception;
	void deleteTechnology(DeleteTechnologyRequest deleteTechnologyRequest);
}
