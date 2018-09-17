package online.nwen.service.api;

import online.nwen.service.dto.resources.*;

public interface IResourceService {
    SaveResourceResultDTO save(SaveResourceDTO saveResourceDTO);

    GetResourceResultDTO get(GetResourceByIdDTO getResourceDTO);

    GetResourceResultDTO get(GetResourceByMd5DTO getResourceDTO);
}
