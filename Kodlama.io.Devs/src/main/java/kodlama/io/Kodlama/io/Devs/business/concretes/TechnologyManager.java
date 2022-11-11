package kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Kodlama.io.Devs.business.abstracts.ITechnologyService;
import kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ILanguageRepository;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ITechnologyRepository;
import kodlama.io.Kodlama.io.Devs.entities.concretes.Language;
import kodlama.io.Kodlama.io.Devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements ITechnologyService {
	private ILanguageRepository iLanguageRepository;
	private ITechnologyRepository iTechnologyRepository;
	
	@Autowired
	public TechnologyManager(ILanguageRepository iLanguageRepository, ITechnologyRepository iTechnologyRepository) {
		super();
		this.iLanguageRepository = iLanguageRepository;
		this.iTechnologyRepository = iTechnologyRepository;
	}

	@Override
	public List<GetAllTechnologiesResponse> getAllTechnologies() {
		List<Technology> technologies = iTechnologyRepository.findAll();
		List<GetAllTechnologiesResponse> technologiesResponses = new ArrayList<GetAllTechnologiesResponse>();
		
		for(Technology technology : technologies) {
			GetAllTechnologiesResponse technoItem = new GetAllTechnologiesResponse();
			technoItem.setId(technology.getId());
			technoItem.setName(technology.getName());
			technologiesResponses.add(technoItem);
		}
		
		return technologiesResponses;
	}

	@Override
	public void addTechnology(CreateTechnologyRequest createTechnologyRequest) throws Exception {
		if(createTechnologyRequest.getTechnologyName().isEmpty()) {
			throw new Exception("Programlama dili alt teknoloji boş geçilemez.");
		}
		
		for(Technology technology : iTechnologyRepository.findAll()) {
			if(technology.getName().equalsIgnoreCase(createTechnologyRequest.getTechnologyName())) {
				throw new Exception("Programlama dili alt teknoloji adı tekrar edemez.");
			}
		}
		
		int languageId = 10;
		for(Language language: iLanguageRepository.findAll()) {
			if(language.getName().equalsIgnoreCase(createTechnologyRequest.getLanguageName())){
				languageId = language.getId();
			}
		}
		
		Language language = new Language();
		language.setId(languageId);
		Technology technology = new Technology();
		technology.setName(createTechnologyRequest.getTechnologyName());
		technology.setLanguage(language);;
		
		iTechnologyRepository.save(technology);
	}

	@Override
	public void updateTechnology(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
		if(updateTechnologyRequest.getNewName().isEmpty()) {
			throw new Exception("Programlama dili alt teknoloji boş geçilemez.");
		}
		
		for(Technology technology : iTechnologyRepository.findAll()) {
			if(technology.getName().equalsIgnoreCase(updateTechnologyRequest.getNewName())) {
				throw new Exception("Yeni isim eski isim ile aynı olamaz.");
			}
		}
		
		for(Technology technology: iTechnologyRepository.findAll()) {
			if(technology.getName().equalsIgnoreCase(updateTechnologyRequest.getOldName())){
				technology.setName(updateTechnologyRequest.getNewName());
				iTechnologyRepository.save(technology);
			}
		}
		
	}

	@Override
	public void deleteTechnology(DeleteTechnologyRequest deleteTechnologyRequest) {
		for(Technology technology: iTechnologyRepository.findAll()) {
			if(technology.getName().equalsIgnoreCase(deleteTechnologyRequest.getName())){
				iTechnologyRepository.delete(technology);
			}
		}
	}
	
	
	
	
}
