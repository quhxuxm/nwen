package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAnthologyService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.anthology.GetAnthologyDetailDTO;
import online.nwen.service.dto.anthology.GetAnthologyDetailResultDTO;
import online.nwen.service.dto.anthology.SaveAnthologyDTO;
import online.nwen.service.dto.anthology.SaveAnthologyResultDTO;
import online.nwen.service.security.SecurityContextHolder;
import online.nwen.service.security.annotation.PrepareSecurityContext;
import online.nwen.service.security.annotation.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
class AnthologyService implements IAnthologyService {
    private static final Logger logger = LoggerFactory
            .getLogger(AnthologyService.class);
    private IAnthologyRepository anthologyRepository;
    private IAuthorRepository authorRepository;

    AnthologyService(IAnthologyRepository anthologyRepository,
                     IAuthorRepository authorRepository) {
        this.anthologyRepository = anthologyRepository;
        this.authorRepository = authorRepository;
    }

    @PrepareSecurityContext
    @Cacheable(cacheNames = "anthology-details", key = "#getAnthologyDetailDTO.anthologyId")
    @Override
    public GetAnthologyDetailResultDTO getAnthologyDetail(GetAnthologyDetailDTO getAnthologyDetailDTO) {
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        if (getAnthologyDetailDTO.getAnthologyId() == null) {
            logger.error("The anthology id in request is empty.");
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ANTHOLOGY_ID);
        }
        Optional<Anthology> anthologyOptional =
                this.anthologyRepository.findById(getAnthologyDetailDTO.getAnthologyId());
        if (!anthologyOptional.isPresent()) {
            logger.error("The anthology not exist.");
            throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_NOT_EXIST);
        }
        Anthology anthology = anthologyOptional.get();
        Optional<Author> anthologyAuthorOptional = this.authorRepository.findById(anthology.getAuthorId());
        if (!anthologyAuthorOptional.isPresent()) {
            logger.error("The anthology author not exist.");
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        Author anthologyAuthor = anthologyAuthorOptional.get();
        GetAnthologyDetailResultDTO resultDTO = new GetAnthologyDetailResultDTO();
        resultDTO.setAnthologyId(anthology.getId());
        resultDTO.setTitle(anthology.getTitle());
        resultDTO.setAuthorId(anthology.getAuthorId());
        resultDTO.setAuthorNickName(anthologyAuthor.getNickname());
        resultDTO.setTitle(anthology.getTitle());
        resultDTO.setSummary(anthology.getSummary());
        resultDTO.setCreateDate(anthology.getCreateDate());
        resultDTO.getTags().addAll(anthology.getTags());
        resultDTO.setCoverImageId(anthology.getCoverImageId());
        resultDTO.setAuthorIconImageId(anthologyAuthor.getIconImageId());
        resultDTO.setPraiseNumber(anthology.getPraiseNumber());
        resultDTO.setArticleNumber(anthology.getArticleNumber());
        resultDTO.setCommentNumber(anthology.getCommentNumber());
        resultDTO.setBookmarkNumber(anthology.getBookmarkNumber());
        resultDTO.setUpdateDate(anthology.getUpdateDate());
        return resultDTO;
    }

    @Security
    @PrepareSecurityContext
    @Override
    public SaveAnthologyResultDTO save(SaveAnthologyDTO saveAnthologyDTO) {
        if (saveAnthologyDTO.getTitle() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ANTHOLOGY_TITLE);
        }
        SaveAnthologyResultDTO resultDTO = null;
        if (saveAnthologyDTO.getAnthologyId() == null) {
            resultDTO = this.create(saveAnthologyDTO);
        } else {
            resultDTO = this.update(saveAnthologyDTO);
        }
        return resultDTO;
    }

    private SaveAnthologyResultDTO create(SaveAnthologyDTO saveAnthologyDTO) {
        Author author = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        Anthology anthology = new Anthology();
        anthology.setCreateDate(new Date());
        anthology.setTitle(saveAnthologyDTO.getTitle());
        anthology.setTags(saveAnthologyDTO.getTags());
        anthology.setSummary(saveAnthologyDTO.getSummary());
        anthology.setAuthorId(author.getId());
        anthology.setShared(saveAnthologyDTO.getShared());
        anthology.setPublished(saveAnthologyDTO.getPublished());
        anthology.setCoverImageId(saveAnthologyDTO.getCoverImageId());
        this.anthologyRepository.save(anthology);
        SaveAnthologyResultDTO resultDTO = new SaveAnthologyResultDTO();
        resultDTO.setAnthologyId(anthology.getId());
        return resultDTO;
    }

    private SaveAnthologyResultDTO update(SaveAnthologyDTO saveAnthologyDTO) {
        Author author = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        Optional<Anthology> anthologyOptional = this.anthologyRepository.findById(saveAnthologyDTO.getAnthologyId());
        if (!anthologyOptional.isPresent()) {
            throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_NOT_EXIST);
        }
        Anthology anthology = anthologyOptional.get();
        if (!anthology.getAuthorId().equals(author.getId())) {
            throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_AUTHOR_NOT_OWNER);
        }
        anthology.setUpdateDate(new Date());
        anthology.setTitle(saveAnthologyDTO.getTitle());
        anthology.setTags(saveAnthologyDTO.getTags());
        anthology.setSummary(saveAnthologyDTO.getSummary());
        anthology.setAuthorId(author.getId());
        anthology.setShared(saveAnthologyDTO.getShared());
        anthology.setPublished(saveAnthologyDTO.getPublished());
        anthology.setCoverImageId(saveAnthologyDTO.getCoverImageId());
        this.anthologyRepository.save(anthology);
        SaveAnthologyResultDTO resultDTO = new SaveAnthologyResultDTO();
        resultDTO.setAnthologyId(anthology.getId());
        return resultDTO;
    }
}
