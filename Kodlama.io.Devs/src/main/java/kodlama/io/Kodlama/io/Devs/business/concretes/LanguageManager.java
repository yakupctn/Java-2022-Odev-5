package kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Kodlama.io.Devs.business.abstracts.ILanguageService;
import kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.DeleteLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguagesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ILanguageRepository;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ITechnologyRepository;
import kodlama.io.Kodlama.io.Devs.entities.concretes.Language;
import kodlama.io.Kodlama.io.Devs.entities.concretes.Technology;

@Service
public class LanguageManager implements ILanguageService {
	private ILanguageRepository iLanguageRepository;
	private ITechnologyRepository iTechnologyRepository;

	@Autowired
	public LanguageManager(ILanguageRepository iLanguageRepository, ITechnologyRepository iTechnologyRepository) {
		super();
		this.iLanguageRepository = iLanguageRepository;
		this.iTechnologyRepository = iTechnologyRepository;
	}

	@Override
	public List<GetAllLanguagesResponse> getAllLanguage() {
		List<Language> languages = iLanguageRepository.findAll();
		List<Technology> technologies = iTechnologyRepository.findAll();
		List<GetAllLanguagesResponse> languagesResponse = new ArrayList<GetAllLanguagesResponse>();

		for (Language language : languages) {
			GetAllLanguagesResponse responseItem = new GetAllLanguagesResponse();
			List<GetAllTechnologiesResponse> techArray = new ArrayList<GetAllTechnologiesResponse>();

			for (Technology technology : technologies) {
				GetAllTechnologiesResponse tech = new GetAllTechnologiesResponse();

				if (language.getId() == technology.getLanguage().getId()) {
					tech.setId(technology.getId());
					tech.setName(technology.getName());
					techArray.add(tech);
				}
			}

			responseItem.setId(language.getId());
			responseItem.setName(language.getName());
			responseItem.setTechnologies(techArray);

			languagesResponse.add(responseItem);
		}

		return languagesResponse;
	}

	@Override
	public GetAllLanguagesResponse getLanguageById(int id) {
		List<Language> languages = iLanguageRepository.findAll();
		GetAllLanguagesResponse responseItem = new GetAllLanguagesResponse();

		for (Language language : languages) {
			List<GetAllTechnologiesResponse> techArray = new ArrayList<GetAllTechnologiesResponse>();

			if (language.getId() == id) {
				for (Technology technology : language.getTechnologies()) {
					GetAllTechnologiesResponse tech = new GetAllTechnologiesResponse();

					tech.setId(technology.getId());
					tech.setName(technology.getName());
					techArray.add(tech);
				}

				responseItem.setId(language.getId());
				responseItem.setName(language.getName());
				responseItem.setTechnologies(techArray);
			}
		}
		return responseItem;
	}

	@Override
	public void addLanguage(CreateLanguageRequest createLanguageRequest) throws Exception {
		if (createLanguageRequest.getName().isEmpty()) {
			throw new Exception("Programlama dili boş geçilemez.");
		}

		for (Language language : iLanguageRepository.findAll()) {
			if (language.getName().equalsIgnoreCase(createLanguageRequest.getName())) {
				throw new Exception("Programlama dili adı tekrar edemez.");
			}
		}

		Language language = new Language();
		language.setName(createLanguageRequest.getName());

		iLanguageRepository.save(language);

	}

	@Override
	public void updateLanguage(UpdateLanguageRequest updateLanguageRequest) throws Exception {
		if (updateLanguageRequest.getNewName().isEmpty()) {
			throw new Exception("Programlama dili boş geçilemez.");
		}

		for (Language language : iLanguageRepository.findAll()) {
			if (language.getName().equalsIgnoreCase(updateLanguageRequest.getNewName())) {
				throw new Exception("Yeni isim eski isim ile aynı olamaz.");
			}
		}

		for (Language language : iLanguageRepository.findAll()) {
			if (language.getName().equalsIgnoreCase(updateLanguageRequest.getOldName())) {
				language.setName(updateLanguageRequest.getNewName());
				iLanguageRepository.save(language);
			}
		}
	}

	@Override
	public void deleteLanguage(DeleteLanguageRequest deleteLanguageRequest) {
		for (Language language : iLanguageRepository.findAll()) {
			if (language.getName().equalsIgnoreCase(deleteLanguageRequest.getName())) {
				for (Technology technology : language.getTechnologies()) {
					iTechnologyRepository.delete(technology);
				}
				iLanguageRepository.delete(language);
			}
		}

	}

}
