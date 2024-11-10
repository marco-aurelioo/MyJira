import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SobreComponent } from './component/sobre/sobre.component';
import { PrecosComponent } from './component/precos/precos.component';
import { ConfirmPlanComponent } from './component/confirm-plan/confirm-plan.component';
import { PaymentStatusComponent } from './component/payment-status/payment-status.component';
import { OrganizationComponent } from './component/organization/organization.component';
import { ProfileComponent } from './component/profile/profile.component';

const routes: Routes = [
  
  { path: 'login', component: LoginComponent },
  { path: 'sobre', component: SobreComponent },
  { path: 'precos', component: PrecosComponent },
  
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent,  canActivate: [AuthGuard] },
  { path: 'confirm-plan', component: ConfirmPlanComponent,  canActivate: [AuthGuard] },
  { path: 'payment-status', component: PaymentStatusComponent,  canActivate: [AuthGuard] },
  { path: 'organizacao', component: OrganizationComponent,  canActivate: [AuthGuard] },
  
  { path: '**', redirectTo: 'sobre' }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
