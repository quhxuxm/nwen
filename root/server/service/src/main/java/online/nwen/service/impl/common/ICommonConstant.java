package online.nwen.service.impl.common;

public interface ICommonConstant {
    interface RoleName {
        String AUTHOR = "ROLE_AUTHOR";
        String ADMIN = "ROLE_ADMIN";
    }

    interface DefaultValue {
        long AUTHOR_SELECTED_TAG_INIT_WEIGHT = 10L;
        long ARTICLE_SELECTED_TAG_INIT_WEIGHT = 10L;
        long ANTHOLOGY_SELECTED_TAG_INIT_WEIGHT = 10L;
    }
    interface SessionAttrName{
        String AUTHENTICATED_AUTHOR_DETAIL ="AUTHENTICATED_AUTHOR_DETAIL";
    }
}
