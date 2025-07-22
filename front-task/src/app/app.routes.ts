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
  {
    path: 'projetos',
    loadComponent: () => import('../app/component/projetos/projetos.component').then(m => m.ProjetosComponent),
    canActivate: [AuthGuard] 
  },
  {
    path: 'projeto/:id',
    loadComponent: () => import('../app/component/projeto/projeto.component').then(m => m.ProjetoComponent),
    canActivate: [AuthGuard] 
  },
  {
    path: 'participar',
    loadComponent: () => import('../app/component/participar/participar.component').then(m => m.ParticiparComponent),
    canActivate: [AuthGuard] 
  },
//   {
//     path: 'unauthorized',
//     loadComponent: () => import('./unauthorized/unauthorized.component').then(m => m.UnauthorizedComponent)
//   }
];