import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { SobreComponent } from './component/sobre/sobre.component';
import { ProfileComponent } from './component/profile/profile.component';
import { ProjetosComponent } from './component/projetos/projetos.component';
import { ProjetoComponent } from './component/projeto/projeto.component';
import { ParticiparComponent } from './component/participar/participar.component';

const routes: Routes = [
  
  { path: '', component: SobreComponent },
  { path: 'sobre', component: SobreComponent },
  { path: 'projetos', component: ProjetosComponent , canActivate: [AuthGuard]},
  { path: 'projeto/:id', component: ProjetoComponent , canActivate: [AuthGuard]},
  { path: 'participar', component:  ParticiparComponent, canActivate: [AuthGuard]},
  
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'sobre' },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
