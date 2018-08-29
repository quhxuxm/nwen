import {Injectable} from '@angular/core';
import {AuthorDetail} from '../vo/author-detail';

export class AuthorDetailQueryCondition {
  resultNumber: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthorDetailService {
  constructor() {
  }

  query(queryCondition: AuthorDetailQueryCondition): AuthorDetail[] {
    const result: AuthorDetail[] = [];
    for (let i = 0; i < queryCondition.resultNumber; i++) {
      const authorDetail = new AuthorDetail();
      authorDetail.id = 1;
      authorDetail.nickName = `测试作者${authorDetail.id}`;
      authorDetail.description = `测试描述，测试描述测试描述，测试描述，测试描述，测试描述，测试描述，测试描述，测试描述`;
      authorDetail.iconImageId = 1;
      result.push(authorDetail);
    }
    return result;
  }
}
