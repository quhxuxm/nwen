import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { TopNavigationComponent } from './top-navigation/top-navigation.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { ArticleCardComponent } from './article-card/article-card.component';
import { AuthorCardComponent } from './author-card/author-card.component';
import { AnthologyCardComponent } from './anthology-card/anthology-card.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    TopNavigationComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ArticleCardComponent,
    AuthorCardComponent,
    AnthologyCardComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
