import {Injectable} from '@angular/core';
import {AnthologyDetail} from '../vo/anthology-detail';

@Injectable({
  providedIn: 'root'
})
export class AnthologyDetailService {
  constructor() {
  }

  query(id: number): AnthologyDetail {
    const result = new AnthologyDetail();
    result.id = id;
    result.title = '测试文集标题';
    result.authorNickname = '测试作者昵称';
    result.authorIconImgId = 1;
    result.updateDate = new Date();
    result.authorId = 1;
    return result;
  }
}
