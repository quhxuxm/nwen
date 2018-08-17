import {ArticleSummary} from '../article-summary';

export class ArticleSummaryCard {
  summary: ArticleSummary;
  useCoverImageFilter: boolean;

  constructor() {
    this.useCoverImageFilter = true;
    this.summary = null;
  }
}
