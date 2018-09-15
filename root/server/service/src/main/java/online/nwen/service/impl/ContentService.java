package online.nwen.service.impl;

import online.nwen.domain.Resource;
import online.nwen.repository.IResourceRepository;
import online.nwen.service.api.IContentService;
import online.nwen.service.dto.content.ContentParseResultDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
class ContentService implements IContentService {
    private static final String IMG_TAG = "img";
    private static final String VIDEO_TAG = "video";
    private static final String AUDIO_TAG = "audio";
    private IResourceRepository resourceRepository;

    ContentService(IResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public ContentParseResultDTO parse(String content) {
        ContentParseResultDTO result = new ContentParseResultDTO();
        Document cleanDocument = Jsoup.parse(Jsoup.clean(content, this.createWhiteList()));
        this.parseMediaContent(cleanDocument, IMG_TAG, "image/*", result);
        this.parseMediaContent(cleanDocument, VIDEO_TAG, "video/*", result);
        this.parseMediaContent(cleanDocument, AUDIO_TAG, "audio/*", result);
        String contentHtml = cleanDocument.body().html();
        result.setContent(contentHtml);
        return result;
    }

    private Whitelist createWhiteList() {
        return Whitelist.none()
                .addTags("audio", "video", "img", "div", "p", "h1", "h2", "h3", "h4", "b", "i", "blockquote", "br")
                .addAttributes("img", "src")
                .addAttributes("video", "src")
                .addAttributes("audio", "src")
                .addProtocols("img", "src", "data")
                .addProtocols("audio", "src", "data")
                .addProtocols("video", "src", "data");
    }

    private void parseMediaContent(Document document, String tagName, String contentType,
                                   ContentParseResultDTO contentParseResultDTO) {
        Elements imgElements = document.getElementsByTag(tagName);
        imgElements.forEach(element -> {
            String base64Content = element.attr("src");
            if (StringUtils.isEmpty(base64Content)) {
                return;
            }
            String[] dataUrlParts = base64Content.split(",");
            if (dataUrlParts.length < 2) {
                return;
            }
            byte[] dataUrlBytes = Base64.decodeBase64(dataUrlParts[1]);
            String md5 = DigestUtils.md5Hex(dataUrlBytes);
            Resource resource = this.resourceRepository.findByMd5(md5);
            if (resource == null) {
                resource = new Resource();
                resource.setContentType(contentType);
                resource.setContent(dataUrlBytes);
                resource.setMd5(md5);
                this.resourceRepository.save(resource);
            }
            element.attr("src", "/api/resource/" + resource.getId());
            contentParseResultDTO.getResources().add(resource);
        });
    }
}
