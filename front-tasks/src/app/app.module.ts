// src/app/app.module.ts
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { KeycloakService } from './services/keycloak.service';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { SobreComponent } from './component/sobre/sobre.component';

import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { NavegComponent } from './component/naveg/naveg.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './component/profile/profile.component';
import { ProjetosComponent } from './component/projetos/projetos.component';
import { HomeComponent } from './home/home.component';
import { ProjetoComponent } from './component/projeto/projeto.component';
import { ParticiparComponent } from './component/participar/participar.component';
import { MinhasSolicitacoesComponent } from './component/participar/minhas-solicitacoes/minhas-solicitacoes.component';
import { ParticiparProjetosComponent } from './component/participar/participar-projetos/participar-projetos.component';
import { AprovarSolicitacoesComponent } from './component/participar/aprovar-solicitacoes/aprovar-solicitacoes.component';

import { MembrosComponent } from './component/projeto/membros/membros.component';
import { ConfiguracoesComponent } from './component/projeto/configuracoes/configuracoes.component';
import { TarefasComponent } from './component/projeto/tarefas/tarefas.component';
import { QuadroSprintComponent } from './component/projeto/quadro-sprint/quadro-sprint.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () => keycloak.init();
}

@NgModule({
  declarations: [
    AppComponent, 
    LoginComponent,
    SobreComponent, 
    HeaderComponent, 
    FooterComponent, 
    NavegComponent,
    ProfileComponent,
    ProjetosComponent, 
    HomeComponent,
    ProjetoComponent, 
    ParticiparComponent, 
    MinhasSolicitacoesComponent,
    ParticiparProjetosComponent,
    AprovarSolicitacoesComponent,
    MembrosComponent,
    ConfiguracoesComponent,
    TarefasComponent,
    QuadroSprintComponent
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
