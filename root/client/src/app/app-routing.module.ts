import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AboutComponent} from './component/about/about.component';
import {AnthologyCreateComponent} from './component/anthology-create/anthology-create.component';
import {AnthologyDetailComponent} from './component/anthology-detail/anthology-detail.component';
import {ArticleCreateComponent} from './component/article-create/article-create.component';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {AuthorDetailComponent} from './component/author-detail/author-detail.component';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {ProfileComponent} from './component/profile/profile.component';
import {RegisterComponent} from './component/register/register.component';

const routes: Routes = [
  {
    path: '/client/home',
    component: HomeComponent
  },
  {
    path: '/client/login',
    component: LoginComponent
  },
  {
    path: '/client/register',
    component: RegisterComponent
  },
  {
    path: '/client/profile',
    component: ProfileComponent
  },
  {
    path: '/client/about',
    component: AboutComponent
  },
  {
    path: '/client/article/detail/:id',
    component: ArticleDetailComponent
  },
  {
    path: '/client/article/create',
    component: ArticleCreateComponent
  },
  {
    path: '/client/anthology/detail/:id',
    component: AnthologyDetailComponent
  },
  {
    path: '/client/anthology/create',
    component: AnthologyCreateComponent
  },
  {
    path: '/client/author/detail/:id',
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
