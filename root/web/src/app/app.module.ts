import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '../../node_modules/@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppUiModule} from './app-ui.module';
import {AppComponent} from './app.component';
import {ArticleSummaryCardContainerComponent} from './component/article-summary-card-container/article-summary-card-container.component';
import {ArticleSummaryCardComponent} from './component/article-summary-card/article-summary-card.component';
import {HomeComponent} from './component/home/home.component';
import {LogoComponent} from './component/logo/logo.component';
import {NavigatorComponent} from './component/navigator/navigator.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent,
    LogoComponent,
    ArticleSummaryCardComponent,
    ArticleSummaryCardContainerComponent,
    HomeComponent
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
