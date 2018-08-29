package online.nwen.service.api;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.*;

public interface IAnthologyService {
    Long saveAnthology(CreateAnthologyDTO createAnthologyDTO)
            throws ServiceException;

    void assignTagsToAnthology(AnthologyAssignTagsDTO anthologyAssignTagsDTO)
            throws ServiceException;

    void bookmarkAnthology(AnthologyBookmarkDTO anthologyBookmarkDTO)
            throws ServiceException;

    void praiseAnthology(AnthologyPraiseDTO anthologyPraiseDTO)
            throws ServiceException;

    AnthologyDetailDTO viewAnthology(AnthologyViewDTO anthologyViewDTO)
            throws ServiceException;

    void increaseAuthorTagWeightAccordingToAnthologyTags(Author author,
                                                         Anthology anthology) throws ServiceException;
}
