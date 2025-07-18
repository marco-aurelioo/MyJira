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
  
//   {
//     path: 'admin',
//     loadComponent: () => import('./admin/admin.component').then(m => m.AdminComponent),
//     canActivate: [AuthGuard, RoleGuard],
//     data: { roles: ['admin'] }
//   },
//   {
//     path: 'unauthorized',
//     loadComponent: () => import('./unauthorized/unauthorized.component').then(m => m.UnauthorizedComponent)
//   }
];