import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppUiModule} from './app-ui.module';
import {AppComponent} from './app.component';
import {CardColumnContainerComponent} from './component/card-coumn-container/card-column-container.component';
import {CardJumbotronContainerComponent} from './component/card-jumbotron-container/card-jumbotron-container.component';
import {CardComponent} from './component/card/card.component';
import {HomeComponent} from './component/home/home.component';
import {LogoComponent} from './component/logo/logo.component';
import {NavigatorComponent} from './component/navigator/navigator.component';
import {SeparatorComponent} from './component/separator/separator.component';
import { JumbotronComponent } from './component/jumbotron/jumbotron.component';
import { ArticleDetailComponent } from './component/article-detail/article-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent,
    LogoComponent,
    CardComponent,
    CardColumnContainerComponent,
    HomeComponent,
    CardJumbotronContainerComponent,
    SeparatorComponent,
    JumbotronComponent,
    ArticleDetailComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AppUiModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
