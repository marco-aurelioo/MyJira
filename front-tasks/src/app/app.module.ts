// src/app/app.module.ts
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { KeycloakService } from './services/keycloak.service';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { SobreComponent } from './component/sobre/sobre.component';
import { PrecosComponent } from './component/precos/precos.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { NavegComponent } from './component/naveg/naveg.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmPlanComponent } from './component/confirm-plan/confirm-plan.component';
import { PaymentStatusComponent } from './component/payment-status/payment-status.component';
import { OrganizationComponent } from './component/organization/organization.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './component/profile/profile.component';
import { EquipeComponent } from './component/equipe/equipe.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () => keycloak.init();
}

@NgModule({
  declarations: [
    
    AppComponent, 
    HomeComponent, 
    LoginComponent,
    SobreComponent, 
    PrecosComponent, 
    HeaderComponent, 
    FooterComponent, 
    NavegComponent, ConfirmPlanComponent, PaymentStatusComponent, OrganizationComponent, ProfileComponent, EquipeComponent
    
      ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule, 
    MatIconModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule  
  ],
  providers: [
    
    HttpClientModule,
    KeycloakService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService],
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
