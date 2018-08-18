export class Card {
  title: string;
  titleRouterLink: string;
  subTitle: string;
  subTitleRouterLink: string;
  content: string;
  contentRouterLink: string;
  coverImageUrl: string;
  coverImageRouterLink: string;
  showTitle: boolean;
  showSubTitle: boolean;
  showContent: boolean;
  showCoverImage: boolean;
  useCoverImageFilter: boolean;

  constructor() {
    this.title = null;
    this.titleRouterLink = null;
    this.subTitle = null;
    this.subTitleRouterLink = null;
    this.content = null;
    this.contentRouterLink = null;
    this.coverImageUrl = null;
    this.coverImageRouterLink = null;
    this.showCoverImage = true;
    this.showTitle = true;
    this.showSubTitle = true;
    this.showContent = true;
    this.showCoverImage = true;
    this.useCoverImageFilter = true;
  }
}
