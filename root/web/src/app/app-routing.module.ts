import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AnthologyCreateComponent} from './component/anthology-create/anthology-create.component';
import {AnthologyDetailComponent} from './component/anthology-detail/anthology-detail.component';
import {ArticleCreateComponent} from './component/article-create/article-create.component';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {AuthorDetailComponent} from './component/author-detail/author-detail.component';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'article/detail/:id',
    component: ArticleDetailComponent
  },
  {
    path: 'article/create',
    component: ArticleCreateComponent
  },
  {
    path: 'anthology/detail/:id',
    component: AnthologyDetailComponent
  },
  {
    path: 'anthology/create',
    component: AnthologyCreateComponent
  },
  {
    path: 'author/detail/:id',
    component: AuthorDetailComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
