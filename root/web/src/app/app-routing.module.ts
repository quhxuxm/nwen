import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
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
