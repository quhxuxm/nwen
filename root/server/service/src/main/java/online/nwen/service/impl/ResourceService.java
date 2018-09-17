package online.nwen.service.impl;

import online.nwen.domain.Author;
import online.nwen.domain.Resource;
import online.nwen.repository.IResourceRepository;
import online.nwen.service.api.IResourceService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.resources.*;
import online.nwen.service.security.SecurityContextHolder;
import online.nwen.service.security.annotation.PrepareSecurityContext;
import online.nwen.service.security.annotation.Security;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ResourceService implements IResourceService {
    public ResourceService(IResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    private static class Base64ContentConvertResult {
        private String contentType;
        private String md5;
        private byte[] bytes;
    }

    private IResourceRepository resourceRepository;

    @Security
    @PrepareSecurityContext
    @CachePut(cacheNames = "resources-by-id", key = "#{result.id}")
    @Override
    public SaveResourceResultDTO save(SaveResourceDTO saveResourceDTO) {
        String contentAsBase64 = saveResourceDTO.getContentAsBase64();
        Base64ContentConvertResult convertResult = this.convert(contentAsBase64);
        GetResourceByMd5DTO getResourceByMd5DTO = new GetResourceByMd5DTO();
        getResourceByMd5DTO.setMd5(convertResult.md5);
        try {
            GetResourceResultDTO getResourceResultDTO = this.get(getResourceByMd5DTO);
            SaveResourceResultDTO resourceResultDTO = new SaveResourceResultDTO();
            resourceResultDTO.setId(getResourceResultDTO.getId());
            resourceResultDTO.setMd5(getResourceResultDTO.getMd5());
            return resourceResultDTO;
        } catch (ServiceException e) {
            if (ExceptionCode.RESOURCE_ERROR_NOT_EXIST == e.getCode()) {
                Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
                Resource resource = new Resource();
                resource.setContentType(convertResult.contentType);
                resource.setContent(convertResult.bytes);
                resource.setMd5(convertResult.md5);
                resource.setSaveAuthorId(currentAuthor.getId());
                this.resourceRepository.save(resource);
                SaveResourceResultDTO resourceResultDTO = new SaveResourceResultDTO();
                resourceResultDTO.setId(resource.getId());
                resourceResultDTO.setMd5(resource.getMd5());
                return resourceResultDTO;
            }
            throw e;
        }
    }

    private Base64ContentConvertResult convert(String base64Content) {
        if (StringUtils.isEmpty(base64Content)) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_RESOURCE_CONTENT);
        }
        String[] dataUrlParts = base64Content.split(",");
        if (dataUrlParts.length < 2) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_RESOURCE_CONTENT_FORMAT_INCORRECT);
        }
        String protocolPart = dataUrlParts[0].toLowerCase();
        if (!protocolPart.startsWith("data:") || !protocolPart.endsWith(";base64")) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_RESOURCE_CONTENT_FORMAT_INCORRECT);
        }
        String contentType = protocolPart.substring("data:".length(), protocolPart.indexOf(";base64"));
        if (StringUtils.isEmpty(contentType)) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_RESOURCE_CONTENT_FORMAT_INCORRECT);
        }
        if (StringUtils.isEmpty(dataUrlParts[1])) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_RESOURCE_CONTENT_FORMAT_INCORRECT);
        }
        byte[] dataUrlBytes = Base64.decodeBase64(dataUrlParts[1]);
        String md5 = DigestUtils.md5Hex(dataUrlBytes);
        Base64ContentConvertResult result = new Base64ContentConvertResult();
        result.contentType = contentType;
        result.md5 = md5;
        result.bytes = dataUrlBytes;
        return result;
    }

    @Cacheable(cacheNames = "resources-by-id", key = "#{getResourceDTO.id}")
    @Override
    public GetResourceResultDTO get(GetResourceByIdDTO getResourceDTO) {
        Optional<Resource> resourceOptional = this.resourceRepository.findById(getResourceDTO.getId());
        if (!resourceOptional.isPresent()) {
            throw new ServiceException(ExceptionCode.RESOURCE_ERROR_NOT_EXIST);
        }
        Resource resource = resourceOptional.get();
        GetResourceResultDTO resourceResultDTO = new GetResourceResultDTO();
        resourceResultDTO.setId(resource.getId());
        resourceResultDTO.setContent(resource.getContent());
        resourceResultDTO.setContentType(resource.getContentType());
        resourceResultDTO.setMd5(resource.getMd5());
        return resourceResultDTO;
    }

    @Cacheable(cacheNames = "resources-by-md5", key = "#{getResourceDTO.md5}")
    @Override
    public GetResourceResultDTO get(GetResourceByMd5DTO getResourceDTO) {
        Resource resource = this.resourceRepository.findByMd5(getResourceDTO.getMd5());
        if (resource == null) {
            throw new ServiceException(ExceptionCode.RESOURCE_ERROR_NOT_EXIST);
        }
        GetResourceResultDTO resourceResultDTO = new GetResourceResultDTO();
        resourceResultDTO.setId(resourceResultDTO.getId());
        resourceResultDTO.setContent(resource.getContent());
        resourceResultDTO.setContentType(resource.getContentType());
        resourceResultDTO.setMd5(resource.getMd5());
        return resourceResultDTO;
    }
}
