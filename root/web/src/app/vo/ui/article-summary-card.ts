import {ArticleSummary} from '../article-summary';

export class ArticleSummaryCard {
  summary: ArticleSummary;
  useFilter: boolean;

  constructor() {
    this.useFilter = true;
    this.summary = null;
  }
}
