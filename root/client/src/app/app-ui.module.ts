import {LayoutModule} from '@angular/cdk/layout';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatCheckboxModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatSidenavModule,
  MatTabsModule, MatToolbarModule, MatTooltipModule
} from '@angular/material';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {library} from '@fortawesome/fontawesome-svg-core';
import {fab} from '@fortawesome/free-brands-svg-icons';
import {far} from '@fortawesome/free-regular-svg-icons';
import {fas} from '@fortawesome/free-solid-svg-icons';

library.add(fas, far, fab);

@NgModule({
  imports: [
    CommonModule,
    LayoutModule,
    FontAwesomeModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatMenuModule,
    MatInputModule,
    MatCheckboxModule,
    MatTabsModule
  ],
  exports: [
    LayoutModule,
    FontAwesomeModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatMenuModule,
    MatInputModule,
    MatCheckboxModule,
    MatTabsModule
  ],
  declarations: []
})
export class AppUiModule {
}
