import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  constructor() {
  }

  generateImageUrl(id: string): string {
    return `assets/image/${id}`;
  }

  generateAuthorDetailRouterUrl(id: string): string {
    return `/author/detail/${id}`;
  }

  generateArticleDetailRouterUrl(id: string): string {
    return `/article/detail/${id}`;
  }

  generateAnthologyDetailRouterUrl(id: string): string {
    return `/anthology/detail/${id}`;
  }
}
