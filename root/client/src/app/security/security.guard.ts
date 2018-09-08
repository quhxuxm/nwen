import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {SecurityService} from '../service/security.service';

@Injectable({
  providedIn: 'root'
})
export class SecurityGuard implements CanActivate {
  constructor(private securityService: SecurityService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.securityService.securityContext.isAuthenticated()) {
      return true;
    }
    this.router.navigateByUrl('/login');
    return false;
  }
}
