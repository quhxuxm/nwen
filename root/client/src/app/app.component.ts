import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'nwen-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'NWen';

  constructor(private translate: TranslateService) {
    translate.addLangs(['zh', 'en']);
    translate.setDefaultLang('en');
    const languageOfBrowser = translate.getBrowserLang();
    translate.use(languageOfBrowser);
  }
}
