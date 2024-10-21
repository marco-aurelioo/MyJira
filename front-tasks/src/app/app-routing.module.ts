import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SobreComponent } from './component/sobre/sobre.component';
import { PrecosComponent } from './component/precos/precos.component';
import { ConfirmPlanComponent } from './component/confirm-plan/confirm-plan.component';
import { PaymentStatusComponent } from './component/payment-status/payment-status.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'sobre', component: SobreComponent },
  { path: 'precos', component: PrecosComponent },
  { path: 'confirm-plan', component: ConfirmPlanComponent,  canActivate: [AuthGuard] },
  { path: 'payment-status', component: PaymentStatusComponent,  canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'precos' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
