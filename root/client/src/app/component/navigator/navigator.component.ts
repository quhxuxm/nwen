import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Component} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {SecurityService} from '../../service/security.service';
import {SecurityContext} from '../../vo/security-context';

@Component({
  selector: 'nwen-navigator',
  templateUrl: './navigator.component.html',
  styleUrls: ['./navigator.component.scss']
})
export class NavigatorComponent {
  securityContext: SecurityContext;
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private breakpointObserver: BreakpointObserver, private securityService: SecurityService) {
    this.securityContext = this.securityService.securityContext;
  }

  logout() {
    this.securityContext.clear();
  }
}
