package online.nwen.service.api;

import online.nwen.service.dto.anthology.GetAnthologyDetailDTO;
import online.nwen.service.dto.anthology.GetAnthologyDetailResultDTO;
import online.nwen.service.dto.anthology.SaveAnthologyDTO;
import online.nwen.service.dto.anthology.SaveAnthologyResultDTO;

public interface IAnthologyService {
    GetAnthologyDetailResultDTO getAnthologyDetail(GetAnthologyDetailDTO getAnthologyDetailDTO);

    SaveAnthologyResultDTO save(SaveAnthologyDTO saveAnthologyDTO);
}