import { Routes } from '@angular/router';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { PublicPageComponent } from './component/public-page/public-page.component'; // Import do componente de acesso livre
import { AppAuthGuard } from './services/AppAuthGuard ';


export const routes: Routes = [
  { path: '', redirectTo: 'public', pathMatch: 'full' }, // Rota padrão
  { path: 'new-task', component: NewTaskComponent, canActivate: [AppAuthGuard], data: { roles: ['user'] } },
  { path: 'scrum-board', component: ScrumBoardComponent, canActivate: [AppAuthGuard], data: { roles: ['user'] } },
  { path: 'user-profile', component: UserProfileComponent, canActivate: [AppAuthGuard], data: { roles: ['user'] } },
  { path: 'public', component: PublicPageComponent }, // Rota de acesso livre
  { path: '**', redirectTo: 'public' } // Fallback para rotas não encontradas
];
