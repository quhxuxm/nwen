package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAnthologyService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.configuration.ServiceConfiguration;
import online.nwen.service.dto.anthology.*;
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
    private ServiceConfiguration serviceConfiguration;

    AnthologyService(IAnthologyRepository anthologyRepository,
                     IAuthorRepository authorRepository,
                     ServiceConfiguration serviceConfiguration) {
        this.anthologyRepository = anthologyRepository;
        this.authorRepository = authorRepository;
        this.serviceConfiguration = serviceConfiguration;
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
        if (currentAuthor != null) {
            if (anthology.getViewers().containsKey(currentAuthor.getId())) {
                Date lastViewDate = anthology.getViewers().get(currentAuthor.getId());
                Date currentDate = new Date();
                long difference = currentDate.getTime() - lastViewDate.getTime();
                if (difference > this.serviceConfiguration.getViewDateInterval()) {
                    anthology.setViewersNumber(anthology.getViewersNumber() + 1);
                    anthology.getViewers().put(currentAuthor.getId(), new Date());
                }
            } else {
                anthology.setViewersNumber(anthology.getViewersNumber() + 1);
                anthology.getViewers().put(currentAuthor.getId(), new Date());
            }
            this.anthologyRepository.save(anthology);
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
        resultDTO.setPraisesNumber(anthology.getPraiseNumber());
        resultDTO.setArticleNumber(anthology.getArticleNumber());
        resultDTO.setCommentsNumber(anthology.getCommentsNumber());
        resultDTO.setBookmarksNumber(anthology.getBookmarksNumber());
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

    @Security
    @PrepareSecurityContext
    @Override
    public PraiseAnthologyResultDTO praiseAnthology(PraiseAnthologyDTO praiseAnthologyDTO) {
        if (praiseAnthologyDTO.getAnthologyId() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ANTHOLOGY_ID);
        }
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        Optional<Anthology> anthologyOptional = this.anthologyRepository.findById(praiseAnthologyDTO.getAnthologyId());
        if (!anthologyOptional.isPresent()) {
            throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_NOT_EXIST);
        }
        Anthology anthology = anthologyOptional.get();
        if (!anthology.getPraises().containsKey(currentAuthor.getId())) {
            anthology.getPraises().put(currentAuthor.getId(), new Date());
            anthology.setPraisesNumber(anthology.getPraisesNumber() + 1);
        } else {
            anthology.getPraises().remove(currentAuthor.getId());
            anthology.setPraisesNumber(anthology.getPraisesNumber() - 1);
        }
        this.anthologyRepository.save(anthology);
        PraiseAnthologyResultDTO resultDTO = new PraiseAnthologyResultDTO();
        resultDTO.setAnthologyId(anthology.getId());
        resultDTO.setPraiseNumber(anthology.getPraisesNumber());
        return resultDTO;
    }

    @Security
    @PrepareSecurityContext
    @Override
    public BookmarkAnthologyResultDTO bookmarkAnthology(BookmarkAnthologyDTO bookmarkAnthologyDTO) {
        if (bookmarkAnthologyDTO.getAnthologyId() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ANTHOLOGY_ID);
        }
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        Optional<Anthology> anthologyOptional =
                this.anthologyRepository.findById(bookmarkAnthologyDTO.getAnthologyId());
        if (!anthologyOptional.isPresent()) {
            throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_NOT_EXIST);
        }
        Anthology anthology = anthologyOptional.get();
        if (!anthology.getBookmarks().containsKey(currentAuthor.getId())) {
            anthology.getBookmarks().put(currentAuthor.getId(), new Date());
            anthology.setBookmarksNumber(anthology.getBookmarksNumber() + 1);
        } else {
            anthology.getBookmarks().remove(currentAuthor.getId());
            anthology.setBookmarksNumber(anthology.getBookmarksNumber() - 1);
        }
        this.anthologyRepository.save(anthology);
        BookmarkAnthologyResultDTO resultDTO = new BookmarkAnthologyResultDTO();
        resultDTO.setAnthologyId(anthology.getId());
        resultDTO.setBookmarkNumber(anthology.getBookmarksNumber());
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
