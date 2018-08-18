import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppUiModule} from './app-ui.module';
import {AppComponent} from './app.component';
import {ArticleSummaryCardColumnContainerComponent} from './component/article-summary-card-coumn-container/article-summary-card-column-container.component';
import {ArticleSummaryCardJumbotronContainerComponent} from './component/article-summary-card-jumbotron-container/article-summary-card-jumbotron-container.component';
import {ArticleSummaryCardComponent} from './component/article-summary-card/article-summary-card.component';
import {HomeComponent} from './component/home/home.component';
import {LogoComponent} from './component/logo/logo.component';
import {NavigatorComponent} from './component/navigator/navigator.component';
import {SeparatorComponent} from './component/separator/separator.component';
import { JumbotronComponent } from './component/jumbotron/jumbotron.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent,
    LogoComponent,
    ArticleSummaryCardComponent,
    ArticleSummaryCardColumnContainerComponent,
    HomeComponent,
    ArticleSummaryCardJumbotronContainerComponent,
    SeparatorComponent,
    JumbotronComponent
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
