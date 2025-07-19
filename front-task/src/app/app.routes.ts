import { Routes } from '@angular/router';
import { AuthGuard } from '../app/guards/auth.guard';
import { RoleGuard } from '../app/guards/role.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    loadComponent: () => import('../app/component/dashboard/dashboard.component').then(m => m.DashboardComponent)
  },
  {
    path: 'profile',
    loadComponent: () => import('../app/component/profile/profile.component').then(m => m.ProfileComponent),
    canActivate: [AuthGuard] 
  },
//   {
//     path: 'unauthorized',
//     loadComponent: () => import('./unauthorized/unauthorized.component').then(m => m.UnauthorizedComponent)
//   }
];