import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AnthologyDetailComponent} from './component/anthology-detail/anthology-detail.component';
import {ArticleCreateComponent} from './component/article-create/article-create.component';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {AuthorDetailComponent} from './component/author-detail/author-detail.component';
import {HomeComponent} from './component/home/home.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'article/:id',
    component: ArticleDetailComponent
  },
  {
    path: 'article/create',
    component: ArticleCreateComponent
  },
  {
    path: 'anthology/:id',
    component: AnthologyDetailComponent
  },
  {
    path: 'author/:id',
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
