package online.nwen.service.api;

import online.nwen.service.dto.anthology.GetAnthologyDetailDTO;
import online.nwen.service.dto.anthology.GetAnthologyDetailResultDTO;

public interface IAnthologyService {
    GetAnthologyDetailResultDTO getAnthologyDetail(GetAnthologyDetailDTO getAnthologyDetailDTO);
}